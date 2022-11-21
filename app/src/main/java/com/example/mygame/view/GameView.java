package com.example.mygame.view;


import static com.example.mygame.global.Config.gameBK;
import static com.example.mygame.global.Config.playerPic;
import static com.example.mygame.global.Config.screenHeight;
import static com.example.mygame.global.Config.screenWidth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.mygame.activity.MainActivity;
import com.example.mygame.activity.StartActivity;
import com.example.mygame.entity.Bullet;
import com.example.mygame.entity.Enemy;
import com.example.mygame.entity.Player;
import com.example.mygame.entity.Rocker;
import com.example.mygame.entity.Star;
import com.example.mygame.model.BaseModel;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static GameView gameView;
//    创建画笔
    public Paint paint;
//    创建画布
    public Canvas canvas;
    public Rocker rocker;
    public Player player;
    public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    public ArrayList<Star> starList = new ArrayList<Star>();
    public ArrayList<BaseModel> deadEntity = new ArrayList<BaseModel>();
    Random random = new Random();
//    创建画布管理者
    private SurfaceHolder surfaceHolder;
//    设置运行标记
    private boolean gameRunFlag;
//    设置出怪时间
    private int times = 0;
    private int frequency = 100;
    public int count;
    private MainActivity mainActivity;
    private boolean touchFlag = false;
    public int deadEnemyCount;
    public int bulletCount = 0;


    public GameView(Context context){
        super(context);
        gameView = this;
        mainActivity = (MainActivity) context;
        paint = new Paint();
        rocker = new Rocker();
        player = new Player(0,screenHeight / 2);
        count = 0;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameRunFlag = true;
        deadEnemyCount = 0;
    }

    @Override
    public void run() {
        while (gameRunFlag){
            synchronized (surfaceHolder){
                if (times % frequency == 1){
                    enemyList.add(new Enemy(screenWidth + 10,
                            random.nextInt(5) * screenHeight * 7 / 36 + screenHeight / 36,
                            random.nextInt(3)));
                }
                if (times % (3 * frequency) == 1){
                    starList.add(new Star(2 * screenWidth / 3,
                            random.nextInt(5) * screenHeight * 7 / 36 + screenHeight / 36 + screenHeight / 30,
                            random.nextInt(2)));
                }
                if (times % 10 == 1){
                    bulletCount++;
                }
                try{
                    //把画布锁住
                    canvas = surfaceHolder.lockCanvas();
                    updataData();
                    ondraw(canvas);
                    if (!gameRunFlag){
//                        paint.setColor(Color.BLACK);
//                        canvas.drawRect(0,0,screenWidth,screenHeight,paint);
                        paint.setTextSize(200);
                        paint.setColor(Color.RED);;
                        canvas.drawText("You Lose",(screenWidth - paint.measureText("You Lose")) / 2,screenHeight /2,paint);
                    }

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
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent(mainActivity,StartActivity.class);
        Log.d("END","end" + gameRunFlag);
        mainActivity.startActivity(intent);
    }




    public void ondraw(Canvas canvas){
        canvas.drawBitmap(gameBK,0,0,paint);
        rocker.drawSelf(canvas,paint);
        player.drawSelf(canvas,paint);
        for (Enemy enemy: enemyList){
            enemy.drawSelf(canvas,paint);
            enemy.hitBullet(bulletList);
            if (enemy.getLocationX() + screenHeight / 6 >= 2 * screenWidth / 3) {
                enemy.hitStar(starList);
            }
            if (!enemy.isAlive()){
                deadEntity.add(enemy);
                deadEnemyCount++;
                if (deadEnemyCount % 3 == 1 && frequency > 20){
                    frequency -= 10;
                }
                if (enemy.enemyKind == 0){
                    count += 50;
                }else {
                    count += 100;
                }
            }
            if (enemy.getLocationX() < -200){
                gameRunFlag = false;
                break;
            }
        }
        for (Bullet bullet: bulletList){
            bullet.drawSelf(canvas,paint);
            if (bullet.getLocationX() >= screenWidth + 10){
                bullet.setAlive(false);
            }
            if (!bullet.isAlive()){
                deadEntity.add(bullet);
            }
        }
        for (Star star: starList){
            star.drawSelf(canvas ,paint);
            if (!star.isAlive()){
                deadEntity.add(star);
            }
        }
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawText("COUNT:" + count,20,40,paint);

    }

    public void updataData(){
        for (BaseModel model: deadEntity){
            bulletList.remove(model);
            enemyList.remove(model);
            starList.remove(model);
        }
        player.hitEnemy(enemyList);
        deadEntity.clear();
    }

    public static GameView getInstance(){
        return gameView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        rocker.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            // 当触屏区域不在活动范围内
            if (Math.sqrt(Math.pow((rocker.mRockerBg_X - (int) event.getX()), 2) + Math.pow((rocker.mRockerBg_Y - (int) event.getY()), 2)) <= rocker.mRockerBg_R) {
//                //得到摇杆与触屏点所形成的角度
//                double tempRad = getRad(mRockerBg_X, mRockerBg_Y, event.getX(), event.getY());
//                //保证内部小圆运动的长度限制
//                getXY(mRockerBg_X, mRockerBg_Y, mRockerBg_R, tempRad);
//            } else {//如果小球中心点小于活动区域则随着用户触屏点移动即可
                rocker.mRockerBtn_X = (int) event.getX();
                rocker.mRockerBtn_Y = (int) event.getY();
                player.report((float) Math.sqrt(Math.pow(event.getX(), 2) + Math.pow(event.getY(), 2)) / rocker.mRockerBg_R,
                            (float) rocker.getRad(rocker.mRockerBg_X, rocker.mRockerBg_Y, event.getX(), event.getY()));
                touchFlag  = true;
            }
            if (Math.sqrt(Math.pow((rocker.mAttackBtn_X - (int) event.getX()), 2) + Math.pow((rocker.mAttackBtn_Y - (int) event.getY()), 2)) <= rocker.mAttackBtn_R
                   && bulletCount != 0){
                if (player.isAlive()) {
                    bulletList.add(new Bullet(player.getLocationX() + playerPic.getWidth() - 35,
                            player.getLocationY() + playerPic.getHeight() / 2 - 55));
                    bulletCount--;
                }
            }
            if (Math.sqrt(Math.pow((rocker.mRockerBg_X - (int) event.getX()), 2) + Math.pow((rocker.mRockerBg_Y - (int) event.getY()), 2)) > rocker.mRockerBg_R
            && touchFlag && Math.sqrt(Math.pow((rocker.mAttackBtn_X - (int) event.getX()), 2) + Math.pow((rocker.mAttackBtn_Y - (int) event.getY()), 2)) > rocker.mAttackBtn_R){
                rocker.getXY(rocker.mRockerBg_X,rocker.mRockerBg_Y,rocker.mRockerBg_R,
                        rocker.getRad(rocker.mRockerBg_X,rocker.mRockerBg_Y,event.getX(),event.getY()));
                player.report((float)7 * rocker.mRockerBg_R / rocker.mRockerBg_R,
                        (float) rocker.getRad(rocker.mRockerBg_X, rocker.mRockerBg_Y, event.getX(), event.getY()));
            }


        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //当释放按键时摇杆要恢复摇杆的位置为初始位置
            rocker.mRockerBtn_X = rocker.mRockerBg_X - 20;
            rocker.mRockerBtn_Y = rocker.mRockerBg_Y - 10;

            player.report(0, 0);
            touchFlag = false;

        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        new Thread(this).start();
        Log.d("THE","Holder");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("THE","change");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("THE","Destroyed");
    }
}
