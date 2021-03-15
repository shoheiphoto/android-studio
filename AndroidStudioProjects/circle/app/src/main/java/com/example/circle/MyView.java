package com.example.circle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    Ball bl1 = new Ball(100,100);
    Ball bl2 = new Ball(100,200, -10,50);


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.argb(255,89,116,62));

        bl1.Draw(canvas);
        bl2.Draw(canvas);

        bl1.Move();
        bl2.Move();

        bl1.Check(this.getWidth(),this.getHeight());
        bl2.Check(this.getWidth(),this.getHeight());

        invalidate();
    }

    public  MyView(Context context) {
        super(context);
    }
}

class Ball {
    int mX, mY;
    int mVX, mVY;

    public Ball() {
        mX = 0;
        mY = 0;
        mVX = 1;
        mVY = 1;
    }

    public Ball (int x, int y) {
        this();
        mX = x;
        mY = y;
    }

    public Ball (int x, int y, int mvx, int mvy) {
        this(x,y);
        mVX = mvx;
        mVY = mvy;
    }

    void Draw (Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(mX,mY,100, paint);;
    }

    void Move() {
        mX += mVX;
        mY += mVY;
    }

    void Check(int width, int height) {
        if (mX > width) {
            mVX = -mVX;
        } else if (mX < 0) {
            mVX = -mVX;
        }
        if (mY > height) {
            mVY = -mVY;
        } else if (mY < 0) {
            mVY = -mVY;
        }
    }

}
