package com.ohmstheresistance.tribute.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button infoFromInternetButton;
    private Button generateOwnListButton;
    private ImageView welcomeScreenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textview);
        infoFromInternetButton = findViewById(R.id.info_from_internet_button);
        generateOwnListButton = findViewById(R.id.generate_own_list_button);
        welcomeScreenImageView = findViewById(R.id.welcome_screen_imageview);

        Picasso.get()
                .load(R.drawable.guesswho)
                .into(welcomeScreenImageView);
    }
}
