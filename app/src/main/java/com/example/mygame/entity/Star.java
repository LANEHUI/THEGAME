package com.example.mygame.entity;

import static com.example.mygame.global.Config.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


import com.example.mygame.model.BaseModel;

public class Star extends BaseModel {
    private int locationX;
    private int locationY;
    private boolean isAlive;
    private Bitmap starPic;
    private int time;
    public int bloodAdd;
    public int speedAdd;

    public Star(int locationX, int locationY, int starKind){
        this.locationX = locationX;
        this.locationY = locationY;
        isAlive = true;
        setStarKind(starKind);
        time = 0;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        if (isAlive){
            canvas.drawBitmap(starPic,locationX,locationY,paint);
            time++;
        }
        if (time > 500){
            isAlive = false;
        }
    }

    public void setStarKind(int kind){
        switch (kind){
            case 0:
                starPic = starPic1;
                bloodAdd = 2;
                speedAdd = 1;
                break;
            case 1:
                starPic = starPic2;
                bloodAdd = 1;
                speedAdd = 2;
                break;
            default:
                break;
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
