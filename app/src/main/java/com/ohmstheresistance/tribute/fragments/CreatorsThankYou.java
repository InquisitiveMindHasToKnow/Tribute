package com.ohmstheresistance.tribute.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ohmstheresistance.tribute.R;


public class CreatorsThankYou extends Fragment {

    private TextView speechHeaderTextView;
    private TextView acceptanceSpeechTextView;
    private ImageView trophyAcceptanceImageView;
    private View rootView;

    public CreatorsThankYou() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_creators_thank_you, container, false);

        speechHeaderTextView = rootView.findViewById(R.id.speech_header_textview);
        acceptanceSpeechTextView = rootView.findViewById(R.id.acceptance_speech_textview);
        trophyAcceptanceImageView = rootView.findViewById(R.id.trophy_acceptance_imageview);

        Glide.with(this)
                .load(R.drawable.trophyraisingsiralextribute)
                .into(trophyAcceptanceImageView);

        return rootView;
    }

}
