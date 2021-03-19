package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textview;  //変数定義

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView)findViewById(R.id.textView);

        Button left_btn = (Button)findViewById(R.id.button);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("はじめまして");
            }
        });
        Button right_btn = (Button)findViewById(R.id.button2);
        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = 0;
                for (int i = 1; i < 11; i++){
                    result += i;
                }
                textview.setText(new Integer(result).toString());
                // textview.setText(Integer.toString(result));
            }
        });
    }
}