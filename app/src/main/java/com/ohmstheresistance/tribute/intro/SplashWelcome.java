package com.ohmstheresistance.tribute.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.activities.LoginActivity;

public class SplashWelcome extends AppCompatActivity {

    ImageView splashImage;
    private static int SPLASH_SCREEN_TIMER = 2000;
    private Intent toLoginScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        splashImage = findViewById(R.id.splash_screen_image);


        Animation blinkingAnimation = new AlphaAnimation(0.0f, 1.0f);
        blinkingAnimation.setDuration(100);
        blinkingAnimation.setStartOffset(300);
        blinkingAnimation.setRepeatMode(Animation.REVERSE);
        blinkingAnimation.setRepeatCount(Animation.INFINITE);
        splashImage.startAnimation(blinkingAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                toLoginScreenIntent = new Intent(SplashWelcome.this, LoginActivity.class);
                SplashWelcome.this.startActivity(toLoginScreenIntent);
                SplashWelcome.this.finish();
            }
        }, SPLASH_SCREEN_TIMER);
    }
}

