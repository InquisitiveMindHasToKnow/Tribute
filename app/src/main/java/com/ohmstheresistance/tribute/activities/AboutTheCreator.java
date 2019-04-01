package com.ohmstheresistance.tribute.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.fragments.ContactCreatorFragment;
import com.ohmstheresistance.tribute.fragments.CreatorsThankYou;
import com.ohmstheresistance.tribute.rv.CreatorsInfoAdapter;

public class AboutTheCreator extends AppCompatActivity {


    private static final String TAG = "About The Creator";
    private ViewPager viewPager;
    //private CreatorsInfoAdapter creatorsInfoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_creator);

        //creatorsInfoAdapter = new CreatorsInfoAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        setUpViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {

        CreatorsInfoAdapter creatorsInfoAdapter = new CreatorsInfoAdapter(getSupportFragmentManager());
        creatorsInfoAdapter.addFragments(new ContactCreatorFragment(), "Contact Me");
        creatorsInfoAdapter.addFragments(new CreatorsThankYou(), "Thank You Message");
        viewPager.setAdapter(creatorsInfoAdapter);
    }
}


