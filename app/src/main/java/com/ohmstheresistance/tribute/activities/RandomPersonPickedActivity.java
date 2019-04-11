package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;

public class RandomPersonPickedActivity extends AppCompatActivity {

    private TextView randomlySelectedPerson;
    private Intent chosenPersonIntent;
    private static final String RANDOM_PERSON_KEY = "randomPersonKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_person_picked);

        randomlySelectedPerson = findViewById(R.id.randomly_selected_person_textview);

        chosenPersonIntent = getIntent();
        randomlySelectedPerson.setText(chosenPersonIntent.getStringExtra(RANDOM_PERSON_KEY));

        try {
            Uri randomPersonAlert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone alert = RingtoneManager.getRingtone(getApplicationContext(), randomPersonAlert);
            alert.play();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
