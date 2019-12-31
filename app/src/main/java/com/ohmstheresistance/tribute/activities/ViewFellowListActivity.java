package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewFellowListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private static final String TAG = "FellowJSON.TAG";
    private TextView allstar_textview;
    private FellowAdapter fellowAdapter;
    private RecyclerView fellowRecyclerView;
    private Button pickRandomFellowButton;
    private SearchView fellowSearchView;
    private long lastButtonClickTime = 0;
    private static final String RANDOM_FELLOW_KEY = "randomFellowKey";

    private List<Fellows> fellowList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fellow_list);
        fellowRecyclerView = findViewById(R.id.fellow_list_recycler);
        allstar_textview = findViewById(R.id.allstar_fellow_textview);
        fellowSearchView = findViewById(R.id.fellow_search_view);
        pickRandomFellowButton = findViewById(R.id.pick_random_fellow_button);

        fellowSearchView.setOnQueryTextListener(ViewFellowListActivity.this);
        fellowSearchView.setIconified(false);
        fellowSearchView.setFocusable(false);
        fellowSearchView.setIconified(false);
        fellowSearchView.clearFocus();


        pickRandomFellowButton.setOnClickListener(v -> {
            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();



            Random randomNumber = new Random();
            Fellows randomFellowPicked = fellowList.get(randomNumber.nextInt(fellowList.size() - 1) + 1);
            Intent randomFellowIntent = new Intent(getApplicationContext(), RandomFellowPickedActivity.class);
            randomFellowIntent.putExtra(RANDOM_FELLOW_KEY, randomFellowPicked.getFellow());

            fellowList.remove(randomFellowPicked);
            fellowAdapter.setData(fellowList);
            startActivity(randomFellowIntent);
        });

        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        FellowService fellowService = retrofit.create(FellowService.class);
        fellowService.getFellows().enqueue(new Callback<FellowAPI>() {
            @Override
            public void onResponse(Call<FellowAPI> call, Response<FellowAPI> response) {
                Log.d(TAG, "Fellow retrofit call works, Omar!" + response.body().getFellows().get(0));


                for (Fellows fellows : response.body().getFellows()) {
                    fellowList.add(fellows);

                    if (fellowList.size() <= 1) {
                        fellowList.add(fellows);
                    }
                }
                fellowAdapter = new FellowAdapter(response.body().getFellows());
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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Fellows> newFellowList = new ArrayList<>();
        for (Fellows fellows : fellowList) {

            if (fellows.getFellow().toLowerCase().contains(s.toLowerCase())) {
                newFellowList.add(fellows);
            }
        }
        fellowAdapter.setData(newFellowList);
        return false;
    }
}

