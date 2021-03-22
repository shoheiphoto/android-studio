package com.example.practicecounddown;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timer,timer2;
    Button btn1,btn2,btn5m,btn3m,btn1m,btnStop;
    MyCountDownTimer count;
    MyCountDownTimer2 count2;
    ToneGenerator tone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView)findViewById(R.id.textview);
        btn1 = (Button)findViewById(R.id.button);
        tone = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = new MyCountDownTimer(10000,1000);
                count.start();
            }
        });

        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.cancel();
            }
        });

        timer2 = (TextView)findViewById(R.id.textview2);
        btn5m = (Button)findViewById(R.id.button5min);
        btn3m = (Button)findViewById(R.id.button3min);
        btn1m = (Button)findViewById(R.id.button1min);
        btnStop = (Button)findViewById(R.id.buttonStop);
        btn5m.setOnClickListener(new BtnClickListener());
        btn3m.setOnClickListener(new BtnClickListener());
        btn1m.setOnClickListener(new BtnClickListener());
        btnStop.setOnClickListener(new BtnClickListener());
    }

    private class BtnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button btn = (Button)v;
            switch (btn.getId()) {
                case R.id.button5min:
                    count2 = new MyCountDownTimer2(5*60000,1000);
                    count2.start();
                    break;
                case R.id.button3min:
                    count2 = new MyCountDownTimer2(3*60000,1000);
                    count2.start();
                    break;
                case R.id.button1min:
                    count2 = new MyCountDownTimer2(1*60000,1000);
                    count2.start();
                    break;
                case R.id.buttonStop:
                    count2.cancel();
                    break;
            }
        }
    }

    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer.setText("0");
            Toast.makeText(getApplicationContext(), "終了", Toast.LENGTH_LONG).show();
            tone.startTone(ToneGenerator.TONE_PROP_BEEP);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText(Long.toString(millisUntilFinished/1000/60) + ":" + Long.toString(millisUntilFinished/1000%60) + ":" + Long.toString(millisUntilFinished));
            tone.startTone(ToneGenerator.TONE_PROP_BEEP);
        }
    }

    private class MyCountDownTimer2 extends CountDownTimer {
        public MyCountDownTimer2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer2.setText("0");
            Toast.makeText(getApplicationContext(), "終了", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onTick(long millisUntilFinished) {
            timer2.setText(Long.toString(millisUntilFinished/1000/60) + ":" + Long.toString(millisUntilFinished/1000%60) + ":" + Long.toString(millisUntilFinished));
        }
    }
}