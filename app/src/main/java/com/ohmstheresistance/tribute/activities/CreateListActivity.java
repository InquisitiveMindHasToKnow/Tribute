package com.ohmstheresistance.tribute.activities;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonViewModel;
import com.ohmstheresistance.tribute.rv.PersonAdapter;

import java.util.List;
import java.util.Random;


public class CreateListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final int ADD_PERSON_RESULT_CODE = 1;
    public static final int EDIT_PERSON_REQUEST_CODE = 2;
    private PersonViewModel personViewModel;
    private RecyclerView personRecyclerView;
    private FloatingActionButton personFab;

    private Intent addPersonIntent;
    private Button selectRandomPerson;
    private long lastButtonClickTime = 0;
    private SearchView personSearchView;

    private LinearLayout createListLinearLayout;
    private String createListActivitySnackBarText;

    private static final String RANDOM_PERSON_KEY = "randomPersonKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        personSearchView = findViewById(R.id.person_search_view);
        personRecyclerView = findViewById(R.id.create_person_recycler_view);
        personFab = findViewById(R.id.create_person_action_button);
        selectRandomPerson = findViewById(R.id.select_random_person_button);
        createListLinearLayout = findViewById(R.id.createlistlinear);

        personRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        personRecyclerView.setHasFixedSize(false);

        final PersonAdapter personAdapter = new PersonAdapter();
        personRecyclerView.setAdapter(personAdapter);

        personSearchView.setIconified(false);
        personSearchView.setFocusable(false);
        personSearchView.setIconified(false);
        personSearchView.clearFocus();

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        personViewModel.getAllPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {

                personAdapter.submitList(people);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                int position = viewHolder.getAdapterPosition();
                Person personToDelete = personAdapter.getPersonAtPosition(position);

                personViewModel.deletePerson(personToDelete);

                createListActivitySnackBarText = "Person data deleted.";
                displayCreateListActivitySnackbar();
            }

        }).attachToRecyclerView(personRecyclerView);


        selectRandomPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                if (personViewModel.getAllPersons().getValue().isEmpty()) {

                    createListActivitySnackBarText = "Cannot generate random person from an empty list.";
                    displayCreateListActivitySnackbar();

                } else {

                    if (personViewModel.getAllPersons().getValue().size() <= 1) {

                        createListActivitySnackBarText = "There's only one person remaining.";
                        displayCreateListActivitySnackbar();

                        return;
                    }

                    Random randomNumber = new Random();
                    Person randomPersonPicked = personViewModel.getAllPersons().getValue()
                            .get(randomNumber.nextInt(personViewModel.getAllPersons().getValue().size()));

                    Intent randomPersonIntent = new Intent(CreateListActivity.this, RandomPersonPickedActivity.class);
                    randomPersonIntent.putExtra(RANDOM_PERSON_KEY, randomPersonPicked.getPersonName());

                    startActivity(randomPersonIntent);
                }
            }
        });


        personAdapter.SetItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Person person) {

                Intent editPersonIntent = new Intent(CreateListActivity.this, EditPersonDataActivity.class);
                editPersonIntent.putExtra(EditPersonDataActivity.PERSON_ID, person.getPersonID());
                editPersonIntent.putExtra(EditPersonDataActivity.PERSON_NAME, person.getPersonName());
                editPersonIntent.putExtra(EditPersonDataActivity.PERSON_NUMBER, person.getPersonPhoneNumber());
                editPersonIntent.putExtra(EditPersonDataActivity.PERSON_EMAIL, person.getPersonEmail());
                editPersonIntent.putExtra(EditPersonDataActivity.PERSON_NOTES, person.getPersonNotes());
                CreateListActivity.this.startActivityForResult(editPersonIntent, EDIT_PERSON_REQUEST_CODE);

            }
        });
        {


            personFab.setOnClickListener(v -> {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                addPersonIntent = new Intent(CreateListActivity.this, AddPersonActivity.class);
                startActivityForResult(addPersonIntent, ADD_PERSON_RESULT_CODE);

            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.database_delete_all:

                if (personViewModel.getAllPersons().getValue().isEmpty()) {

                    createListActivitySnackBarText = "Nothing to delete.";
                    displayCreateListActivitySnackbar();

                } else {

                    deleteDatabase();
                }

                break;

        }
        return true;
    }

    private void deleteDatabase() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateListActivity.this, R.style.CustomAlertDialog);
        dialog.setCancelable(false);
        dialog.setTitle("This option deletes the entire database!");
        dialog.setMessage("Are you sure you want to complete this action?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int id) {

                personViewModel.deleteAllPersons();

                createListActivitySnackBarText = "All information erased.";
                displayCreateListActivitySnackbar();
            }
        })

                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog12, int which) {

                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PERSON_RESULT_CODE && resultCode == RESULT_OK) {

            String personName = data.getStringExtra(AddPersonActivity.PERSON_NAME);
            String personNumber = data.getStringExtra(AddPersonActivity.PERSON_NUMBER);
            String personEmail = data.getStringExtra(AddPersonActivity.PERSON_EMAIL);
            String personNotes = data.getStringExtra(AddPersonActivity.PERSON_NOTES);

            Person person = new Person(personName, personNumber, personEmail, personNotes);
            personViewModel.addPerson(person);

            createListActivitySnackBarText = "Person data added.";
            displayCreateListActivitySnackbar();

        } else if (requestCode == EDIT_PERSON_REQUEST_CODE && resultCode == RESULT_OK) {
            int personID = data.getIntExtra(EditPersonDataActivity.PERSON_ID, -1);

            if (personID == -1) {

                createListActivitySnackBarText = "Person info can't be updated.";
                displayCreateListActivitySnackbar();
                return;
            }

            String personName = data.getStringExtra(EditPersonDataActivity.PERSON_NAME);
            String personNumber = data.getStringExtra(EditPersonDataActivity.PERSON_NUMBER);
            String personEmail = data.getStringExtra(EditPersonDataActivity.PERSON_EMAIL);
            String personNotes = data.getStringExtra(EditPersonDataActivity.PERSON_NOTES);


            Person personToEdit = new Person(personName, personNumber, personEmail, personNotes);
            personToEdit.setPersonID(personID);
            personViewModel.updatePerson(personToEdit);

            createListActivitySnackBarText = "Person data updated";
            displayCreateListActivitySnackbar();

        } else {

            createListActivitySnackBarText = "Person data not updated.";
            displayCreateListActivitySnackbar();
        }
    }

    private void displayCreateListActivitySnackbar() {
        Snackbar createListSnackbar = Snackbar.make(createListLinearLayout, createListActivitySnackBarText, Snackbar.LENGTH_LONG);
        View snackbarView = createListSnackbar.getView();
        TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);

        snackBarTextView.setBackgroundResource(R.drawable.snackbar_background);
        snackBarTextView.setTextSize(16);
        snackBarTextView.setTypeface(snackBarTextView.getTypeface(), Typeface.BOLD_ITALIC);
        snackBarTextView.setGravity(Gravity.CENTER);
        snackBarTextView.setTextColor(getResources().getColor(R.color.mainBackgroundColor));
        snackbarView.setBackgroundColor(Color.TRANSPARENT);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
        layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        layoutParams.bottomMargin = 120;
        snackbarView.setLayoutParams(layoutParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        createListSnackbar.show();
    }
}







