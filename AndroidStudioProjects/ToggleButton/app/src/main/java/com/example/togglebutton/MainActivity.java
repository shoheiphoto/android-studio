package com.example.togglebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ToggleButton toggle = (ToggleButton)findViewById(R.id.toggleButton2);
        final TextView textView = (TextView)findViewById(R.id.textView);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton toggle = (ToggleButton)v;
                if (toggle.isChecked()) {
                    Log.d("TGL", "トグルボタンON");
                    textView.setText("トグルボタンON");
                } else {
                    Log.d("TGL", "トグルボタンOFF");
                    textView.setText("トグルボタンOFF");
                }
            }
        });

    }
}