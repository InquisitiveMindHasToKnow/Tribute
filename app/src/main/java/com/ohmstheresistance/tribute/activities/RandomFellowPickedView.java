package com.ohmstheresistance.tribute.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ohmstheresistance.tribute.R;

public class RandomFellowPickedView extends AppCompatActivity {

    private TextView chosenFellowTextView;
    private ImageView chosenFellowImageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_fellow_picked_view);

        chosenFellowTextView = findViewById(R.id.randomly_selected_fellow_textview);
        chosenFellowImageview = findViewById(R.id.i_choose_you_gif_imageview);

         Glide.with(this).load(R.drawable.ichooseyou).into(chosenFellowImageview);
    }
}
