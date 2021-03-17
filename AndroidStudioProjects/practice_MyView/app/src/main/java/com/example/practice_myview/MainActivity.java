package com.example.practice_myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //MainActivityから呼び出し

        /* レイアウトファイルのmyViewクラスの呼び出し */
        final MyView myview = (MyView)findViewById(R.id.myview);
        final int myviewWidth = myview.getWidth();
        final int myviewHeight = myview.getHeight();
        final Random random = new Random();

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.bl1.setmVXY(10, 10);    // myViewクラスのフィールドやメソッド呼び出し
                myview.bl2.setmVXY(10, 10);
                myview.bl3.setmVXY(10, 10);    // myViewクラスのフィールドやメソッド呼び出し
                myview.bl4.setmVXY(10, 10);    // myViewクラスのフィールドやメソッド呼び出し
            }
        });


        Button button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.bl1.setmVXY(-10, -10);    // myViewクラスのフィールドやメソッド呼び出し
                myview.bl2.setmVXY(-10, -10);
                myview.bl3.setmVXY(-10, -10);    // myViewクラスのフィールドやメソッド呼び出し
                myview.bl4.setmVXY(-10, -10);    // myViewクラスのフィールドやメソッド呼び出し
            }
        });

    }
}