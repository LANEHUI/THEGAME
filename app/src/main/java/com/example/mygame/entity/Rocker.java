package com.example.mygame.entity;

import static com.example.mygame.global.Config.screenHeight;
import static com.example.mygame.global.Config.screenWidth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.mygame.R;
import com.example.mygame.view.GameView;

public class Rocker {
    //固定摇杆背景圆形的X,Y坐标以及半径
    public int mRockerBg_X;
    public int mRockerBg_Y;
    public int mRockerBg_R;
    //摇杆的X,Y坐标以及摇杆的半径
    public int mRockerBtn_X;
    public int mRockerBtn_Y;
    public int mRockerBtn_R;
//    攻击键的X，Y坐标与半径
    public int mAttackBtn_X;
    public int mAttackBtn_Y;
    public int mAttackBtn_R;

    private final Bitmap mBmpRockerBg;
    private final Bitmap mBmpRockerBtn;
    private final Bitmap mBmpAttackBtn;




    public Rocker() {
        mBmpRockerBg = BitmapFactory.decodeResource(GameView.getInstance().getContext().getResources(), R.drawable.rocker_bg);
        mBmpRockerBtn = BitmapFactory.decodeResource(GameView.getInstance().getContext().getResources(), R.drawable.rocker_bg);
        mBmpAttackBtn = BitmapFactory.decodeResource(GameView.getInstance().getContext().getResources(), R.drawable.attack_btn);
        mRockerBg_R = screenHeight/5;
        mRockerBtn_R = screenHeight/15;
        mAttackBtn_R = mRockerBg_R;
        mRockerBg_X = mRockerBg_R + 20;
        mRockerBg_Y = screenHeight - mRockerBg_R - 20;
        mRockerBtn_X = mRockerBg_X - 20;
        mRockerBtn_Y = mRockerBg_Y - 10;
        mAttackBtn_X = screenWidth - mAttackBtn_R;
        mAttackBtn_Y = mRockerBg_Y;
    }

    public void drawSelf(Canvas canvas, Paint paint) {
        int Alpha = paint.getAlpha();
        paint.setAlpha(100);
        canvas.drawBitmap(mBmpRockerBg, null,
                new Rect((int) (mRockerBg_X - mRockerBg_R),
                        (int) (mRockerBg_Y - mRockerBg_R),
                        (int) (mRockerBg_X + mRockerBg_R),
                        (int) (mRockerBg_Y + mRockerBg_R)),
                paint);
        canvas.drawBitmap(mBmpRockerBtn, null,
                new Rect((int) (mRockerBtn_X - mRockerBtn_R),
                        (int) (mRockerBtn_Y - mRockerBtn_R),
                        (int) (mRockerBtn_X + mRockerBtn_R),
                        (int) (mRockerBtn_Y + mRockerBtn_R)),
                paint);
        canvas.drawBitmap(mBmpAttackBtn, null,
                new Rect((int) (mAttackBtn_X - mAttackBtn_R),
                        (int) (mAttackBtn_Y - mAttackBtn_R),
                        (int) (mAttackBtn_X + mAttackBtn_R),
                        (int) (mAttackBtn_Y + mAttackBtn_R)),
                paint);
        paint.setAlpha(Alpha);

    }

    /***
     * 得到两点之间的弧度
     */
    public double getRad(int px1, int py1, float px2, float py2) {
        //得到两点X的距离
        float x = px2 - px1;
        //得到两点Y的距离
        float y = py1 - py2;
        //算出斜边长
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //得到这个角度的余弦值（通过三角函数中的定理 ：邻边/斜边=角度余弦值）
        float cosAngle = x / xie;
        //通过反余弦定理获取到其角度的弧度
        float rad = (float) Math.acos(cosAngle);
        //注意：当触屏的位置Y坐标<摇杆的Y坐标我们要取反值-0~-180
        if (py2 < py1) {
            rad = -rad;
        }
        return rad;
    }

    /**
     * @param R       圆周运动的旋转点
     * @param centerX 旋转点X
     * @param centerY 旋转点Y
     * @param rad     旋转的弧度
     */
    public void getXY(int centerX, int centerY, int R, double rad) {
        //获取圆周运动的X坐标
        mRockerBtn_X = (int) (R * Math.cos(rad)) + centerX;
        //获取圆周运动的Y坐标
        mRockerBtn_Y = (int) (R * Math.sin(rad)) + centerY;
    }
}
