package com.ohmstheresistance.tribute.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.rv.PersonViewModel;

import java.util.List;

public class CreateListActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        personViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> persons) {
                //update RecyclerView
                Toast.makeText(CreateListActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
