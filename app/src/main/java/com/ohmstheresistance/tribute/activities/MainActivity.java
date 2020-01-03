package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;

public class MainActivity extends AppCompatActivity {

    private static final String USER_NAME_KEY = "currentUser";
    private Intent userNameIntent;
    private TextView welcomeTextView;
    private ImageView welcomeScreenImageView;

    private Button viewFellowListButton;
    private Button generatePersonListButton;
    private Button aboutTheCreatorButton;

    private long lastButtonClickTime = 0;
    private Intent navigationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textview);
        welcomeScreenImageView = findViewById(R.id.welcome_screen_imageview);

        viewFellowListButton = findViewById(R.id.main_page_view_fellow_list_button);
        generatePersonListButton = findViewById(R.id.main_page_generate_person_list_button);
        aboutTheCreatorButton = findViewById(R.id.main_page_about_the_creator_button);

        userNameIntent = getIntent();
        String userName = userNameIntent.getStringExtra(USER_NAME_KEY);
        welcomeTextView.setText("Welcome, " + userName + "!");


        viewFellowListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();
                navigationIntent = new Intent(MainActivity.this , ViewFellowListActivity.class);
                startActivity(navigationIntent);
                overridePendingTransition(0, 0);

            }
        });

        generatePersonListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();
                navigationIntent = new Intent(MainActivity.this , CreateListActivity.class);
                startActivity(navigationIntent);
                overridePendingTransition(0, 0);

            }
        });

        aboutTheCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();
                navigationIntent = new Intent(MainActivity.this , AboutTheCreator.class);
                startActivity(navigationIntent);
                overridePendingTransition(0, 0);



            }
        });
    }

}
