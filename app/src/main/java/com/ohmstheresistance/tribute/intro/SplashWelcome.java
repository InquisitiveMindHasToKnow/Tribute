package com.ohmstheresistance.tribute.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.activities.LoginActivity;
import com.squareup.picasso.Picasso;

public class SplashWelcome extends AppCompatActivity {

    ImageView splashImage;
    private static int SPLASH_SCREEN_TIMER = 3000;
    private Intent toLoginScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        splashImage = findViewById(R.id.splash_screen_image);

        Picasso.get()
                .load(R.drawable.questiondice)
                .into(splashImage);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                toLoginScreenIntent = new Intent(SplashWelcome.this, LoginActivity.class);
                startActivity(toLoginScreenIntent);
                finish();
            }
        }, SPLASH_SCREEN_TIMER);
    }
}

