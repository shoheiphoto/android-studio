package com.example.tainaidokei;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    EditText editText;

    Button btn1, btn2, btn3;
    Timer timer;
    Handler mHandler = new Handler();
    int time, goalTime;
    ToneGenerator tone;
    boolean timeDisplay = true; //計測時間表示のON/OFF

    MediaPlayer mPlayer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tone = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
        /*-----------------------------------
                ToneGeneratorはトーン（簡単な音程）を生成し、音を鳴らすクラス
                コンストラクタ：ToneGenerator( int streamType, int volume)
             -----------------------------------*/
        textView1 = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        editText = (EditText)findViewById(R.id.editText);

        btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalTime = Integer.parseInt(editText.getText().toString());
                time = 0;
                timer = new Timer(true);  //新しくtimerインスタンスを生成
                timeDisplay = true; //計測時間を表示
                tone.startTone(ToneGenerator.TONE_PROP_BEEP);

                onResume();

                timer.schedule(new TimerTask() {
                    /* このメソッドはdelayで指定した時間後にperiodミリ秒ごとに実行される */
                    @Override
                    public void run() {
                        time++;
//                        tone.startTone(ToneGenerator.TONE_PROP_BEEP);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (timeDisplay == true) {
                                    textView1.setText(Integer.toString(time / 10) + "." + Integer.toString(time % 10) + "びょう");
                                    if (time / 10 > 5) {
                                        textView1.setText("");
                                    }
                                }
                            }
                        });
                    }
                }, 0,100);  //delay:0(待ち時間なしでタスクを実行), period:1000(1000ms毎にタスクを繰り返す）
            }
        });

        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tone.startTone(ToneGenerator.TONE_PROP_BEEP);
                timer.cancel();  // timerのschedule実行をキャンセルする
//                timeGosa = 10 - time;
                textView1.setText(Integer.toString(time / 10) + "." + Integer.toString(time % 10) + "びょう!");
                Double checktime = time / 10.0 - goalTime;
                textView2.setText(checktime.toString() + "びょうずれたよ");
            }
        });

        btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tone.startTone(ToneGenerator.TONE_PROP_BEEP);
                timer.cancel();  // timerのschedule実行をキャンセルする
                time = 0;

                textView1.setText("たのしかったね");
                textView2.setText("またちょうせんしてね");
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (mPlayer1 != null) {
            mPlayer1.release();
        }
        mPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.crrect_answer1);
        mPlayer1.start();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mPlayer1 != null) mPlayer1.release();
//    }
}