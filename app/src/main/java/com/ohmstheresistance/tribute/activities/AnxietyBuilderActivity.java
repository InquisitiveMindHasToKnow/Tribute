package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ohmstheresistance.tribute.R;

public class AnxietyBuilderActivity extends AppCompatActivity {

    ImageView anxietyBuilderImage;
    private static int ANXITY_BUILDER_TIMER = 7000;
    private Intent toDisplayPersonPicked;
    private Intent getRandomPersonIntent;
    private static final String RANDOM_PERSON_KEY = "randomPersonKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_builder);

        getRandomPersonIntent = getIntent();

        String randomPerson = getRandomPersonIntent.getStringExtra(RANDOM_PERSON_KEY);


        anxietyBuilderImage = findViewById(R.id.build_anxiety_screen_image);

        Glide.with(AnxietyBuilderActivity.this)
                .load(R.drawable.slotmachine)
                .into(anxietyBuilderImage);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                toDisplayPersonPicked = new Intent(AnxietyBuilderActivity.this, RandomPersonPickedActivity.class);
                toDisplayPersonPicked.putExtra(RANDOM_PERSON_KEY, randomPerson);
                Log.e("randomPerson: ", randomPerson);
                AnxietyBuilderActivity.this.startActivity(toDisplayPersonPicked);
                AnxietyBuilderActivity.this.finish();
            }
        }, ANXITY_BUILDER_TIMER);
    }
}
