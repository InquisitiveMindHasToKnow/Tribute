package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.ButtonAPI;
import com.ohmstheresistance.tribute.network.RetrofitSingleton;
import com.ohmstheresistance.tribute.network.ButtonService;
import com.ohmstheresistance.tribute.rv.ButtonAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ButtonJSON.TAG";
    private static final String USER_NAME_KEY = "currentUser";
    private RecyclerView buttonRecyclerView;
    private Intent userNameIntent;
    private TextView welcomeTextView;
    private ImageView welcomeScreenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textview);
        welcomeScreenImageView = findViewById(R.id.welcome_screen_imageview);
        buttonRecyclerView = findViewById(R.id.button_recycler);

        Picasso.get()
                .load(R.drawable.mainpagedie)
                .into(welcomeScreenImageView);

        userNameIntent = getIntent();
        String userName = userNameIntent.getStringExtra(USER_NAME_KEY);
        welcomeTextView.setText("Welcome " + userName);

        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        ButtonService buttonService = retrofit.create(ButtonService.class);
        buttonService.getButtons().enqueue(new Callback<ButtonAPI>() {
            @Override
            public void onResponse(Call<ButtonAPI> call, Response<ButtonAPI> response) {
                Log.d(TAG, "Button retrofit call works, Omar!" + response.body().getMessage().get(0));
                ButtonAdapter adapter = new ButtonAdapter(response.body().getMessage());
                buttonRecyclerView.setAdapter(adapter);
                buttonRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<ButtonAPI> call, Throwable t) {
                Log.d(TAG, "Button retrofit call failed! " + t.getMessage());
            }
        });

    }

}
