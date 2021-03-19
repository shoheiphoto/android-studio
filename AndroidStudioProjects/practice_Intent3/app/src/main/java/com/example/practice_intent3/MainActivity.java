package com.example.practice_intent3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menu1btn = (Button)findViewById(R.id.button);
        menu1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.editTextNumber1);
                int mVX = Integer.parseInt(editText.getText().toString());
                EditText editText2 = (EditText)findViewById(R.id.editTextNumber2);
                int mVY = Integer.parseInt(editText2.getText().toString());

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra("x軸", mVX);
                intent.putExtra("y軸", mVY);

                startActivity(intent);
            }
        });
    }
}