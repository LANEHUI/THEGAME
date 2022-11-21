package com.example.mygame.activity;

import static com.example.mygame.global.Config.screenHeight;
import static com.example.mygame.global.Config.screenWidth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mygame.R;
import com.example.mygame.tools.DeviceTools;
import com.example.mygame.view.StartView;

public class StartActivity extends AppCompatActivity {
    public StartView startView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//        if ( getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
//        }
//        setContentView(R.layout.activity_start);
//        Button startBtn = (Button) findViewById(R.id.startBtn);
//        startBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(StartActivity.this,MainActivity.class);
//            startActivity(intent);
//        });
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dis = getResources().getDisplayMetrics();
        screenWidth = dis.widthPixels;
        screenHeight = dis.heightPixels;
        startView = new StartView(StartActivity.this);
        setContentView(startView);
    }
}