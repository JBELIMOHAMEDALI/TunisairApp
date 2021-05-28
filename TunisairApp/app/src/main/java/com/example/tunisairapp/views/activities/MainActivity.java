package com.example.tunisairapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tunisairapp.R;

public class MainActivity extends AppCompatActivity {

    public final static long SPLASH_SCREEN = 4000;

    Animation topAnim, bottomAnim;
    TextView app_title, app_subtitle;
    ImageView app_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Widgets
        app_logo = findViewById(R.id.app_logo);
        app_title = findViewById(R.id.app_title);
        app_subtitle = findViewById(R.id.app_subtitle);

        //assign animation
        app_logo.setAnimation(topAnim);
        app_title.setAnimation(bottomAnim);
        app_subtitle.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent i = new Intent(MainActivity.this, LoginActivity.class);
               startActivity(i);
            }
        }, SPLASH_SCREEN);
    }
}