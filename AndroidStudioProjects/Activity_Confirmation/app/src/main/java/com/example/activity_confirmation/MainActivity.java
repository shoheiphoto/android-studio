package com.example.activity_confirmation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"onCreate", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this,"onRestart",Toast.LENGTH_SHORT).show();
    }
    @Override protected void onDestroy() {
        Toast.makeText(MainActivity.this,"onDestroy",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    @Override protected void onStop() {
        Toast.makeText(MainActivity.this,"onStop",Toast.LENGTH_SHORT).show();
        super.onStop();
    }
    @Override protected void onPause() {
        Toast.makeText(MainActivity.this,"onPause",Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override protected void onResume() {
        Toast.makeText(MainActivity.this,"onResume",Toast.LENGTH_SHORT).show();
        super.onResume();
    }
    @Override protected void onStart() {
        super.onStart(); Toast.makeText(MainActivity.this,"onStart",Toast.LENGTH_SHORT).show();
    }
}