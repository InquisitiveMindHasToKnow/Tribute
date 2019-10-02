package com.ohmstheresistance.tribute.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDataSource;
import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.database.PersonRepository;
import com.ohmstheresistance.tribute.rv.PersonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class CreateListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView personRecyclerView;
    private FloatingActionButton personFab;
    private CompositeDisposable compositeDisposable;
    private PersonRepository personRepository;

    private List<Person> personList;
    private PersonAdapter personAdapter;
    private Intent addPersonIntent;
    private Intent cancelRandomIntent;
    private Button selectRandomPerson;
    private long lastButtonClickTime = 0;
    private SearchView personSearchView;


    private static final String RANDOM_PERSON_KEY = "randomPersonKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        personSearchView = findViewById(R.id.person_search_view);
        personRecyclerView = findViewById(R.id.create_person_recycler_view);
        personList = new ArrayList<>();
        personSearchView.setIconified(false);

        personRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        personRecyclerView.setHasFixedSize(true);
        personAdapter = new PersonAdapter(this);
        personRecyclerView.setAdapter(personAdapter);

        personFab = findViewById(R.id.create_person_action_button);
        selectRandomPerson = findViewById(R.id.select_random_person_button);

        compositeDisposable = new CompositeDisposable();

        PersonDatabase personDatabase = PersonDatabase.getInstance(this);
        personRepository = PersonRepository.getInstance(PersonDataSource.getPersonInstance(personDatabase.personDao()));

        getInfo();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                int position = viewHolder.getAdapterPosition();
                Person person = personAdapter.getPersonAtPosition(position);

                Toast.makeText(CreateListActivity.this, "Person Data Removed", Toast.LENGTH_LONG).show();
                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) {
                        //personList.remove(person);
                        personRepository.deletePerson(person);
                        Log.e("personListafterdelete: ", personList.size() + "");
                        emitter.onComplete();


                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(o -> personAdapter.deletePerson(position), throwable ->

                                Toast.makeText(CreateListActivity.this, "Error Removing Person Info" + throwable.getMessage(), Toast.LENGTH_LONG).show(), () -> {

                        });
                compositeDisposable.add(disposable);

            }

        }).attachToRecyclerView(personRecyclerView);

        selectRandomPerson.setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();

            if (personList.size() == 0) {
                cancelRandomIntent = new Intent(CreateListActivity.this, CreateListActivity.class);
                Toast.makeText(CreateListActivity.this, "Cannot Generate Random Person From An Empty List", Toast.LENGTH_LONG).show();

            } else {

                if (personList.size() <= 1) {
                    getInfo();
                }

                Random randomNumber = new Random();
                Person randomPersonPicked = personList.get(randomNumber.nextInt(personList.size()));
                Intent randomPersonIntent = new Intent(CreateListActivity.this, AnxietyBuilderActivity.class);
                randomPersonIntent.putExtra(RANDOM_PERSON_KEY, randomPersonPicked.getPersonName());

                personList.remove(randomPersonPicked);
                startActivity(randomPersonIntent);
            }
        });


        personAdapter.SetItemClickListener(person -> {

            Intent editPersonIntent = new Intent(CreateListActivity.this, EditPersonDataActivity.class);
            editPersonIntent.putExtra(EditPersonDataActivity.PERSON_ID, person.getPersonID());
            editPersonIntent.putExtra(EditPersonDataActivity.PERSON_NAME, person.getPersonName());
            editPersonIntent.putExtra(EditPersonDataActivity.PERSON_NUMBER, person.getPersonPhoneNumber());
            editPersonIntent.putExtra(EditPersonDataActivity.PERSON_EMAIL, person.getPersonEmail());
            startActivity(editPersonIntent);

        });
        {


            personFab.setOnClickListener(v -> {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                addPersonIntent = new Intent(CreateListActivity.this, AddPersonActivity.class);
                startActivity(addPersonIntent);

            });
        }
    }

    private void getInfo() {

        Disposable disposable = personRepository.getAllPersons()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(people -> {
                    onGetInfoSuccess(people);
                    personSearchView.setOnQueryTextListener(CreateListActivity.this);
                    personList.addAll(people);

                }, throwable -> Toast.makeText(CreateListActivity.this, "Get person list info failed" + throwable.getMessage(), Toast.LENGTH_LONG).show());
        compositeDisposable.add(disposable);

    }

    private void onGetInfoSuccess(List<Person> people) {
        personAdapter.setPersons(people);

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

                deleteDatabase();

                break;

        }
        return true;
    }

    private void deleteDatabase() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateListActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("This option deletes the entire database!");
        dialog.setMessage("Are you sure you want to complete this action?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int id) {

                Toast.makeText(CreateListActivity.this, "Database Cleared", Toast.LENGTH_LONG).show();
                Disposable disposable = Observable.create((ObservableEmitter<Object> emitter) -> {

                    personList.clear();
                    personRepository.deleteAllPersons();
                    emitter.onComplete();

                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(o -> {

                        }, throwable -> Toast.makeText(CreateListActivity.this, "Database Cleared" + throwable.getMessage(), Toast.LENGTH_LONG).show(), new Action() {
                            @Override
                            public void run() {

                            }
                        });
                compositeDisposable.add(disposable);

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
        List<Person> newFellowList = new ArrayList<>();
        for (Person persons : personList) {
            if (persons.getPersonName().toLowerCase().contains(s.toLowerCase())) {
                newFellowList.add(persons);
            }

        }

        personAdapter.setData(newFellowList);
        return false;
    }

}







