package com.example.mygame.activity;

import static com.example.mygame.global.Config.activities;
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
import com.example.mygame.music.Music;
import com.example.mygame.tools.DeviceTools;
import com.example.mygame.view.StartView;

public class StartActivity extends AppCompatActivity {
    public StartView startView = null;
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
        getSupportActionBar().hide();
        DeviceTools.addActivity(activities,this);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startView = new StartView(StartActivity.this);
        setContentView(startView);
    }

}