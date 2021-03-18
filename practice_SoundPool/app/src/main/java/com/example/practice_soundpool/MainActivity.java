package com.example.practice_soundpool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int SOUND_POOL_MAX = 6;
    SoundPool pool = buildSoundPool(SOUND_POOL_MAX);
    private int mId1, mId2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mId1 = pool.load(this, R.raw.swordslash1, 0);
        mId2 = pool.load(this, R.raw.swordslash1, 1);
        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pool.play(mId1,1,1,0,0,1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pool.play(mId2,1,1,0,0,1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        pool.release();
        super.onDestroy();
    }
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private  SoundPool buildSoundPool(int poolMax) {
        SoundPool pool = null;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            pool = new SoundPool(poolMax, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            pool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(poolMax)
                    .build();
        }
        return pool;
    }
}