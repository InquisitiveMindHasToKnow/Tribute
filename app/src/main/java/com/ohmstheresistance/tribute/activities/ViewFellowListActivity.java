package com.ohmstheresistance.tribute.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.FellowAPI;
import com.ohmstheresistance.tribute.network.RetrofitSingleton;
import com.ohmstheresistance.tribute.network.FellowService;
import com.ohmstheresistance.tribute.rv.FellowAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewFellowListActivity extends AppCompatActivity {

    private static final String TAG = "FellowJSON.TAG";
    private RecyclerView fellowRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fellow_list);

        fellowRecyclerView = findViewById(R.id.fellow_list_recycler);

        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        FellowService fellowService = retrofit.create(FellowService.class);
        fellowService.getFellows().enqueue(new Callback<FellowAPI>() {
            @Override
            public void onResponse(Call<FellowAPI> call, Response<FellowAPI> response) {
                Log.d(TAG, "Fellow retrofit call works, Omar!" + response.body().getMessage().get(0));
                FellowAdapter fellowAdapter = new FellowAdapter(response.body().getMessage());
                fellowRecyclerView.setAdapter(fellowAdapter);
                fellowRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<FellowAPI> call, Throwable t) {
                Log.d(TAG, "Fellow retrofit call failed! " + t.getMessage());
            }
        });

    }

    }

