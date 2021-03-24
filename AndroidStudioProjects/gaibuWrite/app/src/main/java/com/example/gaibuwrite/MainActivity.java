package com.example.gaibuwrite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/*******************************************************
 * テキストｐ158　外部ストレージ　書込み サンプル
 * 外部ストレージでは、アプリ間で書き込んだファイルを共有できる
 * 読込は、次のサンプルから行ってください。
 * OutputStream等の使い方は内部ストレージと同様。
 * 外部ストレージの場合、書込み先の指定が変わる。
 ********************************************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* buttonが押されたら外部ストレージにファイルを作成し、書込み */
        Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                /* OutputStream関連の変数 */
                OutputStream out = null;
                OutputStreamWriter writer = null;
                BufferedWriter bw = null;

                try {
                    /* システムから外部ストレージの保存先パスを取得 */
                    File folder = Environment.getExternalStoragePublicDirectory("MyDocuments");

                    /* もし、"MyDocuments"ディレクトリが存在しなければ作成 */
                    if (!folder.exists()) {
                        boolean result = folder.mkdir();
                        /* 作成に失敗した場合は、このメソッドを終了 */
                        if (result == false) {
                            return; //onCreateメソッドが終了する
                        }
                    }

                    /* 上記で取得・作成したディレクトリに新規ファイルの作成 */
                    File file = new File(folder,"myText.txt");

                    /* 作成したファイルに対して,ストリームをオープン */
                    out = new FileOutputStream(file);
                    writer = new OutputStreamWriter(out);
                    bw = new BufferedWriter(writer);

                    /* ストリームにデータを書き込む */
                    bw.write("write data"); //ストリームの先にあるファイルに書き込まれる

                    /* Toastで保存完了を通知 */
                    Toast.makeText(MainActivity.this,"保存完了", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    /* IOExceptionはログキャットに表示 */
                    Log.e("Internal","IO Exception" + e.getMessage(),e);
                } finally {

                    /* ストリーム関連の終了処理 */
                    try {
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
        });
    }
}