package com.example.sensor_jiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;

    float[] accell = new float[3];  //加速度センサー、0:X,1:Y,2:Z
    float[] geo = new float[3];     //地磁気センサー、0:X,1:Y,2:Z

    private TextView[] mSensor = new TextView[3];   //テキストビュー

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* システムからセンサーマネージャーの取得 */
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        /* 各ビューのインスタンスを取得 */
        mSensor[0] = (TextView) findViewById(R.id.textView2);
        mSensor[1] = (TextView) findViewById(R.id.textView4);
        mSensor[2] = (TextView) findViewById(R.id.textView6);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /* センサーマネージャーからTYPE_ALLですべてのセンサー管理インスタンスを取得 */
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        /* すべてのセンサーを読出して登録 */
        for (Sensor sensor : sensors) {
            /* 取得したセンサーが加速度センサーのときは */
            if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                /* ディレイ設定等を登録 */
                mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            }

            /* 取得したセンサーが地磁気センサーのときは */
            if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                /* ディレイ設定等を登録 */
                mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            }

            /* それ以外のときは登録無し */
        }

        /* 加速度センサーの管理インスタンスを取得 */
        Sensor acceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        /* 加速度センサーの登録 */
        mSensorManager.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        /* 使っていないときはセンサーの登録を解除 */
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    /* センサーの値に変更があった場合に呼ばれるメソッド */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] mori = new float[3];
        float[] inR = new float[16];
        float[] outR = new float[16];
        float[] I = new float[16];

        /* センサー値の変更イベントが発生したセンサーごとに場合わけ */
        switch (event.sensor.getType()) {
            /* 地磁気センサーのときは */
            case Sensor.TYPE_MAGNETIC_FIELD:
                geo = event.values.clone(); //値を取得して配列geoに保存

                /* ログキャットに表示 */
                Log.i("DAT", "Gravity[X]=" + geo[0]);
                Log.i("DAT", "Gravity[Y]=" + geo[1]);
                Log.i("DAT", "Gravity[Z]=" + geo[2]);
                break;

            /* 加速度センサーのときは */
            case Sensor.TYPE_ACCELEROMETER:
                accell = event.values.clone();  // 値を取得して配列accellに保存

                /* ログキャットに表示 */
                Log.i("DAT", "accell[X]=" + accell[0]);
                Log.i("DAT", "accell[Y]=" + accell[1]);
                Log.i("DAT", "accell[Z]=" + accell[2]);
                break;

            /* テキストサンプルに追加。一応Default(上記以外は) */
            default:
                //何もしない
                break;
        }

        /* もし加速度センサーと地磁気センサーの値が取得できていたら */
        if (geo != null && accell != null) {
            /* 加速度センサーと地磁気センサーの値から方位を求める */
            SensorManager.getRotationMatrix(inR, I, accell, geo);
            SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
            SensorManager.getOrientation(outR, mori);

            /* 求めた方位角（ラジアン）を度数（ディグリー）に変換しTextViewに表示 */
            mSensor[0].setText(String.valueOf(radianToDegree(mori[0])));    //方向（北が0度）
            mSensor[1].setText(String.valueOf(radianToDegree(mori[1])));    // ロール
            mSensor[2].setText(String.valueOf(radianToDegree(mori[2])));    //ピッチ
        }
    }

    /* 角度をラジアン単位から度数（ディグリー）単位に変換する自作メソッド */
    int radianToDegree(float rad) {
        /* Mathクラスのメソッドをつかって変換 */
        return (int) Math.floor(Math.toDegrees(rad));
    }

    /* Sensorの精度に変更があった場合に呼ばれる */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


