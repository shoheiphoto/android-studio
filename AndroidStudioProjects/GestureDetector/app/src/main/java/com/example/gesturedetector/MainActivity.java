package com.example.gesturedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GestureDetector gesDetect;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gesDetect.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesDetect = new GestureDetector(this, onGestureListener);
    }
    private final GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public  boolean onDoubleTap(MotionEvent e) {
            Log.v("Gesture", "onDoubleTap");
            return super.onDoubleTap(e);
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.v("Gesture", "onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }
        @Override
        public boolean onDown(MotionEvent e) {
            Log.v("Gesture", "onDown");
            return super.onDown(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.v("Gesture", "onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            Log.v("Gesture", "onLongPress");
            super.onLongPress(e);
        }
        @Override
        public  boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.v("Gesture", "onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        @Override
        public void onShowPress(MotionEvent e) {
            Log.v("Gesture", "onShowPress");
            super.onShowPress(e);
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.v("Gesture", "onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.v("Gesture", "onSingleTapUp");
            return super.onSingleTapUp(e);
        }
    };
}