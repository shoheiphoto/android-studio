package com.example.log_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public  boolean onTouchEvent (MotionEvent event) {
        Log.d("TouchEvent", "X:" + event.getX() + ", Y:" + event.getX());
        Log.d("TouchEvent", "Time:" + event.getEventTime() + ", Press:" + event.getPressure());
        Log.d("TouchEvent", "Edge:" + event.getEdgeFlags());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                break;
        }
        return true;
    }
}