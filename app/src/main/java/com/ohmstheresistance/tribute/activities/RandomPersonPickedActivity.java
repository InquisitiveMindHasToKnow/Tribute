package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;

public class RandomPersonPickedActivity extends AppCompatActivity {

    private TextView randomlySelectedPerson;
    private ImageView randomPersonSelectedImageView;
    private Intent chosenPersonIntent;
    private static final String RANDOM_PERSON_KEY = "randomPersonKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_person_picked);

        randomlySelectedPerson = findViewById(R.id.randomly_selected_person_textview);
        randomPersonSelectedImageView = findViewById(R.id.random_person_selected_imageview);

        chosenPersonIntent = getIntent();
        randomlySelectedPerson.setText(chosenPersonIntent.getStringExtra(RANDOM_PERSON_KEY));


    }
}
