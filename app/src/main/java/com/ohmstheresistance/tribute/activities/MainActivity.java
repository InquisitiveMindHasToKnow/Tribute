package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.ButtonAPI;
import com.ohmstheresistance.tribute.network.ButtonRetrofit;
import com.ohmstheresistance.tribute.network.ButtonService;
import com.ohmstheresistance.tribute.rv.ButtonAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ButtonJSON.TAG";
    private RecyclerView buttonRecyclerView;

    private TextView welcomeTextView;
    private ImageView welcomeScreenImageView;
    private Intent navigationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcome_textview);
        welcomeScreenImageView = findViewById(R.id.welcome_screen_imageview);
        buttonRecyclerView = findViewById(R.id.button_recycler);

        Picasso.get()
                .load(R.drawable.guesswho)
                .into(welcomeScreenImageView);

        Retrofit retrofit = ButtonRetrofit.getRetrofitInstance();
        ButtonService buttonService = retrofit.create(ButtonService.class);
        buttonService.getButtons().enqueue(new Callback<ButtonAPI>() {
            @Override
            public void onResponse(Call<ButtonAPI> call, Response<ButtonAPI> response) {
                Log.d(TAG, "This retrofit call works, Omar!" + response.body().getMessage().get(0));
                ButtonAdapter adapter = new ButtonAdapter(response.body().getMessage());
                buttonRecyclerView.setAdapter(adapter);
                buttonRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<ButtonAPI> call, Throwable t) {
                Log.d(TAG, "Retrofit call failed! " + t.getMessage());
            }
        });

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
                Uri linkedInUri = Uri.parse("https://www.linkedin.com/in/omar-raymond-7411a5b2/");
                Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, linkedInUri);
                startActivity(linkedInIntent);
                break;
        }
        return true;
    }

}
