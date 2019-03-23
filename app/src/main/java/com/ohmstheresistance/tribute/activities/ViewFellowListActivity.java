package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.FellowAPI;
import com.ohmstheresistance.tribute.model.Fellows;
import com.ohmstheresistance.tribute.network.RetrofitSingleton;
import com.ohmstheresistance.tribute.network.FellowService;
import com.ohmstheresistance.tribute.rv.FellowAdapter;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewFellowListActivity extends AppCompatActivity {


    private static final String TAG = "FellowJSON.TAG";
    private TextView allstar_textview;
    private RecyclerView fellowRecyclerView;
    private Button pickRandomFellowButton;
    private List<Fellows> fellowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fellow_list);
        fellowRecyclerView = findViewById(R.id.fellow_list_recycler);
        allstar_textview = findViewById(R.id.allstar_fellow_textview);
        pickRandomFellowButton = findViewById(R.id.pick_random_fellow_button);

        pickRandomFellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomFellow = new Random();
               // Fellows fellows = fellowList.get(randomFellow.nextInt(fellowList.size()));
                Intent randomFellowIntent =  new Intent(getApplicationContext(), RandomFellowPickedView.class);
                startActivity(randomFellowIntent);
            }
        });

        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        FellowService fellowService = retrofit.create(FellowService.class);
        fellowService.getFellows().enqueue(new Callback<FellowAPI>() {
            @Override
            public void onResponse(Call<FellowAPI> call, Response<FellowAPI> response) {
                Log.d(TAG, "Fellow retrofit call works, Omar!" + response.body().getMessage().get(0));
                FellowAdapter fellowAdapter = new FellowAdapter(response.body().getMessage());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                fellowRecyclerView.setLayoutManager(gridLayoutManager);
                fellowRecyclerView.setAdapter(fellowAdapter);

            }

            @Override
            public void onFailure(Call<FellowAPI> call, Throwable t) {
                Log.d(TAG, "Fellow retrofit call failed! " + t.getMessage());
            }
        });

    }



}

