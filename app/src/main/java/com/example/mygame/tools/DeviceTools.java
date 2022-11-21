package com.example.mygame.tools;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import com.example.mygame.global.Config;

public class DeviceTools {

    public static Bitmap resizeBitmap(Bitmap bitmap){
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Log.i("info",width + " " + height);
            Matrix matrix = new Matrix();
            matrix.postScale(Config.scaleWidth, Config.scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,0,0,width,
                    height,matrix,true);
            return resizedBitmap;
        }else {
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap,int w,int h){
        if (bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = w;
            int newHeight = h;
            float scaleWidth = (float) newWidth / width;
            float scaleHeight = (float) newHeight / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth,scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,0,0,width,
                    height,matrix,true);
            return resizedBitmap;
        }else {
            return null;
        }
    }
}
