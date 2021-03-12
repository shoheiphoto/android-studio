package com.example.practice_button_move2;

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






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.ID_BTN1);
        button1.setOnClickListener(new Button1ClickListener());;
        button1.setOnLongClickListener(new Button1LongClickListener());
        button1.setOnTouchListener(new Button1OnTouchListener());

        Button button2 = (Button)findViewById(R.id.ID_BTN2);
        button2.setOnClickListener(new Button2ClickListener());;
        button2.setOnLongClickListener(new Button2LongClickListener());
        button2.setOnTouchListener(new Button2OnTouchListener());

        Button button3 = (Button)findViewById(R.id.ID_BTN3);
        button3.setOnClickListener(new Button3ClickListener());;
        button3.setOnLongClickListener(new Button3LongClickListener());
        button3.setOnTouchListener(new Button3OnTouchListener());

        Button button4 = (Button)findViewById(R.id.ID_BTN4);
        button4.setOnClickListener(new Button4ClickListener());;
        button4.setOnLongClickListener(new Button4LongClickListener());
        button4.setOnTouchListener(new Button4OnTouchListener());

        Button button5 = (Button)findViewById(R.id.ID_BTN5);
        button5.setOnClickListener(new Button5ClickListener());;
//        button4.setOnLongClickListener(new Button4LongClickListener());

        ImageButton ib_midori = (ImageButton)findViewById(R.id.ID_MIDORI);
        ib_midori.setOnTouchListener(new MidoriTouchListener());;

        ImageButton ib_star = (ImageButton)findViewById(R.id.ID_Star);
        ib_star.setOnTouchListener(new StarTouchListener());;

    }


    class MidoriTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView) findViewById(R.id.Button01);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                txtview.setText("星だよ");
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                txtview.setText("ほんとだよ");
            }
            return false;
        }
    }



    class Button1ClickListener implements View.OnClickListener {
        public void onClick(View v) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("ひだり？");
            mlp.setMargins(mlp.leftMargin - 40, mlp.topMargin, mlp.rightMargin + 40, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);

            int num1 = new Random().nextInt(300) - 150;
            int num2 = new Random().nextInt(300) - 150;
            TextView txtview2 = (TextView) findViewById(R.id.Button01);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setText("うろうろ");
            mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
            txtview2.setLayoutParams(mlp2);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);

            if (isViewOverlapping(star, midori)){
                txtview.setTextColor(Color.GREEN);
                txtview2.setTextColor(Color.GREEN);
                txtview.setText("こんにちは");
                txtview2.setText("こんにちは");
            };
        }

        private boolean isViewOverlapping(View firstView, View secondView) {
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

    }


    class Button1OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("みぎ？");
            mlp.setMargins(mlp.leftMargin + 40, mlp.topMargin, mlp.rightMargin - 40, mlp.bottomMargin);
            txtview.setLayoutParams(mlp);

            int num1 = new Random().nextInt(300) - 150;
            int num2 = new Random().nextInt(300) - 150;
            TextView txtview2 = (TextView) findViewById(R.id.Button01);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setText("うろうろ");
            mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
            txtview2.setLayoutParams(mlp2);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);

            if (isViewOverlapping(star, midori)){
                txtview.setTextColor(Color.GREEN);
                txtview2.setTextColor(Color.GREEN);
                txtview.setText("こんにちは");
                txtview2.setText("こんにちは");
            };
        }



        private boolean isViewOverlapping(View firstView, View secondView) {
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

    }


    class Button2OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("うえ？");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin - 40, mlp.rightMargin, mlp.bottomMargin + 40);
            txtview.setLayoutParams(mlp);

            int num1 = new Random().nextInt(300) - 150;
            int num2 = new Random().nextInt(300) - 150;
            TextView txtview2 = (TextView) findViewById(R.id.Button01);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setText("うろうろ");
            mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
            txtview2.setLayoutParams(mlp2);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);

            if (isViewOverlapping(star, midori)){
                txtview.setTextColor(Color.GREEN);
                txtview2.setTextColor(Color.GREEN);
                txtview.setText("こんにちは");
                txtview2.setText("こんにちは");
            };
        }

        private boolean isViewOverlapping(View firstView, View secondView) {
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

    }


    class Button3OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("した？");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin + 40, mlp.rightMargin, mlp.bottomMargin - 40);
            txtview.setLayoutParams(mlp);

            int num1 = new Random().nextInt(300) - 150;
            int num2 = new Random().nextInt(300) - 150;
            TextView txtview2 = (TextView) findViewById(R.id.Button01);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setText("うろうろ");
            mlp2.setMargins(mlp2.leftMargin + num1, mlp2.topMargin + num2, mlp2.rightMargin - num1, mlp2.bottomMargin - num2);
            txtview2.setLayoutParams(mlp2);

            txtview.setTextColor(Color.BLACK);
            txtview2.setTextColor(Color.BLACK);

            View star = (View)findViewById(R.id.ID_Star);
            View midori = (View)findViewById(R.id.ID_MIDORI);

            if (isViewOverlapping(star, midori)){
                txtview.setTextColor(Color.GREEN);
                txtview2.setTextColor(Color.GREEN);
                txtview.setText("こんにちは");
                txtview2.setText("こんにちは");
            };
        }

        private boolean isViewOverlapping(View firstView, View secondView) {
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

    }


    class Button4OnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView) findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setTextColor(Color.BLACK);
            txtview.setText("かえりました");
            mlp.setMargins(mlp.leftMargin = 0, mlp.topMargin = 0, mlp.rightMargin = 0, mlp.bottomMargin = 0);
            txtview.setLayoutParams(mlp);

            TextView txtview2 = (TextView) findViewById(R.id.Button01);
            ViewGroup.LayoutParams lp2 = txtview2.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp2 = (ViewGroup.MarginLayoutParams)lp2;
            txtview2.setTextColor(Color.BLACK);
            txtview2.setText("おかえり");
            mlp2.setMargins(mlp2.leftMargin = 0, mlp2.topMargin = 0, mlp2.rightMargin = 0, mlp2.bottomMargin = 0);
            txtview2.setLayoutParams(mlp2);
        }
    }





    class Button1LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
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
            TextView txtview = (TextView)findViewById(R.id.ID_TXTVIEW);
            ViewGroup.LayoutParams lp = txtview.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)lp;
            txtview.setText("した！");
            mlp.setMargins(mlp.leftMargin, mlp.topMargin + 120, mlp.rightMargin, mlp.bottomMargin - 120);
            txtview.setLayoutParams(mlp);
            return false;
        }
    }




}