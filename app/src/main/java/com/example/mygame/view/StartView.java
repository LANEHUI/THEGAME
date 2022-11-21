package com.example.mygame.view;

import static com.example.mygame.global.Config.*;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.mygame.R;
import com.example.mygame.activity.MainActivity;
import com.example.mygame.activity.StartActivity;
import com.example.mygame.entity.Enemy;
import com.example.mygame.entity.Star;
import com.example.mygame.tools.DeviceTools;

public class StartView  extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    private String[] text;
    private Bitmap startBK;
    private Canvas canvas;
    private Paint paint;
    private boolean[] textBln = new boolean[6];
    private SurfaceHolder surfaceHolder;
    private int times;
    private int index;
    private Rect rect1;
    private Rect rect2;
    private StartActivity startActivity;

    public StartView(Context context) {
        super(context);
        startBK = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.start_bk),screenWidth,screenHeight);
        text = new String[]{"你是否想象过，在另一个纪元里做自己的王?", "若是如B612号小行星般孤独寂寞呢？",
        "若是野兽横行、一片狼藉呢？", "你是否愿意一直在自己的世界里做孤独的王?","愿意","非常愿意"};
        canvas = new Canvas();
        paint = new Paint();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        startActivity = (StartActivity) context;
        for (int i = 0; i < 6 ; i++){
            textBln[i] = false;
        }
        times = 0;
        index = 0;
        rect1 = new Rect(screenWidth / 5,screenHeight / 2 + 20,2 * screenWidth / 5,screenHeight / 2 + 200);
        rect2 = new Rect(3 * screenWidth / 5,screenHeight / 2 + 20,4 * screenWidth / 5,screenHeight / 2 + 200);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        Log.d("SIZE",screenWidth + "," + screenHeight);
        while (true){
            synchronized (surfaceHolder){
                if(times > 20 && times % 20 == 1 && index < 6){
                    textBln[index] = true;
                    index++;
                }
                try{
                    //把画布锁住
                    canvas = surfaceHolder.lockCanvas();
                    ondraw(canvas);

                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    if(canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try {
                Thread.sleep(50);
                times++;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void ondraw(Canvas canvas){
        canvas.drawBitmap(startBK,0,0,paint);
        for (int i = 0;i < 6 ; ++i){
            if (textBln[i]){
                if (i >= 0 && i <= 3){
                    paint.setTextSize(50);
                    paint.setColor(Color.WHITE);
                    canvas.drawText(text[i],(screenWidth - paint.measureText(text[i])) / 2,screenHeight / 20 + i * (screenHeight / 8),paint);
                }else if (i == 4){
                    paint.setTextSize(100);
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(rect1,paint);
                    paint.setColor(Color.WHITE);
                    canvas.drawText(text[i],3 * screenWidth / 10 - paint.measureText(text[i]) / 2,screenHeight / 2 + 150,paint );
                }else {
                    paint.setTextSize(100);
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(rect2,paint);
                    paint.setColor(Color.WHITE);
                    canvas.drawText(text[i],7 * screenWidth / 10 - paint.measureText(text[i]) / 2,screenHeight / 2 + 150,paint );
                }

            }
        }

    }

    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            if(rect1.contains((int) event.getX(),(int) event.getY()) || rect2.contains((int) event.getX(),(int) event.getY())){
                Intent intent = new Intent(startActivity, MainActivity.class);
                startActivity.startActivity(intent);
            }
        }
        return true;
    }

}
