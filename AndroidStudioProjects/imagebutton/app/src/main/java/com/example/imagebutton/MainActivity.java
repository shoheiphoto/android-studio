package com.example.imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCopyClick(View v) {
        ImageView imageview = (ImageView)findViewById(R.id.imageView);
        if (Boolean.valueOf(imageview.getTag().toString()) == true) {
            imageview.setImageResource(R.drawable.pin001);
            imageview.setTag(false);
        } else {
            imageview.setImageResource(R.drawable.pin002);
            imageview.setTag(true);
        }
    }
}