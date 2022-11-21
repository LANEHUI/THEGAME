package com.example.mygame.activity;

import static com.example.mygame.global.Config.bulletPic;
import static com.example.mygame.global.Config.enemyPic1;
import static com.example.mygame.global.Config.enemyPic2;
import static com.example.mygame.global.Config.enemyPic3;
import static com.example.mygame.global.Config.gameBK;
import static com.example.mygame.global.Config.playerPic;
import static com.example.mygame.global.Config.scaleHeight;
import static com.example.mygame.global.Config.scaleWidth;
import static com.example.mygame.global.Config.screenHeight;
import static com.example.mygame.global.Config.screenWidth;
import static com.example.mygame.global.Config.starPic1;
import static com.example.mygame.global.Config.starPic2;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygame.R;
import com.example.mygame.tools.DeviceTools;
import com.example.mygame.view.GameView;

public class MainActivity extends AppCompatActivity {
    public GameView gameView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        gameView = new GameView(MainActivity.this);
        setContentView(gameView);

    }

    private void init(){
//        设置游戏背景；
        gameBK = BitmapFactory.decodeResource(this.getResources(), R.drawable.game_bk);
        scaleWidth = screenWidth / (float) gameBK.getWidth();
        scaleHeight = screenHeight / (float) gameBK.getHeight();
        gameBK = DeviceTools.resizeBitmap(gameBK);
        playerPic = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.player),200,screenHeight/6 );
        bulletPic = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.bullet),screenHeight/6,100);
        enemyPic1 = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.enemy1),screenHeight/6,screenHeight/6);
        enemyPic2 = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.enemy2),screenHeight/6,screenHeight/6);
        enemyPic3 = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.enemy3),screenHeight/6,screenHeight/6);
        starPic1 = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.star),screenHeight/10,screenHeight/10);
        starPic2 = DeviceTools.resizeBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.star1),screenHeight/10,screenHeight/10);
    }

    public boolean onTouchEvent (MotionEvent event){
        return gameView.onTouchEvent(event);
    }

}