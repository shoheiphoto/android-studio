package com.example.phone_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView phoneNumber;   // textViewのインスタンスを保持

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* レイアウトファイルの読込 */
        setContentView(R.layout.layout4);

        /* 各ビューのidを使ってレイアウトファイルと関連付け */
        phoneNumber = (TextView)findViewById(R.id.textView2);

        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        Button btn5 = (Button)findViewById(R.id.button5);
        Button btn6 = (Button)findViewById(R.id.button6);
        Button btn7 = (Button)findViewById(R.id.button7);
        Button btn8 = (Button)findViewById(R.id.button8);
        Button btn9 = (Button)findViewById(R.id.button9);
        Button btn0 = (Button)findViewById(R.id.button0);

        Button callButton = (Button)findViewById(R.id.buttonCall);

        /* 各ボタンが押されたときの処理（イベントリスナー） */
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                /* textView2に表示されている文字を取得して */
                String num = phoneNumber.getText().toString();
                /* 末尾にボタンの数字を追加して表示 */
                phoneNumber.setText(num + "1");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "9");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String num = phoneNumber.getText().toString();
                phoneNumber.setText(num + "0");
            }
        });

        /* 電話をかけるボタンが押されたときは、 */
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 表示している数字（電話番号）をクリア
                phoneNumber.setText("");
            }
        });
    }
}

