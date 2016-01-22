package com.example.hanxixun.androidtest_inwrite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: hanxixun
 * @time: 2016/1/22 8:21
 */
class MyView extends View {

    private Paint paint;
    private int x,y;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.RED);      //
        paint.setAntiAlias(true);

        x=100;
        y=100;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawLine(0,0,x,y,paint);
       // drawTest(canvas);

        //drawPath(canvas);

       // drawBitmap(canvas);
    }


    private void drawTest(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);         //设置画笔风格
        paint.setStrokeWidth(15);               //设置画笔的宽
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
        canvas.drawCircle(200, 100, 100, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(200, 100, 100, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 500, 300, 600, paint);
        //  canvas.drawRoundRect(100,700,200,800,10,10,paint);

    }

    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 300);
        path.lineTo(200, 250);

        paint.setColor(Color.RED);

        canvas.drawPath(path, paint);

        Path path1 = new Path();
        path1.addCircle(500, 500, 200, Path.Direction.CW);
        path1.addCircle(500, 500, 180, Path.Direction.CCW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);

        canvas.drawPath(path1, paint);

        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path1, paint);
    }

    private void drawBitmap(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

//         canvas.drawBitmap(bitmap,500,500,paint);
//
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.translate(getWidth() / 2, getHeight() / 2);      //坐标系
//        canvas.drawCircle(0, 0, 20, paint);
//        canvas.drawBitmap(bitmap, 0, 0, paint);

        canvas.save();
        paint.setStrokeWidth(15);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(0, 0, 20, paint);
        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, paint);
        canvas.drawLine(-getWidth() / 2, 0, getWidth(), 0, paint);

        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(90);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.drawCircle(200, 200, 50, paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        invalidate();
        return super.onTouchEvent(event);
    }

}
