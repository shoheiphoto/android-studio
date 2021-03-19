package com.example.radiobutton_vegetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
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
            }
        });

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