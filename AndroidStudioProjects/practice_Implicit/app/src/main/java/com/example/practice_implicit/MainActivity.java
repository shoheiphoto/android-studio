package com.example.practice_implicit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menu1Btn = (Button)findViewById(R.id.button);
        menu1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yahoo.co.jp/"));
                startActivity(intent);
            }
        });

        Button menu2Btn = (Button)findViewById(R.id.button2);
        menu2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Osaka"));
                startActivity(intent);
            }
        });

        Button menu3Btn = (Button)findViewById(R.id.button3);
        menu3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=StopWatch"));
                startActivity(intent);
            }
        });

        Button menu4Btn = (Button)findViewById(R.id.button4);
        menu4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:09099999999"));
                startActivity(intent);
            }
        });

        Button menu5Btn = (Button)findViewById(R.id.button5);
        menu5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(intent.EXTRA_TEXT, "文字列");
                startActivity(intent);
            }
        });

        Button menu6Btn = (Button)findViewById(R.id.button6);
        menu6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(intent.EXTRA_SUBJECT, "テストメール");
                intent.putExtra(intent.EXTRA_TEXT, "本文は入っていますか？");
                startActivity(intent);
            }
        });

    }
}