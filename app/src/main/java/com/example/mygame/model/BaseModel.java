package com.example.mygame.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BaseModel {
    private int locationX;
    private int locationY;
    private boolean isAlive;

    public void drawSelf(Canvas canvas, Paint paint){

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
