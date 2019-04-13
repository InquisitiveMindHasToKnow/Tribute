package com.ohmstheresistance.tribute.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ohmstheresistance.tribute.R;
import com.squareup.picasso.Picasso;


public class ContactCreatorFragment extends Fragment {

    private View rootView;
    private ImageView creatorsImage;
    private Button linkedInButton;
    private Button githubButton;

    public ContactCreatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contact_creator, container, false);

        creatorsImage = rootView.findViewById(R.id.creators_image);
        linkedInButton = rootView.findViewById(R.id.contact_creator_linkedIn_button);
        githubButton = rootView.findViewById(R.id.contact_creator_github_button);

        Picasso.get()
                .load(R.drawable.omarsimage)
                .into(creatorsImage);

        linkedInButton.setOnClickListener(v -> {
            Uri linkedInUri = Uri.parse("https://www.linkedin.com/in/omar-raymond-7411a5b2/");
            Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, linkedInUri);
            startActivity(linkedInIntent);
        });

        githubButton.setOnClickListener(v -> {
            Uri githubUri = Uri.parse("https://github.com/InquisitiveMindHasToKnow");
            Intent githubIntent = new Intent(Intent.ACTION_VIEW, githubUri);
            startActivity(githubIntent);
        });
        return rootView;
    }

}
