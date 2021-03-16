package com.example.practice_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> animals = new ArrayList<String>();
        animals.add("いぬ");
        animals.add("ねこ");
        animals.add("ひつじ");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // レイアウトファイルに作成したSpinnerに登録
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);    // 上記で作成した選択肢を登録
        spinner2.setSelection(1);    // 初期選択値はリストの0番に設定 →　試しに1番に変更

        Spinner spinner1 = (Spinner)findViewById(R.id.spinner);
        spinner1.setSelection(1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected (AdapterView < ? > parent, View view, int position, long id){
                   String animal = parent.getItemAtPosition(position).toString();

                   Toast toast = Toast.makeText(MainActivity.this, animal, Toast.LENGTH_SHORT);
                   toast.show();
               }
               @Override
               public void onNothingSelected (AdapterView < ? > parent){
                   Toast toast = null;
                   toast = Toast.makeText(MainActivity.this, "選択してください", Toast.LENGTH_SHORT);
                   toast.show();
               }
           }
        );

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animal = spinner1.getSelectedItem().toString();
                int position = spinner1.getSelectedItemPosition();

                String msg = Integer.toString(position) + " : " + animal;
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText(msg);
            }
        });

    }


}