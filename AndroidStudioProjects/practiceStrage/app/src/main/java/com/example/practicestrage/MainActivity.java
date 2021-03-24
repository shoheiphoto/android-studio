package com.example.practicestrage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //OutputStreamを使うための各インスタンス保持用変数を宣言
                OutputStream out = null;
                OutputStreamWriter writer = null;
                BufferedWriter bw = null;

                try {
                    //myText.txtというファイルに対して、OutputStreamを開く
                    out = openFileOutput("myText.txt", Context.MODE_PRIVATE);

                    // StreamWriterに開いたストリームを設定する
                    writer = new OutputStreamWriter(out);

                    // さらにBufferedWriterにwriterをセット
                    bw = new BufferedWriter(writer);

                    // BufferedWriterに保存するデータを書込み
                    bw.write("write data");

                    //テキストに追加。書き込むデータはwriteメソッドを複数回呼び出してもOK
                    bw.write("\ntest\n");

                    //保存完了をToastで通知
                    Toast.makeText(MainActivity.this, "保存完了", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e("Internal", "IO Exception" + e.getMessage(), e);
                    // IO Exception発生時はログキャットに表示
                } finally {
                    try {
                        // ストリームの取得時に例外が発生してもしなくても、
                        // もし、取得できていたら閉じておく
                        if (bw != null) {
                            bw.close();
                        }
                        if (writer != null) {
                            writer.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        Log.e("Internal", "IO Exception" + e.getMessage(), e);
                    }
                }
            }
        }); //button（書込みボタン）の処理ここまで


        /* button2が押されたら内部ストレージのファイルの読込を行う */
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                //インプットストリームを使用すためのインスタンス保持用変数
                InputStream in = null;
                InputStreamReader sr = null;
                BufferedReader br = null;

                /* ファイルのオープンと読込 */
                try {
                    //内部ストレージから”myText.txtというファイルを開く
                    in = openFileInput("myText.txt");

                    // ファイルに対してInputStreamReaderを生成
                    sr = new InputStreamReader(in);

                    // InputStreamReaderに対してBufferedReaderを生成
                    br = new BufferedReader(sr);

                    String line;    // 一行ごとの読込データ
                    StringBuilder sb = new StringBuilder(); //各行をStringBuilderでまとめる

                    /* readLineメソッドでファイルから1行ごとに読み込んでlineに格納 */
                    while ((line = br.readLine())!= null) {
                        sb.append(line).append("\n");
                        // 1行分をStringBuilderにセットし、最後に改行を挿入
                    }

                    /* 読み込み完了をToastで表示 */
                    Toast.makeText(MainActivity.this,"読込み完了",Toast.LENGTH_SHORT).show();

                    //テキストに追加 読み込んだファイルの内容をtextViewに表示
                    TextView textView = (TextView)findViewById(R.id.textView);
                    textView.setText(sb);

                } catch (IOException e){
                    /* 例外時の処理 */
                    Log.e("Internal","IO Exception " + e.getMessage(),e);
                } finally {
                    try {
                        /* ストリーム関連の終了処理 */
                        if (br != null) { br.close(); }
                        if (sr != null) { sr.close(); }
                        if (in != null) { in.close(); }
                    } catch (IOException e){
                        Log.e("Internal", "IO Exception " + e.getMessage(),e );
                    }
                }
            }
        }); //button2（読込ボタン）の処理ここまで　

    }
}
