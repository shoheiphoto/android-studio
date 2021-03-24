package com.example.practicereadfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                InputStreamReader sr = null;
                BufferedReader br = null;

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
                } catch (IOException e) {
                    Log.e("Internal","IO Exception" + e.getMessage(),e);
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        if (sr != null) {
                            sr.close();
                        }
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        Log.e("Internal","IO Exception" + e.getMessage(),e);
                    }

                }
            }
        });
    }
}