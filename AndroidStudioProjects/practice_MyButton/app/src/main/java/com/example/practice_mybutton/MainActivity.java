package com.example.practice_mybutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* レイアウトファイルに記述したMyButtonを読み込み */
        MyButton btn = (MyButton) findViewById(R.id.view);

        /* ｍｙButtonにタッチリスナーを設定 */
        btn.setOnTouchListener(new View.OnTouchListener() {
            /* このメソッドはTouchしたときに呼び出される */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // v=タッチイベントが発生したビューつまりMyButton
                MyButton myb = (MyButton)v;
                // MyButtonの円の表示位置をイベント発生位置に設定
                myb.setPosx(event.getX());
                myb.setPosy(event.getY());

                // MyButtonを強制的に再描画
                myb.invalidate();

                return false;
                /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                  onTouchイベント以外のイベントは実行しないようにfalseを戻り値に設定
                　(ボタンをタッチするとクリックとしても捉えることができる）
                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
            }
        });

        /* テキストサンプルに追加 */
        /* Viewタグで設定した通常のボタン */
        Button btn2 =(Button)findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyButtonをローカル変数として再度読み込み
                MyButton mybtn = (MyButton)findViewById(R.id.view);
                // 通常ボタンを押したら初期位置に戻す
                mybtn.setPosx(100f);
                mybtn.setPosy(100f);
                mybtn.invalidate(); //再描画
            }
        });

    }
}