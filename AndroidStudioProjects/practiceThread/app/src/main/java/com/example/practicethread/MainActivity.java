package com.example.practicethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textView);
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("ON");
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("OFF");

                Thread th = new myThread();
                th.start();
            }
        });
    }

    public class myThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i <= 5) {
                Log.d("test", "thread" + i);
                try {
                    Thread.sleep(1000);
                    mHandler.post(new Thread() {
                        @Override
                        public void run() {
                            txt.setText("スレッド起動");
                        }
                    });
                } catch (InterruptedException e) {
                }
                i++;
            }
        }
    }
}