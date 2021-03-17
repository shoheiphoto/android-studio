package com.example.practice_myview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class MyView extends View {

    Ball bl1 = new Ball(100,100, 20, 15);
    Ball bl2 = new Ball(220,220, 20, 15);
    Ball bl3 = new Ball(340,340, 20, 15);
    Ball bl4 = new Ball(460,460, 20, 15);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255,89,116,62));

        bl1.Draw(canvas);
        bl2.Draw(canvas);
        bl3.Draw(canvas);
        bl4.Draw(canvas);

        bl1.Move();
        bl2.Move();
        bl3.Move();
        bl4.Move();

        bl1.Check(this.getWidth(), this.getHeight());
        bl2.Check(this.getWidth(), this.getHeight());
        bl3.Check(this.getWidth(), this.getHeight());
        bl4.Check(this.getWidth(), this.getHeight());


        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,R.mipmap.ic_launcher);
        Paint p = new Paint();
        canvas.drawBitmap(bitmap,500,500,p);




        invalidate();
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

