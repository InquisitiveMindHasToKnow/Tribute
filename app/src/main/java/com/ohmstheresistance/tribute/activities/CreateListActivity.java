package com.ohmstheresistance.tribute.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.rv.PersonAdapter;
import com.ohmstheresistance.tribute.rv.PersonViewModel;
import com.ohmstheresistance.tribute.rv.PersonViewModelFactory;

import java.util.List;

public class CreateListActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;
    private RecyclerView personRecyclerView;
    private Button addPersonButton;
    private Button updatePersonButton;
    private Button deleteAllPersonsButton;
    private Button savePersonInfoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        personRecyclerView = findViewById(R.id.create_person_recycler_view);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(false);

        final PersonAdapter adapter = new PersonAdapter();
        personRecyclerView.setAdapter(adapter);

        addPersonButton = findViewById(R.id.add_person_button);
        updatePersonButton = findViewById(R.id.update_person_button);
        savePersonInfoButton = findViewById(R.id.save_person_info_button);
        deleteAllPersonsButton = findViewById(R.id.delete_all_persons_button);

        personViewModel = ViewModelProviders.of(this, new PersonViewModelFactory(getApplication())).get(PersonViewModel.class);
        personViewModel.allPersons.observe(this, adapter::setPersons);

        personViewModel.getAllPersons();
    }
}
