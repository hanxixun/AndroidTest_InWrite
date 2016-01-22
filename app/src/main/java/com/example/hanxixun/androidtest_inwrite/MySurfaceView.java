package com.example.hanxixun.androidtest_inwrite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: hanxixun
 * @time: 2016/1/22 10:18
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public final static String TAG = "MySurfaceView";
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Thread thread;
    private boolean flag;

    private float x, y;
    private float radius;
    private float color;
    private float speedX, speedY;


    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
    * 初始化
    * */
    private void init() {
        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.RED);


    }

    /*
    * 初始化游戏
    * */
    private void initGame() {
        x = 0;
        y = 0;
        x = getWidth() / 2;
        y = getHeight() / 2;
        radius = 50;
        color = Color.RED;

    }
    private void myDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor((int) color);
        canvas.drawCircle(x, y, radius, paint);
    }

    private void logic() {
        x += speedX;
        y += speedY;
        if (x >= getWidth() || x < 0) {
            speedX = -speedX;
        }
        if (y >= getHeight() || y < 0) {
            speedY = -speedY;
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreate");
        initGame();
        flag = true;
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        flag = false;
    }

    public boolean onTounchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        color = Color.argb(0, x % 255, y % 255, x * y % 255);
        radius = new Random().nextInt(10) + 50;
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();

            canvas = holder.lockCanvas();
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            logic();

            long end = System.currentTimeMillis();
            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {

                }

            }
        }
    }
}
