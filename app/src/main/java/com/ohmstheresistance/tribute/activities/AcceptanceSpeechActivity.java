package com.ohmstheresistance.tribute.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ohmstheresistance.tribute.R;

public class AcceptanceSpeechActivity extends AppCompatActivity {

    private TextView speechHeaderTextView;
    private TextView acceptanceSpeechTextView;
    private ImageView trophyAcceptanceImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance_speech);

        speechHeaderTextView = findViewById(R.id.speech_header_textview);
        acceptanceSpeechTextView = findViewById(R.id.acceptance_speech_textview);
        trophyAcceptanceImageView = findViewById(R.id.trophy_acceptance_imageview);

        Glide.with(this).load(R.drawable.trophyraisingsiralextribute).into(trophyAcceptanceImageView);
    }
}
