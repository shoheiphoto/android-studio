package com.example.event_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tview = (TextView)findViewById(R.id.ID_TVIEW);
        tview.setText("イベント検出");

        TextView tview2 = (TextView)findViewById(R.id.ID_TVIEW2);
        tview2.setText("onCreate発生");

        CheckBox chkbox1 = (CheckBox)findViewById(R.id.ID_CHKBOX1);
        chkbox1.setText("りんご");
        chkbox1.setChecked(false);
        CheckBox chkbox2 = (CheckBox)findViewById(R.id.ID_CHKBOX2);
        chkbox2.setText("みかん");
        chkbox2.setChecked(false);
    }
}