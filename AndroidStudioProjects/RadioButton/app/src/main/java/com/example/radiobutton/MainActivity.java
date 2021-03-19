package com.example.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRadioButtonClick(View v) {
    }

    public void onClearClick(View v) {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        radioGroup.clearCheck();
    }

    public void onGetCheckClick(View v) {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();
        Toast toast;
        switch (id) {
            case R.id.radio_yes:
                toast = Toast.makeText(MainActivity.this,"YES",Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.radio_no:
                toast = Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_SHORT);
                toast.show();
                break;
            default:
                toast = Toast.makeText(MainActivity.this,"NotSelected",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}