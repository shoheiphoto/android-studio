package com.example.practice_intentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menu1btn = (Button)findViewById(R.id.button);
        menu1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.editTextTextPersonName);
                String name = editText.getText().toString();
                EditText editText2 = (EditText)findViewById(R.id.editTextTextPersonName2);
                String mail = editText2.getText().toString();
                EditText editText3 = (EditText)findViewById(R.id.editTextTextPersonName3);
                String age = editText3.getText().toString();
                EditText editText4 = (EditText)findViewById(R.id.editTextTextPersonName4);
                String sum = editText4.getText().toString();
                EditText editText5 = (EditText)findViewById(R.id.editTextTextPersonName5);
                String num = editText5.getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra("名前", name);
                intent.putExtra("メール", mail);
                intent.putExtra("年齢", age);
                intent.putExtra("合計金額", sum);
                intent.putExtra("人数", num);

                startActivity(intent);
            }
        });
    }
}