package com.example.practice_implicit2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;


/*******************************************************
 *テキストp130 暗黙的インテント　演習
 *※テキスト129ページ下側と130ページ上側のサンプルを参考のこと
 ******************************************************/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* もし、結果が失敗（RESULT_OKでなければ）なら */
        if (resultCode != RESULT_OK) {
            /* 失敗したresultCodeをToastで表示 */
            String msg = "Error:onAcitityResult:" + Integer.toString(requestCode);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG);

            /*以降は実行せず、このメソッドを終了 */
            return;
        }

        /* もし、Google音声検索なら(requestCodeが１なら) */
        if (requestCode == 1) {
            // 複数結果があったときのためにStringBufferを生成
            StringBuffer sb = new StringBuffer();

            // 音声認識の検索結果を取得（リスト）
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // リストからデータをひとつずつ読出し、StringBufferに格納
            for (String result : results) {
                sb.append(result);
                sb.append(" ");
            }

            /* 取得した音声認識のキーワードを暗黙的インテントでWeb検索 */
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, sb.toString());
            startActivity(intent);
        }

        /* もし、ギャラリーの表示なら */
        if (requestCode==2){
            // ContentResolver経由でファイルパスを取得
            //取得した値が"content://スキームなのでContentResolverを利用してpathをFile型に変換
            ContentResolver cr = getContentResolver();
            String[] columns = {
                    MediaStore.Images.Media.DATA
            };

            Cursor c = cr.query(data.getData(), columns, null, null, null);
            int column_index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            c.moveToFirst();
            String path = c.getString(column_index);
            Log.v("test", "path=" + path);
            File filePath = new File(path);

            /* 変換したFile型のpathを利用してメールに添付 */
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            String[] to = {"it00@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"テストメール");
            intent.putExtra(Intent.EXTRA_TEXT,"本文は\n入ってますか。");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filePath));
            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent,"メールアプリを選択"));

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* buttonが押されたら */
        Button menu1Btn = (Button)findViewById(R.id.button1);
        menu1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 音声認識からGoogle検索 */
                try {
                    Intent intent = new Intent();
                    intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.ACTION_WEB_SEARCH);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"検索したいキーワードは？");
                    startActivityForResult(intent,1);

                }catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Google音声検索が使えません",Toast.LENGTH_SHORT);
                }
            }
        });

        /* button2が押されたら */
        Button menu2Btn = (Button)findViewById(R.id.button2);
        menu2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* ギャラリーから画像を選択してメール送信 */
                /* ACTION_PICKで画像の選択 */
                Intent intent = new Intent(Intent.ACTION_PICK);

                /* 選択するのはimageの中から */
                intent.setType("image/*");

                /* ギャラリーの選択Activityの起動 */
                startActivityForResult(intent,2);

            }
        });

    }
}