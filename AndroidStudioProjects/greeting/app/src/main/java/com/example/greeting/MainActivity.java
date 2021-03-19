package com.example.greeting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num = 0;
    int num2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(new Button1ClickListener());
        button1.setOnLongClickListener(new Button1LongClickListener());
        button1.setOnTouchListener(new Button1OnTouchListener());

        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(new Button2ClickListener());
        button2.setOnLongClickListener(new Button2LongClickListener());
        button2.setOnTouchListener(new Button2OnTouchListener());

        Button button3 = (Button)findViewById(R.id.ID_BTN3);
        button3.setOnClickListener(new Button3ClickListener());
        button3.setOnLongClickListener(new Button3LongClickListener());
        button3.setOnTouchListener(new Button3OnTouchListener());

        Button button4 = (Button)findViewById(R.id.ID_BTN4);
        button4.setOnClickListener(new Button4ClickListener());
        button4.setOnLongClickListener(new Button4LongClickListener());
        button4.setOnTouchListener(new Button4OnTouchListener());

        Button button5 = (Button)findViewById(R.id.ID_BTN5);
        button5.setOnClickListener(new Button5ClickListener());

        ImageButton ib_midori = (ImageButton)findViewById(R.id.ID_MIDORI);
        ib_midori.setOnTouchListener(new MidoriTouchListener());

        ImageButton ib_star = (ImageButton)findViewById(R.id.ID_Star);
        ib_star.setOnTouchListener(new StarTouchListener());

        Button button_big = (Button)findViewById(R.id.ID_Big);
        button_big.setOnClickListener(new ButtonBigClickListener());

        Button button_small = (Button)findViewById(R.id.ID_Small);
        button_small.setOnClickListener(new ButtonSmallClickListener());

    }


    protected boolean isViewOverlapping(View firstView, View secondView) {
        int[] firstPosition = new int[2];
        int[] secondPosition = new int[2];

        firstView.getLocationOnScreen(firstPosition);
        secondView.getLocationOnScreen(secondPosition);

        // Rect constructor parameters: left, top, right, bottom
        Rect rectFirstView = new Rect(firstPosition[0], firstPosition[1],
                firstPosition[0] + firstView.getMeasuredWidth(), firstPosition[1] + firstView.getMeasuredHeight());
        Rect rectSecondView = new Rect(secondPosition[0], secondPosition[1],
                secondPosition[0] + secondView.getMeasuredWidth(), secondPosition[1] + secondView.getMeasuredHeight());
        return rectFirstView.intersect(rectSecondView);
    }


    protected void greeting(TextView txtview1, TextView txtview2) {
        txtview1.setTextColor(Color.GREEN);
        txtview2.setTextColor(Color.GREEN);
        txtview1.setText("こんにちは");
        txtview2.setText("こんにちは");

        TextView count = (TextView)findViewById(R.id.aisatsu_count);
        num += 1;
        count.setText(String.valueOf(num));
    }

    protected void onigokko(TextView txtview1, TextView txtview3) {
        txtview1.setTextColor(Color.rgb(128,0,0));
        txtview3.setTextColor(Color.rgb(128,0,0));
        txtview1.setText("つかまえた");
        txtview3.setText("つかまった");

        TextView count = (TextView)findViewById(R.id.onigokko_count);
        num2 += 1;
        count.setText(String.valueOf(num2));
    }

    protected void zonbigokko(TextView txtview1, TextView txtview4) {
        txtview1.setTextColor(Color.RED);
        txtview4.setTextColor(Color.RED);
        txtview1.setText("かまないでー");
        txtview4.setText("かんでいい？");
    }


    protected void starMove(TextView txtview2) {
        int num1 = new Random().nextInt(300) - 150;
        int num2 = new Random().nextInt(300) - 150;
        ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
        txtview2.setText("うろうろ");
        mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
        txtview2.setLayoutParams(mlp2);
    }

    protected void batsuMove(TextView txtview3) {
        int num1 = new Random().nextInt(400) - 200;
        int num2 = new Random().nextInt(400) - 200;
        ViewGroup.LayoutParams lp2 = txtview3.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
        txtview3.setText("こっちだよー");
        mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
        txtview3.setLayoutParams(mlp2);
    }

    protected void zonbiMove(TextView txtview4) {
        int num1 = new Random().nextInt(300) - 150;
        int num2 = new Random().nextInt(300) - 150;
        ViewGroup.LayoutParams lp2 = txtview4.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
        txtview4.setText("うー");
        mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
        txtview4.setLayoutParams(mlp2);
    }






    class MidoriTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setText("ごはん？");
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setText("あそんできます");
            }
            return false;
        }
    }

    class StarTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextStar);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setText("ほしだよ");
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setText("ほんとだよ");
            }
            return false;
        }
    }

    class ButtonBigClickListener implements View.OnClickListener {
        public void onClick(View v) {
            View midori = (View)findViewById(R.id.ID_MIDORI);
            ViewGroup.LayoutParams midoriParams = midori.getLayoutParams();
            midoriParams.height *= 1.2;
            midoriParams.width *= 1.2;
            midori.setLayoutParams(midoriParams);
        }
    }

    class ButtonSmallClickListener implements View.OnClickListener {
        public void onClick(View v) {
            View midori = (View)findViewById(R.id.ID_MIDORI);
            ViewGroup.LayoutParams midoriParams = midori.getLayoutParams();
            midoriParams.height *= 0.8;
            midoriParams.width *= 0.8;
            midori.setLayoutParams(midoriParams);
        }
    }



    class Button1ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("ひだり？");
            mlp.setMargins(mlp.leftMargin - 40, mlp.topMargin, mlp.rightMargin + 40, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.ID_TextStar);
            TextView txtview3 = (TextView) findViewById(R.id.ID_TextBatsu);
            TextView txtview4 = (TextView) findViewById(R.id.ID_TextZonbi);
            starMove(txtview2);
            batsuMove(txtview3);
            zonbiMove(txtview4);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);
            txtview3.setTextColor(Color.BLACK);
            txtview4.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);
            View batsu = (View)findViewById(R.id.ID_Batsu);
            View zonbi = (View)findViewById(R.id.ID_Zonbi);

            if (isViewOverlapping(star, midori)){
                greeting(txtview,txtview2);
            };
            if (isViewOverlapping(batsu, midori)){
                onigokko(txtview,txtview3);
            };
            if (isViewOverlapping(zonbi, midori)){
                zonbigokko(txtview,txtview4);
            };
        }

    }


    class Button1OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setTextColor(Color.BLUE);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setTextColor(Color.BLACK);
            }
            return false;
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("みぎ？");
            mlp.setMargins(mlp.leftMargin + 40, mlp.topMargin, mlp.rightMargin - 40, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.ID_TextStar);
            TextView txtview3 = (TextView) findViewById(R.id.ID_TextBatsu);
            TextView txtview4 = (TextView) findViewById(R.id.ID_TextZonbi);
            starMove(txtview2);
            batsuMove(txtview3);
            zonbiMove(txtview4);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);
            txtview3.setTextColor(Color.BLACK);
            txtview4.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);
            View batsu = (View)findViewById(R.id.ID_Batsu);
            View zonbi = (View)findViewById(R.id.ID_Zonbi);

            if (isViewOverlapping(star, midori)){
                greeting(txtview,txtview2);
            };
            if (isViewOverlapping(batsu, midori)){
                onigokko(txtview,txtview3);
            };
            if (isViewOverlapping(zonbi, midori)){
                zonbigokko(txtview,txtview4);
            };
        }





    }


    class Button2OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setTextColor(Color.BLUE);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setTextColor(Color.BLACK);
            }
            return false;
        }
    }

    class Button3ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("うえ？");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin - 40, mlp.rightMargin, mlp.bottomMargin + 40);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.ID_TextStar);
            TextView txtview3 = (TextView) findViewById(R.id.ID_TextBatsu);
            TextView txtview4 = (TextView) findViewById(R.id.ID_TextZonbi);
            starMove(txtview2);
            batsuMove(txtview3);
            zonbiMove(txtview4);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);
            txtview3.setTextColor(Color.BLACK);
            txtview4.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);
            View batsu = (View)findViewById(R.id.ID_Batsu);
            View zonbi = (View)findViewById(R.id.ID_Zonbi);

            if (isViewOverlapping(star, midori)){
                greeting(txtview,txtview2);
            };
            if (isViewOverlapping(batsu, midori)){
                onigokko(txtview,txtview3);
            };
            if (isViewOverlapping(zonbi, midori)){
                zonbigokko(txtview,txtview4);
            };
        }



    }


    class Button3OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setTextColor(Color.BLUE);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setTextColor(Color.BLACK);
            }
            return false;
        }
    }


    class Button4ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("した？");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin + 40, mlp.rightMargin, mlp.bottomMargin - 40);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.ID_TextStar);
            TextView txtview3 = (TextView) findViewById(R.id.ID_TextBatsu);
            TextView txtview4 = (TextView) findViewById(R.id.ID_TextZonbi);
            starMove(txtview2);
            batsuMove(txtview3);
            zonbiMove(txtview4);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);
            txtview3.setTextColor(Color.BLACK);
            txtview4.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);
            View batsu = (View)findViewById(R.id.ID_Batsu);
            View zonbi = (View)findViewById(R.id.ID_Zonbi);

            if (isViewOverlapping(star, midori)){
                greeting(txtview,txtview2);
            };
            if (isViewOverlapping(batsu, midori)){
                onigokko(txtview,txtview3);
            };
            if (isViewOverlapping(zonbi, midori)){
                zonbigokko(txtview,txtview4);
            };
        }
    }


    class Button4OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setTextColor(Color.BLUE);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setTextColor(Color.BLACK);
            }
            return false;
        }
    }


    class Button5ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView) findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setTextColor(Color.BLACK);
            txtview.setText("かえりました");
            mlp.setMargins(mlp.leftMargin = 0, mlp.topMargin = 0, mlp.rightMargin = 0, mlp.bottomMargin = 0);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.ID_TextStar);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setTextColor(Color.BLACK);
            txtview2.setText("おかえり");
            mlp2.setMargins(mlp2.leftMargin = 0, mlp2.topMargin = 0, mlp2.rightMargin = 0, mlp2.bottomMargin = 0);
            txtview2.setLayoutParams(mlp2);

            TextView txtview3 = (TextView) findViewById(R.id.ID_TextBatsu);
            ViewGroup.LayoutParams lp3 = txtview3.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp3 = (ViewGroup.MarginLayoutParams)lp3;
            txtview3.setTextColor(Color.BLACK);
            txtview3.setText("またあそぼうね");
            mlp3.setMargins(mlp3.leftMargin = 0, mlp3.topMargin = 0, mlp3.rightMargin = 0, mlp3.bottomMargin = 0);
            txtview3.setLayoutParams(mlp3);

            TextView txtview4 = (TextView) findViewById(R.id.ID_TextZonbi);
            ViewGroup.LayoutParams lp4 = txtview4.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp4 = (ViewGroup.MarginLayoutParams)lp4;
            txtview4.setTextColor(Color.BLACK);
            txtview4.setText("ぼくはぞんび…");
            mlp4.setMargins(mlp4.leftMargin = 0, mlp4.topMargin = 0, mlp4.rightMargin = 0, mlp4.bottomMargin = 0);
            txtview4.setLayoutParams(mlp4);

            View midori = (View)findViewById(R.id.ID_MIDORI);
            ViewGroup.LayoutParams midoriParams = midori.getLayoutParams();
            midoriParams.height = 120;
            midoriParams.width = 120;
            midori.setLayoutParams(midoriParams);

            TextView count = (TextView)findViewById(R.id.aisatsu_count);
            TextView count2 = (TextView)findViewById(R.id.onigokko_count);
            num = 0;
            count.setText(String.valueOf(num));
            count2.setText(String.valueOf(num));
        }
    }





    class Button1LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("ひだり！");
            mlp.setMargins(mlp.leftMargin - 120, mlp.topMargin, mlp.rightMargin + 120, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);
            return false;
        }
    }

    class Button2LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("みぎ！");
            mlp.setMargins(mlp.leftMargin + 120, mlp.topMargin, mlp.rightMargin - 120, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);
            return false;
        }
    }

    class Button3LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("うえ！");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin - 120, mlp.rightMargin, mlp.bottomMargin + 120);
            txtview.setLayoutParams(mlp);
            return false;
        }
    }

    class Button4LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TextMidori);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("した！");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin + 120, mlp.rightMargin, mlp.bottomMargin - 120);
            txtview.setLayoutParams(mlp);
            return false;
        }
    }




}