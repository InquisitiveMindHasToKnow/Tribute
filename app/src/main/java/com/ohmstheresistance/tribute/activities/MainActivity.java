package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button infoFromInternetButton;
    private Button generateOwnListButton;
    private Button pickRandomPerson;
    private ImageView welcomeScreenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textview);
        welcomeScreenImageView = findViewById(R.id.welcome_screen_imageview);
        pickRandomPerson = findViewById(R.id.pick_random_person_button);
        generateOwnListButton = findViewById(R.id.generate_own_list_button);
        infoFromInternetButton = findViewById(R.id.info_from_internet_button);

        Picasso.get()
                .load(R.drawable.guesswho)
                .into(welcomeScreenImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.omarsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.omars_github:
                Uri githubUri = Uri.parse("https://github.com/InquisitiveMindHasToKnow");
                Intent githubIntent = new Intent(Intent.ACTION_VIEW, githubUri);
                startActivity(githubIntent);
                break;

            case R.id.omars_linkedin:
                Uri linkedInUri = Uri.parse("https://www.linkedin.com/");
                Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, linkedInUri);
                startActivity(linkedInIntent);
                break;
        }

        return true;
    }
}
