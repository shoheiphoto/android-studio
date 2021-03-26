package com.example.sensor_accell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor acceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] accell = new float[3];
        accell[0] = event.values[0];
        accell[1] = event.values[1];
        accell[2] = event.values[2];
        Log.i("DAT","accell[X]" + accell[0]);
        Log.i("DAT","accell[Y]" + accell[1]);
        Log.i("DAT","accell[X]" + accell[2]);

        TextView textViewX = (TextView)findViewById(R.id.textView2);
        TextView textViewY = (TextView)findViewById(R.id.textView4);
        TextView textViewZ = (TextView)findViewById(R.id.textView6);
        textViewX.setText(Float.toString(accell[0]));
        textViewY.setText(Float.toString(accell[1]));
        textViewZ.setText(Float.toString(accell[2]));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}