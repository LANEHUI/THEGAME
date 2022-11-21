package com.example.mygame.entity;

import static com.example.mygame.global.Config.enemyPic1;
import static com.example.mygame.global.Config.playerPic;
import static com.example.mygame.global.Config.screenHeight;
import static com.example.mygame.global.Config.screenWidth;


import android.graphics.Canvas;
import android.graphics.Paint;


import com.example.mygame.model.BaseModel;

import java.util.ArrayList;

public class Player extends BaseModel {
    private int locationX;
    private int locationY;
    private boolean isAlive;
    private final int speed;
    public int dx;
    public int dy;

    public Player(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        isAlive = true;
        speed = 7;
        dx = 0;
        dy = 0;
    }


    public void report(float bi, float angle) {
         dx = (int)(speed * bi * Math.cos(angle));
         dy = (int)(speed * bi * Math.sin(angle));
    }

    public void move(){
        locationX += dx;
        locationY += dy;
        if (locationX < 0){
            locationX = 0;
        }
        if (locationY < 0){
            locationY = 0;
        }
        if (locationX + playerPic.getWidth() > screenWidth){
            locationX = screenWidth - playerPic.getWidth();
        }
        if (locationY + playerPic.getHeight() > screenHeight){
            locationY = screenHeight - playerPic.getHeight();
        }
    }

    @Override
    public void drawSelf(Canvas canvas, Paint paint) {
        if (isAlive){
            canvas.drawBitmap(playerPic,locationX,locationY,paint);
            move();
        }
    }

    public void hitEnemy(ArrayList<Enemy> enemyArrayList){
        for (Enemy enemy: enemyArrayList){
            if(enemy.getLocationX() + enemyPic1.getWidth() - 30> this.locationX + 30 &&
                    enemy.getLocationX() + 30 < this.locationX + playerPic.getWidth() - 20 &&
                    enemy.getLocationY() + enemyPic1.getHeight() - 20 > this.locationY + 20 &&
                    enemy.getLocationY() + 20 < this.locationY + enemyPic1.getHeight() - 20){
                this.isAlive = false;
            }
        }

    }

    public int getLocationX(){
        return locationX;
    }

    public void setLocationX(int locationX){
        this.locationX = locationX;
    }

    public int getLocationY(){
        return locationY;
    }

    public void setLocationY(int locationY){
        this.locationY = locationY;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
}
