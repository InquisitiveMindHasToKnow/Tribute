package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
        handler.postDelayed(() -> {
            chosenFellowTextView.setText(chosenFellowIntent.getStringExtra(RANDOM_FELLOW_KEY));
            chosenFellowTextView.setBackgroundColor(Color.parseColor("#FBF1C6"));

            try {
                Uri randomFellowAlert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone alert = RingtoneManager.getRingtone(getApplicationContext(), randomFellowAlert);
                alert.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 3000);

    }
}