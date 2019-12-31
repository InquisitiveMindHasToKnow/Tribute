package com.ohmstheresistance.tribute.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.activities.LoginActivity;

public class SplashWelcome extends AppCompatActivity {

    ImageView splashImage;
    private static int SPLASH_SCREEN_TIMER = 5000;
    private Intent toLoginScreenIntent;

    private TextView splashImageBackGroundTextView;
    private ImageView splashScreenImageOneImageView;
    private ImageView splashScreenImageTwoImageView;
    private ImageView splashScreenImageThreeImageView;
    private ImageView splashScreenImageFourImageView;
    private ImageView splashScreenImageFiveImageView;
    private ImageView splashScreenImageSixImageView;
    private ImageView splashScreenImageSevenImageView;
    private ImageView splashScreenImageEightImageView;
    private ImageView splashScreenImageNineImageView;
    private ImageView splashScreenImageTenImageView;
    private ImageView splashScreenImageElevenImageView;
    private ImageView splashScreenImageTwelveImageView;
    private ImageView splashScreenImageThirteenImageView;
    private ImageView splashScreenImageFourteenImageView;
    private ImageView splashScreenImageFifteenImageView;
    private ImageView splashScreenImageSixteenImageView;
    private ImageView splashScreenImageSeventeenImageView;
    private ImageView splashScreenImageEighteenImageView;
    private ImageView splashScreenImageNineteenImageView;
    private ImageView splashScreenImageTwentyImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        splashImageBackGroundTextView = findViewById(R.id.splash_image_background_textview);

        splashImage = findViewById(R.id.splash_screen_image);
        splashScreenImageOneImageView = findViewById(R.id.splash_screen_image1);
        splashScreenImageTwoImageView = findViewById(R.id.splash_screen_image2);
        splashScreenImageThreeImageView = findViewById(R.id.splash_screen_image3);
        splashScreenImageFourImageView = findViewById(R.id.splash_screen_image4);
        splashScreenImageFiveImageView = findViewById(R.id.splash_screen_image5);
        splashScreenImageSixImageView = findViewById(R.id.splash_screen_image6);
        splashScreenImageSevenImageView = findViewById(R.id.splash_screen_image7);
        splashScreenImageEightImageView = findViewById(R.id.splash_screen_image8);
        splashScreenImageNineImageView = findViewById(R.id.splash_screen_image9);
        splashScreenImageTenImageView = findViewById(R.id.splash_screen_image10);
        splashScreenImageElevenImageView = findViewById(R.id.splash_screen_image11);
        splashScreenImageTwelveImageView = findViewById(R.id.splash_screen_image12);
        splashScreenImageThirteenImageView = findViewById(R.id.splash_screen_image13);
        splashScreenImageFourteenImageView = findViewById(R.id.splash_screen_image14);
        splashScreenImageFifteenImageView = findViewById(R.id.splash_screen_image15);
        splashScreenImageSixteenImageView = findViewById(R.id.splash_screen_image16);
        splashScreenImageSeventeenImageView = findViewById(R.id.splash_screen_image17);
        splashScreenImageEighteenImageView = findViewById(R.id.splash_screen_image18);
        splashScreenImageNineteenImageView = findViewById(R.id.splash_screen_image19);
        splashScreenImageTwentyImageView = findViewById(R.id.splash_screen_image20);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                splashScreenImageOneImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageTwoImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageThreeImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageFourImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageFiveImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageSixImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageSevenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageEightImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageNineImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageTenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageElevenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageTwelveImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageThirteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageFourteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageFifteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageSixteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageSeventeenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageEighteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageNineteenImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));
                splashScreenImageTwentyImageView.startAnimation(AnimationUtils.loadAnimation(SplashWelcome.this, R.anim.scale_down));


                splashScreenImageOneImageView.setVisibility(View.INVISIBLE);
                splashScreenImageTwoImageView.setVisibility(View.INVISIBLE);
                splashScreenImageThreeImageView.setVisibility(View.INVISIBLE);
                splashScreenImageFourImageView.setVisibility(View.INVISIBLE);
                splashScreenImageFiveImageView.setVisibility(View.INVISIBLE);
                splashScreenImageSixImageView.setVisibility(View.INVISIBLE);
                splashScreenImageSevenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageEightImageView.setVisibility(View.INVISIBLE);
                splashScreenImageNineImageView.setVisibility(View.INVISIBLE);
                splashScreenImageTenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageElevenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageTwelveImageView.setVisibility(View.INVISIBLE);
                splashScreenImageThirteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageFourteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageFifteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageSixteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageSeventeenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageEighteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageNineteenImageView.setVisibility(View.INVISIBLE);
                splashScreenImageTwentyImageView.setVisibility(View.INVISIBLE);

            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Animation blinkingAnimation = new AlphaAnimation(0.0f, 1.0f);
                blinkingAnimation.setDuration(100);
                blinkingAnimation.setStartOffset(300);
                blinkingAnimation.setRepeatMode(Animation.REVERSE);
                blinkingAnimation.setRepeatCount(Animation.INFINITE);
                splashImage.startAnimation(blinkingAnimation);
            }
        }, 3000);

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

