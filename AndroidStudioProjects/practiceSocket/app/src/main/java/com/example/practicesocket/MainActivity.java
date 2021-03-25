package com.example.practicesocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /* メンバ変数宣言 */
    private final static String BR=System.getProperty("line.separator");    //システムの改行コードを取得
    private final static String IP = "10.26.142.108";   // 接続先IPアドレス
    private TextView lblReceive;    // 受信データ表示用TextView
    private EditText edtSend;   //送信データ入力表EditText
    private Button btnSend;     // 送信ボタン
    private Socket socket;      //ソケット通信インスタンス（クライアント、TCP）
    private InputStream in;     // ソケット接続用InputStream
    private OutputStream out;     // ソケット接続用OutputStream
    private boolean error;          // 通信エラー判定用
    private final Handler handler = new Handler();  //通信スレッドからのUIスレッドアクセス用Handler

    /* アクティビティのライフサイクルメソッド（生成時） */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 各ビューのインスタンスを取得 */
        lblReceive = (TextView)findViewById(R.id.RECV);
        edtSend = (EditText)findViewById(R.id.SEND);
        btnSend = (Button)findViewById(R.id.SENDBTN);

        /* リスナー登録 */
        btnSend.setOnClickListener(this);   //ボタンを押されたときの処理は、このクラスに実装
    }

    /* アクティビティのライフサイクルメソッド（スタート時） */
    @Override
    protected void onStart() {
        super.onStart();

        /* 新規スレッド生成 */
        Thread thread = new Thread(){
            public void run() {
                /* 指定されたIPアドレス、ポート番号に接続開始 */
                try {
                    connect(IP,8081);   // 自作メソッド
                } catch (Exception e){
                    //未実装
                }

            }
        };  /* 通信処理は必ず待ちが発生するのでUIスレッドではなく、別のスレッドで行うこと */

        /* スレッド開始 */
        thread.start();
    }

    /* クリックイベントが発生したときの処理（発生したビューは引数：viewに渡される） */
    @Override
    public void onClick(View view) {
        /* 送信ボタンが押されたらスレッドを新規に作成 */
        Thread thread = new Thread(new Runnable() {
            public void run() {
                /*  Socket通信の送信処理 */
                error = false;  //エラーの初期値設定

                try {
                    /* socketが生成できていて、すでに接続されていれば */
                    if(socket != null && socket.isConnected()) {
                        /* 送信データをバイト配列に変換して、outputStream経由で送信 */
                        byte[] w = edtSend.getText().toString().getBytes("UTF8");
                        out.write(w);
                        out.flush();    //バッファ内を強制的に送信
                    }
                } catch (Exception e){
                    error = true;   // 例外発生時は、error変数をtrueとする
                }

                /* 送信用スレッドからUIスレッドに送信結果を表示 */
                handler.post(new Runnable() {public void run(){
                    if(!error) {
                        /* エラーが発生していなければ、editTextをクリア */
                        edtSend.setText("");
                    } else {
                        /* エラーが発生していれば、通信失敗をテキストビューに表示 */
                        addText("通信失敗しました");    //TextView表示用の自作メソッド
                    }
                }});
            }});
        /* スレッド開始 */
        thread.start();
    }

    /* アクティビティのライフサイクルメソッド（ストップ時） */
    @Override
    protected void onStop() {
        super.onStop();
        /* 通信のコネクションを切断 */
        disconnect();   //通信切断処理用の自作メソッド
    }

    /* Socket通信接続（コネクション確立）用メソッド */
    private void connect(String ip,int port) {
        int size;   // データサイズ
        String str; //受信文字列

        byte[] w = new byte[1024];  //受信データ保持用（受信もバイト配列で受信される）

        /* 接続開始 */
        try {
            addText("接続中"); //TextViewに接続中と表示
            socket = new Socket(ip,port);
            // 指定されたIPアドレス、ポート番号でソケット（クライアント、TCP）を生成
            //socket生成時に接続の開始がされる
            in = socket.getInputStream();   // ソケットに対するinputStreamの取得

            out = socket.getOutputStream(); // ソケットに対するoutputStreamの取得

            /* textView完了を通知 */
            addText("接続完了");

            /* データの受信待ち処理
            （ここで待ちが発生するのでこのメソッド使用時はスレッドを分けること） */
            /* ソケットが生成できていて、接続が確立している間繰り返す */
            while (socket != null && socket.isConnected()) {
                size = in.read(w);  // 受信データをinputStream経由で読み込む
                // （戻り値は受信データサイズ、引数で指定した変数に、受信データが格納される）

                /* 受信データサイズが0以下ならば再度取得 */
                if(size <= 0) continue;
                /* 取得したバイト配列をUTF-8形式で文字列に変換 */
                str = new String(w, 0, size, "UTF-8");

                /* 変換した文字列をTextViewに表示 */
                addText(str);
            }
        } catch (Exception e) {
            /* 例外発生時は、通信失敗をTextViewに表示 */
            addText("通信失敗しました");
        }
    }

    /* textViewへの表示用自作メソッド */
    private void addText(final String text) {
        /* ハンドラ経由でUIスレッドのTextViewに文字列を表示 */
        /* ハンドラ経由なので、UIスレッド以外からもこのメソッドを通して表示可能 */
        handler.post(new Runnable(){
            public void run() {
                lblReceive.setText(text + BR + lblReceive.getText());
                // 引数で渡された文字列の表示し、
                // 改行後、すでに表示されていた文字も表示する
            }
        });
    }

    /* ソケット通信切断用自作メソッド */
    private void disconnect() {
        try {
            socket.close(); // socket通信の接続を閉じる
            socket = null;  //ソケットインスタンスを開放
        } catch (Exception e) {
            //未実装
        }
    }
}
