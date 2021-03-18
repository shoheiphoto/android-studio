package com.example.practice_intentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("名前");
        String mail = intent.getStringExtra("メール");
        int age = intent.getIntExtra("年齢",1);
        String sum = intent.getStringExtra("合計金額");
        Long kingaku = Long.parseLong(sum);
        String num = intent.getStringExtra("人数");
        Long ninzu = Long.parseLong(num);
        double wari = kingaku / ninzu;

        TextView textview = (TextView)findViewById(R.id.textView);
        textview.setText("ようこそ　" + name + "さん");
        TextView textview2 = (TextView)findViewById(R.id.textSum);
        textview2.setText("合計金額　" + sum + "円");
        TextView textview3 = (TextView)findViewById(R.id.textNum);
        textview3.setText("参加人数　" + num + "人");
        TextView textview4 = (TextView)findViewById(R.id.textWarikan);
        textview4.setText("一人あたり　" + wari + "円");

        String msg = name + " : " + mail + " : " + Integer.toString(age);
        Toast.makeText(MainActivity2.this, msg, Toast.LENGTH_LONG).show();

        Button backBtn = (Button)findViewById(R.id.button2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}