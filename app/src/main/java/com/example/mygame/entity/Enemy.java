package com.example.mygame.entity;

import static com.example.mygame.global.Config.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


import com.example.mygame.model.BaseModel;

import java.util.ArrayList;

public class Enemy extends BaseModel {
    private int locationX;
    private int locationY;
    private boolean isAlive;
    private int speed;
    private int blood;
    private Bitmap enemyPic;
    public int enemyKind;

    public Enemy(int locationX, int locationY, int enemyKind){
        this.locationX = locationX;
        this.locationY = locationY;
        this.isAlive = true;
        this.enemyKind = enemyKind;
        setEnemyKind(enemyKind);
    }

    public void drawSelf(Canvas canvas, Paint paint){
        if (isAlive){
            canvas.drawBitmap(enemyPic,locationX,locationY,paint);
            move();
        }
    }

    public void setEnemyKind(int kind){
        switch (kind){
            case 0:
                enemyPic = enemyPic1;
                blood = 3;
                speed = 5;
                break;
            case 1:
                enemyPic = enemyPic2;
                speed = 10;
                blood = 5;
                break;
            case 2:
                enemyPic = enemyPic3;
                speed = 5;
                blood = 7;
                break;
            default:
                break;
        }
    }

    public void move(){
        locationX -= speed;
    }

    public void hitBullet(ArrayList<Bullet> bulletArrayList){
        for (Bullet bullet: bulletArrayList){
            if(bullet.getLocationX() + bulletPic.getWidth() - 30 > this.locationX + 40 &&
                    bullet.getLocationX() < this.locationX + enemyPic.getWidth() &&
                    bullet.getLocationY() + bulletPic.getHeight() - 20 > this.locationY + 20 &&
                    bullet.getLocationY() + 20 < this.locationY + enemyPic.getHeight() - 20){
                bullet.setAlive(false);
                this.blood--;
            }
            if (this.blood == 0){
                this.isAlive = false;
            }
        }
    }

    public void hitStar(ArrayList<Star> starList){
        for (Star star: starList){
            if(star.getLocationX() + starPic1.getWidth() - 30 > this.locationX + 30 &&
                    star.getLocationX() + 30 < this.locationX + enemyPic.getWidth() - 30 &&
                    star.getLocationY() + starPic1.getHeight() > this.locationY &&
                    star.getLocationY() < this.locationY + enemyPic.getHeight()){
                star.setAlive(false);
                this.blood = this.blood * star.bloodAdd;
                this.speed = this.speed * star.speedAdd;
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
