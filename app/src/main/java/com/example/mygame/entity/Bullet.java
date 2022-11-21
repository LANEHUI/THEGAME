package com.example.mygame.entity;

import static com.example.mygame.global.Config.*;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.mygame.model.BaseModel;

public class Bullet extends BaseModel {
    private int locationX;
    private int locationY;
    private boolean isAlive;
    private final int speed;

    public Bullet(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        isAlive = true;
        speed = 20;
    }

    @Override
    public void drawSelf(Canvas canvas, Paint paint) {
        if (isAlive){
            canvas.drawBitmap(bulletPic,locationX,locationY,paint);
            move();
        }
    }

    public void move(){
        locationX += speed;
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
