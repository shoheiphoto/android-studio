package com.example.practice_mybutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {

    float x=100,y=100;  // 円の中心座標（X,Y)

    /*==================================*==================================
        コンストラクタ
         レイアウトファイルで使用する場合は、以下の３つとも必要
    *==================================*==================================*/
    public MyButton(Context context) {
        super(context);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* ビューの描画時に呼ばれるメソッド */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /* Paintクラスのインスタンスを作成 */
        Paint paint = new Paint();

        /* paint（ペン）の色をしろに設定 */
        paint.setColor(Color.WHITE);

        /* canvasにpaintで円を描画 */
        canvas.drawCircle(x,y,100,paint);
    }

    /*  メンバ変数ｘ（描画する円の中心X座標）を設定するセッター */
    public void setPosx(float mx){
        x = mx;
    }

    /*  メンバ変数ｙ（描画する円の中心Y座標）を設定するセッター */
    public void setPosy(float my){
        y = my;
    }

}
