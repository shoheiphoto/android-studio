package com.example.practicesharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText)findViewById(R.id.editText2);

        Button btnSave = (Button)findViewById(R.id.button1);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.length() == 0 || ed2.length() == 0) {
                    Toast.makeText(MainActivity.this, "入力値を確認してください", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sp = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ex_name", ed1.getText().toString());
                    editor.putString("ex_address", ed2.getText().toString());
                    editor.apply();
                }

            }
        });

        Button btnClear = (Button)findViewById(R.id.button2);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("");
                ed2.setText("");
            }
        });

        Button btnRead = (Button)findViewById(R.id.button3);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                String ex_name = sp.getString("ex_name","");
                String ex_address = sp.getString("ex_address","");
                ed1.setText(ex_name);
                ed2.setText(ex_address);
            }
        });
    }
}