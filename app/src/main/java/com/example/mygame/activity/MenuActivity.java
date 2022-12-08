package com.example.mygame.activity;



import static com.example.mygame.global.Config.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mygame.R;
import com.example.mygame.music.Music;
import com.example.mygame.tools.DeviceTools;

public class MenuActivity extends AppCompatActivity {
    private Music music = new Music();

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        music.play(this, R.raw.bgm);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        music.stop(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dis = getResources().getDisplayMetrics();
        screenWidth = dis.widthPixels;
        screenHeight = dis.heightPixels;
        Button button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this,MainActivity.class);
            startActivity(intent);
        });
        Button button2 = (Button) findViewById(R.id.Button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this,StartActivity.class);
            startActivity(intent);
        });
    }



}