package com.example.practice_plc01;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**** 各変数の宣言  ****/
    private Button openButton,closeButton,cwButton,ccwButton,stopButton;
    private TextView lblReceive;
    private ImageView img;
    private ImageView img2;

    /**** クラス変数の宣言 ****/

    private static String BR = System.getProperty("line.separator");
    /*---- Qヘッダの固定情報 ----*/
    private static String QHEAD = "5000"+"00"+"FF"+"03FF"+"00";
    private static String CPUTIMER = "0010";

    /*** 演習1変更部分 ****/
    private static String CMD_CW   = "14010001" + "M*001000" + "0001";   // M1000
    private static String CMD_CCW  = "14010001" + "M*001001" + "0001";   // M1001
    private static String CMD_STOP = "14010001" + "M*001000" + "0002";  // M1000とM1001の2つ分まとめて
    //今回はM1000とM1001のONとOFFだけ制御する
    // タブレットからX05やY10といった入出力デバイスを直接制御するのは危険なため

    /*----PLCの通信情報 -----*/
    private static String IP = "10.26.142.31";
    private static int  PORT = 4096;
    private static int DELAY = 50;

    /*---- socketの準備 -----*/
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    private final Handler handler = new Handler();

    private boolean     flag;
    private boolean     error;

    String cmd, tcmd;
    Integer lcmd;


//  次ページへ
    /**** Socketスレッドからの文字列を受取ってアクティビティに表示するメソッド ****/
    private void addText(final String text) {
        /** ハンドラ経由で文字列を受信して、TextViewに表示 **/
        handler.post(new Runnable() {
            public void run() {
                String moji, tmp;

                moji = lblReceive.getText().toString(); //元々表示されていた文字列を取得
                if (moji.length() > 1000) {
                    lblReceive.setText(text);   //表示文字が1000文字を超えたら消して新しく表示
                } else {
                    lblReceive.setText(text + BR + moji);
                    // 受信した文字を表示し、改行した後にもともと表示されていた文字を表示
                }

            }
        });
    }

    /**** onCreateでレイアウト情報との関連付け *****/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = (Button)findViewById(R.id.button);
        closeButton = (Button)findViewById(R.id.button2);
        cwButton = (Button)findViewById(R.id.button3);
        ccwButton = (Button)findViewById(R.id.button4);
        stopButton = (Button)findViewById(R.id.button5);

        lblReceive = (TextView)findViewById(R.id.textView);

        /**** リスナー設定（今回はすべて自分自身のインスタンスで処理）**/
        openButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        ccwButton.setOnClickListener(this);
        cwButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    /*** ボタンが押されたときのリスナー ***/
    @Override
    public void onClick(View v) {
        final Thread thread;
        Button btn = (Button)v;
        try {
            /* 押されたボタンごとで処理を切り替える */
            switch (btn.getId()) {
                case R.id.button:   //OPENボタンが押されたら
                    if (socket==null) {
                        //スレッドを開始してソケットで接続
                        thread = new Thread() {
                            public void run() {
                                try {
                                    connect(IP, PORT);
                                } catch (Exception e) {
                                    addText(e.getMessage());
                                }
                            }
                        };
                        thread.start();
                    }
                    break;
                case R.id.button2:  // CLOSEボタンが押されたら
                    /* 接続を解除 */
                    disconnect();
                    break;
//  次ページへ

                case R.id.button3:  //CW,CCW,STOPが押されたら
                case R.id.button4:
                case R.id.button5:
                    error = false;
                    if (btn.getId()== R.id.button3) {
                        cmd = CPUTIMER + CMD_CW   + "1"; //M1000:ON
                    } else if(btn.getId() == R.id.button4) {
                        cmd = CPUTIMER + CMD_CCW  + "1";//M1001:ON
                    }else {
                        cmd = CPUTIMER + CMD_STOP + "00"; // M1000:OFF & M1001:OFF
                    }

                    /*-- 要求データ長のサイズを計算してパケット作成 ----*/
                    lcmd = cmd.length();
                    tcmd = "000" + Integer.toHexString(lcmd);
                    cmd = QHEAD + tcmd.substring(tcmd.length()-4,tcmd.length()) + cmd;
                    /*---- スレッドを作成して、ソケット通信でデータを送信 ----*/
                    /*---- 応答データはOPENボタンで作成したスレッドで受け取り ----*/
                    thread = new Thread(new Runnable() {
                        public void run() {
                            try {
                                /*** ソケット通信が接続されていたら ****/
                                if (socket != null && socket.isConnected()) {
                                    // 送信データ（cmd）をバイト列に変換して
                                    byte[] w = cmd.toString().getBytes("US-ASCII");
                                    out.write(w);   // 送信準備
                                    out.flush();    // 送信開始
                                    Thread.sleep(DELAY);
                                }
                            } catch (Exception e) {
                                error = true;
                                addText("network error");
                            }
                        }
                    });
                    /*** スレッド開始 ***/
                    thread.start();
                    break;
            }
        } catch (Exception e){
            addText("Error Occured");
        }
    }

    /*** ソケットの接続と応答データの受信用メソッド ****/
    /*** OPENボタンで別スレッドとして動作         ****/
    private void connect(String ip,int port) throws IOException {
        int size;
        String str;
        byte[] w = new byte[1024];
        if (socket != null) {
            return;
        }
        try {
            socket = new Socket(ip,port);   // socketインスタンス生成
            if(socket != null) addText("connected");
            in = socket.getInputStream();   // ソケットの入力ストリームを取得
            out = socket.getOutputStream(); // ソケットの出力ストリームを取得

            /*---- 接続中は常に受信動作をして、受信データをハンドラ経由で画面に表示  ----*/
            while (socket != null && socket.isConnected()){
                size = in.read(w);  // データサイズの取得
                if (size <= 0) {    // 応答データがなければ
                    if (socket == null) break;  // 途中でsocketが破棄されていたら終了
                    continue;       // whileの先頭に戻る
                }
                str = new String(w,0,size,"US-ASCII");  //受信データを文字列に変換
                addText(str);   // 受信データの文字列をハンドラ経由で画面に表示
            }
        } catch (Exception e){
            /* エラー処理は特になし */
        }
    }
//  次ページへ


    /*** ソケット通信の切断用メソッド ***/
    private void disconnect() {
        try {
            socket.close(); // ソケット通信を閉じて
            socket = null;  // ソケットインスタンスを破棄
            addText("disconnect");
        } catch (Exception e) {

        }
    }

}

