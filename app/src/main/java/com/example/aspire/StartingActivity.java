package com.example.aspire;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartingActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2000;
    Animation topAnimation, bottomAnimation;
    ImageView image_logo;
    TextView txt_logo, txt_sapo, txt_slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.starting_layout);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image_logo = findViewById(R.id.image_logo);
        txt_logo = findViewById(R.id.txt_logo);
        txt_slogan = findViewById(R.id.txt_slogan);
        txt_sapo = findViewById(R.id.txt_sapo);

        image_logo.setAnimation(topAnimation);
        txt_logo.setAnimation(bottomAnimation);
        txt_slogan.setAnimation(bottomAnimation);
        txt_sapo.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartingActivity.this, StartActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(image_logo, "image_logo");
                pairs[1] = new Pair<View, String>(txt_logo, "txt_logo");

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartingActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                    finish();
                }
            }
        }, SPLASH_SCREEN);
    }
}