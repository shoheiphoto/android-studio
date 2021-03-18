package com.example.practice_onsei;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer1;
    MediaPlayer mPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPlayer1 != null) {
                    mPlayer1.release();
                }
                mPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.waterdrop1);
                mPlayer1.start();
            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPlayer2 != null) {
                    mPlayer2.release();
                }
                mPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.swordslash1);
                mPlayer2.start();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer1 != null) mPlayer1.release();
        if (mPlayer2 != null) mPlayer2.release();
    }
}