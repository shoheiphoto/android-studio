package com.example.practicebackground;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service implements Runnable{
    Boolean flag = false;
    @Override
    public void run() {
        MediaPlayer mPlayer;
        mPlayer = MediaPlayer.create(this, R.raw.swordslash1);
        while (flag) {
            mPlayer.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.i("Service","onDestroy");
        Toast.makeText(this, "MyService#onDestroy", Toast.LENGTH_SHORT).show();
        flag = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i ("Service", "onStartCommand");
        Toast.makeText(this, "MyService#onStartCommand", Toast.LENGTH_SHORT).show();

        flag = true;
        Thread th = new Thread(this);
        th.start();

        return START_STICKY;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Service","onCreate");
        Toast.makeText(this,"MyService#onCreate",Toast.LENGTH_SHORT).show();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}