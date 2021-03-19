package com.example.practice_button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
// よく使われれる
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
                        txtview.setText("左");
                    }
                }
        );

        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
                        txtview.setText("右");
                    }
                });
    }
}


//（別記載１）
//上記コードをわかりやすく書いたもの
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(new Button1ClickListener());
        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(new Button2ClickListener());
    }

    class Button1ClickListener implements View.OnClickListener {
        public void onClick(View v){
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
            txtview.setText("左");
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        public void onClick(View v){
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
            txtview.setText("右");
        }
    }
}




//（別記載２）
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);

        switch (v.getId()) {
            case R.id.ID_BTN1: txtview.setText("左");
            break;
            case R.id.ID_BTN2: txtview.setText("右");
            break;
        }
    }
}

// 写経
public class MainActivity extends  AppCompatActivity implements  View.OnClickListener{
    @Override
    protected  void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
        switch (v.getId()) {
            case R.id.ID_BTN1:
                txtview.setText("左");
                break;
            case R.id.ID_BTN2:
                txtview.setText("右");
                break;
        }
    }
}

 */


//（別記載３）
// この方法が一番プログラム量が少なくなりますが、メソッド名とプロパティの値が違った時にコンパイルエラーにはならず、
// 実行時エラーとなりデバッグが困難です。

//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void onMyClick(View v) {
//        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
//        switch (v.getId()) {
//            case R.id.ID_BTN1:
//                txtview.setText("左");
//                break;
//            case R.id.ID_BTN2:
//                txtview.setText("右");
//                break;
//        }
//    }
//}

// 写経
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onMyClick(View v) {
        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
        switch (v.getId()) {
            case R.id.ID_BTN1:
                txtview.setText("左");
                break;
            case R.id.ID_BTN2:
                txtview.setText("右");
                break;
        }
    }
}



