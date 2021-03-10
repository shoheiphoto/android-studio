package com.example.practice_button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
                        txtview.setText("左");
                    }
                }
                );

        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
                        txtview.setText("右");
                    }
                });
    }
}