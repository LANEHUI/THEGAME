package com.example.mygame.global;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static int screenWidth;
    public static int screenHeight;
    public static Bitmap gameBK;
    public static float scaleWidth;
    public static float scaleHeight;
//    游戏人物图片
    public static Bitmap playerPic;
//    子弹图片；
    public static Bitmap bulletPic;
    public static Bitmap enemyPic1;
    public static Bitmap enemyPic2;
    public static Bitmap enemyPic3;
    public static Bitmap starPic1;
    public static Bitmap starPic2;
    public static List<Activity> activities = new ArrayList<>();
}