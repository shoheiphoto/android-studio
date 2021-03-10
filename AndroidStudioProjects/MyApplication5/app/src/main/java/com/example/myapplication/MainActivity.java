package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView numberText;    // 入力値
    TextView displayText;    // 入力・計算結果の表示
    /* 計算用変数 */
    String number1 = "0";     // 一つ目の数字
    String number2 = "0";     // 二つ目の数字
    String cal = "";    // 四則演算の演算子 +, -, *

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberText = (TextView)findViewById(R.id.inputNum2);
        displayText = (TextView)findViewById(R.id.inputNum);

        Button btnBS = (Button)findViewById(R.id.button_BS);
        Button btnCE = (Button)findViewById(R.id.button_CE);
        Button btnC = (Button)findViewById(R.id.button_C);

        Button btn1 = (Button)findViewById(R.id.button_1);
        Button btn2 = (Button)findViewById(R.id.button_2);
        Button btn3 = (Button)findViewById(R.id.button_3);
        Button btn4 = (Button)findViewById(R.id.button_4);
        Button btn5 = (Button)findViewById(R.id.button_5);
        Button btn6 = (Button)findViewById(R.id.button_6);
        Button btn7 = (Button)findViewById(R.id.button_7);
        Button btn8 = (Button)findViewById(R.id.button_8);
        Button btn9 = (Button)findViewById(R.id.button_9);
        Button btn0 = (Button)findViewById(R.id.button_0);

        Button btnDot = (Button) findViewById(R.id.button_Dot);

        Button btnMinus = (Button)findViewById(R.id.button_Minus);
        Button btnPlus = (Button)findViewById(R.id.button_Plus);
        Button btnMul = (Button)findViewById(R.id.button_Kakeru);
        Button btnEql = (Button)findViewById(R.id.button_Equal);

        /****************************************
         * 電卓用のプログラム
         * 2つの数字の計算のみ対応
         ****************************************/


        /* 一文字削除 BackSpace */
        btnBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = numberText.getText().toString();

                if ( num != null && num.length() > 0){
                    num = num.substring(0,num.length() -1);
                }

                numberText.setText(num);
            }
        });

        /*  入力した数字の削除 Clear Entry */
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberText.setText("");
            }
        });

        /* 計算式の削除　Clear */
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1 = "0";
                number2 = "0";
                cal = "";
                numberText.setText("");
            }
        });

        /*************
         * 数字の入力
         *************/
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "1";
                numberText.setText(tmp);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "2";
                numberText.setText(tmp);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "3";
                numberText.setText(tmp);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "4";
                numberText.setText(tmp);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "5";
                numberText.setText(tmp);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "6";
                numberText.setText(tmp);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "7";
                numberText.setText(tmp);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "8";
                numberText.setText(tmp);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "9";
                numberText.setText(tmp);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += "0";
                numberText.setText(tmp);
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = numberText.getText().toString();
                tmp += ".";
                numberText.setText(tmp);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1 = numberText.getText().toString();
                cal = "-";
                numberText.setText("");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1 = numberText.getText().toString();
                cal = "+";
                numberText.setText("");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number1 = numberText.getText().toString();
                cal = "*";
                numberText.setText("");
            }
        });

        btnEql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double answer = 0;
                number2 = numberText.getText().toString();

                if(number1 != "" && number2 != "" && cal != ""){
                    switch (cal) {
                        case "+":
                            answer = Double.parseDouble(number1) + Double.parseDouble(number2);
                            displayText.setText(number1 + " + " + number2 + " = ");
                            break;
                        case "-":
                            answer = Double.parseDouble(number1) - Double.parseDouble(number2);
                            displayText.setText(number1 + " - " + number2 + " = ");
                            break;
                        case "*":
                            answer = Double.parseDouble(number1) * Double.parseDouble(number2);
                            displayText.setText(number1 + " × " + number2 + " = ");
                            break;
                        default:
                            break;
                    }
                } else {

                }
                numberText.setText(Double.toString(answer));

                number1 = "0";
                number2 = "0";
                cal = "";
            }
        });




    }
}
