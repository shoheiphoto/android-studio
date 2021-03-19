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
        int sum = intent.getIntExtra("合計金額",1);
        int num = intent.getIntExtra("人数",1);

        int wari = sum / num;
        int amari = sum % num;

        TextView textview = (TextView)findViewById(R.id.textView);
        textview.setText("ようこそ　" + name + "さん");
        TextView textview2 = (TextView)findViewById(R.id.textSum);
        textview2.setText("合計金額　" + sum + "円");
        TextView textview3 = (TextView)findViewById(R.id.textNum);
        textview3.setText("参加人数　" + num + "人");
        TextView textview4 = (TextView)findViewById(R.id.textWarikan);
        textview4.setText("一人あたり　" + Integer.toString(wari) + "円　あとだれか" + amari + "円だしてー");

        String msg = name + " : " + mail + " : " + Integer.toString(age);
        Toast.makeText(MainActivity2.this, msg, Toast.LENGTH_SHORT).show();

        Button backBtn = (Button)findViewById(R.id.button2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                intentBack.putExtra("結果",Integer.toString(wari));
                setResult(RESULT_OK,intentBack);
                finish();
            }
        });
    }
}