package com.example.gaiburead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*******************************************************
 * テキストｐ160　外部ストレージ　読み込み サンプル
 * 外部ストレージでは、アプリ間で書き込んだファイルを共有できる
 * 書込みは、前のサンプルから行ってください。
 * InputStream等の使い方は内部ストレージと同様。
 * 外部ストレージの場合、読み込みもとの指定が変わる。
 ********************************************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* button1が押されたら外部ストレージからファイルを読み込む */
        Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener( new View.OnClickListener(){

            public void onClick(View v){
                /* InputStream関連の変数を宣言 */
                InputStream in = null;
                InputStreamReader sr = null;
                BufferedReader br = null;

                /* 外部ストレージからのファイルの読み込み */
                try {
                    /* 外部ストレージのMyDocumentsディレクトリの場所を取得 */
                    File folder = Environment.getExternalStoragePublicDirectory("MyDocuments");

                    /* MyDocumentsディレクトリのファイルのをインスタンスをとして開く */
                    File file = new File(folder,"myText.txt");

                    /* 開いたファイルにInputStreamを開く */
                    in = new FileInputStream(file);
                    sr = new InputStreamReader(in);
                    br = new BufferedReader(sr);

                    /* 開いたファイルからStream経由で1行ずつデータを取得 */
                    String line;    //1行分のデータ保持用変数
                    StringBuilder sb = new StringBuilder(); //各行をまとめる変数

                    /* 一行ごとに読み出してStringBuilderに追加 */
                    while ((line = br.readLine())!= null) {
                        sb.append(line).append("\n");   //読み込みデータと末尾改行コードを追加
                    }

                    /* Toastで読込完了を通知 */
                    Toast.makeText(MainActivity.this,"読込み完了",Toast.LENGTH_SHORT).show();

                    /* テキストに追加。読み込んだデータをtextViewに表示 */
                    ((TextView)findViewById(R.id.textView)).setText(sb);

                } catch (IOException e){
                    Log.e("Internal","IO Exception " + e.getMessage(),e);
                } finally {
                    try {
                        /* InputStream関連の終了処理 */
                        if (br != null) { br.close(); }
                        if (sr != null) { sr.close(); }
                        if (in != null) { in.close(); }
                    } catch (IOException e){
                        Log.e("Internal", "IO Exception " + e.getMessage(),e );
                    }
                }
            }
        });
    }
}

