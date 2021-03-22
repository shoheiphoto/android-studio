package com.example.practicetimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn1, btn2, btn3;
    Timer timer;
    Handler mHandler = new Handler();
    int time = 0;
    ToneGenerator tone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tone = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
        /*-----------------------------------
                ToneGeneratorはトーン（簡単な音程）を生成し、音を鳴らすクラス
                コンストラクタ：ToneGenerator( int streamType, int volume)
             -----------------------------------*/
        textView = (TextView)findViewById(R.id.textview);
        btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                time = 0;
                timer = new Timer(false);  //新しくtimerインスタンスを生成
                // 引数は、　true  :デーモンスレッドとして動作
                //          false:ユーザスレッドとして動作（各自調べてみてください）

                /* timerインスタンスにdelay時間後の処理を指定 */
                /* timer.schedule(
                    TimerTask task(実行するタスク),
                    long delay(指定時間後にタスク実行),
                     long period(指定時間ごとにタスクを実行）*/
                timer.schedule(new TimerTask() {
                    /* このメソッドはdelayで指定した時間後にperiodミリ秒ごとに実行される */
                    @Override
                    public void run() {
                        time++;
                        tone.startTone(ToneGenerator.TONE_PROP_BEEP);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(Integer.toString(time) + "秒経過");
                            }
                        });
                    }
                }, 0,1000);  //delay:0(待ち時間なしでタスクを実行), period:1000(1000ms毎にタスクを繰り返す）
            }
        });

        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();  // timerのschedule実行をキャンセルする
            }
        });

        btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 0;
                textView.setText(Integer.toString(time) + "秒経過");
            }
        });

    }
}