package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ohmstheresistance.tribute.R;

public class RandomFellowPickedActivity extends AppCompatActivity {

    private TextView chosenFellowTextView;
    private TextView fellowMotivationTextView;
    private ImageView chosenFellowImageview;
    private Intent chosenFellowIntent;
    ToneGenerator randomFellowSound;
    private static final String RANDOM_FELLOW_KEY = "randomFellowKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_fellow_picked_view);

        chosenFellowIntent = getIntent();

        chosenFellowTextView = findViewById(R.id.randomly_selected_fellow_textview);
        chosenFellowImageview = findViewById(R.id.i_choose_you_gif_imageview);
        fellowMotivationTextView = findViewById(R.id.fellow_motivation);

        Glide.with(this).load(R.drawable.ashchoseyou).into(chosenFellowImageview);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                chosenFellowTextView.setText(chosenFellowIntent.getStringExtra(RANDOM_FELLOW_KEY));
                chosenFellowTextView.setBackgroundColor(Color.parseColor("#FBF1C6"));

                randomFellowSound = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
                randomFellowSound.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 600);

            }
        }, 3000);

    }
}