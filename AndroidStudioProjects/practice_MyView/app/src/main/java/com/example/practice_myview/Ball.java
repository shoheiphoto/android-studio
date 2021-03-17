package com.example.practice_myview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    int mX, mY;
    int mVX, mVY;

    public Ball () {
        mX = 0; mY = 0;
        mVX = 1; mVY = 1;
    }

    public Ball(int x, int y) {
        this();
        mX = x; mY = y;
    }

    public Ball(int x, int y, int mvx, int mvy) {
        this(x,y);
        mVX = mvx; mVY = mvy;
    }

    void Draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor((Color.WHITE));
        canvas.drawCircle(mX,mY,100, paint);
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



    /* 途中変更用に作成したメソッド */

    /* 中心座標の変更 */
    public void setmXY(int mX, int mY) {
        this.mX = mX;
        this.mY = mY;
    }

    /* 変化量の変更メソッド */
//    public void setmVXY(int mVX, int mVY) {
//        this.mVX = mVX;
//        this.mVX = mVY;
//    }

    /* 変化量の変更メソッド */
    public void setmVXY(int mVX, int mVY) {
        this.mVX += mVX;
        this.mVX += mVY;
    }

}
