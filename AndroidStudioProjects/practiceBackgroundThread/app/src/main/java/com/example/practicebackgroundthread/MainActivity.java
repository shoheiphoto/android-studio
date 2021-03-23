package com.example.practicebackgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MediaPlayer mPlayer;
                        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.swordslash1);
                        while (flag) {
                            try {
                                mPlayer.start();
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                }
                            }
                        }
                        });
                th.start();
                }
                });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });
    }
}