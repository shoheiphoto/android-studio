package com.example.practice_intent3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Ball bl1 = new Ball(100,100, 20, 15);
    Ball bl2 = new Ball(220,220, 20, 15);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255,89,116,62));

        bl1.Draw(canvas);
        bl2.Draw(canvas);

        bl1.Move();
        bl2.Move();

        bl1.Check(this.getWidth(), this.getHeight());
        bl2.Check(this.getWidth(), this.getHeight());


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

