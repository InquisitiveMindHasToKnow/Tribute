package com.ohmstheresistance.tribute.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDataSource;
import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.database.PersonRepository;
import com.ohmstheresistance.tribute.rv.PersonAdapter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class CreateListActivity extends AppCompatActivity {

    private RecyclerView personRecyclerView;
    private FloatingActionButton personFab;
    private CompositeDisposable compositeDisposable;
    private PersonRepository personRepository;

    private List<Person> personList;
    private PersonAdapter personAdapter;
    private Intent addPersonIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        personRecyclerView = findViewById(R.id.create_person_recycler_view);

        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personRecyclerView.setHasFixedSize(true);
        personAdapter = new PersonAdapter(this);
        personRecyclerView.setAdapter(personAdapter);

        personFab = findViewById(R.id.create_person_action_button);

        compositeDisposable = new CompositeDisposable();

        PersonDatabase personDatabase = PersonDatabase.getInstance(this);
        personRepository = PersonRepository.getInstance(PersonDataSource.getPersonInstance(personDatabase.personDao()));

        getInfo();

        personFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPersonIntent = new Intent(CreateListActivity.this, AddPersonActivity.class);
                startActivity(addPersonIntent);

            }
        });
    }

    private void getInfo() {

        Disposable disposable = personRepository.getAllPersons()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> people) throws Exception {
                        onGetInfoSuccess(people);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(CreateListActivity.this, "Get person list info failed" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);

    }

    private void onGetInfoSuccess(List<Person> people) {

        people.size();
        personAdapter.setPersons(people);
        personAdapter.notifyDataSetChanged();
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
            public void onClick(DialogInterface dialog, int id) {

                Toast.makeText(CreateListActivity.this, "Database Cleared", Toast.LENGTH_LONG).show();
                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                        personRepository.deleteAllPersons();
                        emitter.onComplete();

                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Object>() {

                            @Override
                            public void accept(Object o) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(CreateListActivity.this, "Database Cleared" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
                // compositeDisposable.add(disposable);

            }

        })

                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    }


}







