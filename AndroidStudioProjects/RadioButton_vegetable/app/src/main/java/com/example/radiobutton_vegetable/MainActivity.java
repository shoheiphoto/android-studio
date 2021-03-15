package com.example.radiobutton_vegetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CheckBox chkbox1;
    CheckBox chkbox2;
    CheckBox chkbox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkbox1 = (CheckBox)findViewById(R.id.checkBox);
        chkbox2 = (CheckBox)findViewById(R.id.checkBox2);
        chkbox3 = (CheckBox)findViewById(R.id.checkBox3);


    }

    public  void onGetCheck(View v) {
        String msg = "";
        if (chkbox1.isChecked() == true) {
            msg = msg + "たまねぎON";
        } else {
            msg = msg + "たまねぎOFF";
        }
        if (chkbox2.isChecked() == true) {
            msg = msg + "トマトON";
        } else {
            msg = msg + "トマトOFF";
        }
        if (chkbox3.isChecked() == true) {
            msg = msg + "ピーマンON";
        } else {
            msg = msg + "ピーマンOFF";
        }
        Toast toast;
        toast = Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClearCheck(View v) {
        chkbox1.setChecked(false);
        chkbox2.setChecked(false);
        chkbox3.setChecked(false);
    }
}