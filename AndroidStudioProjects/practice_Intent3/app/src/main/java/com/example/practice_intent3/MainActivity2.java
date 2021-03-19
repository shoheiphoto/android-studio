package com.example.practice_intent3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); //MainActivityから呼び出し

        Intent intent = getIntent();
        int mVX = intent.getIntExtra("x軸",1);
        int mVY = intent.getIntExtra("y軸",1);

        final MyView myview = (MyView)findViewById(R.id.myview);
        final int myviewWidth = myview.getWidth();
        final int myviewHeight = myview.getHeight();
        final Random random = new Random();

        myview.bl2.setmVXY(mVX, mVY);    // myViewクラスのフィールドやメソッド呼び出し

        String msg = Integer.toString(mVX) + " : " + Integer.toString(mVY);
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