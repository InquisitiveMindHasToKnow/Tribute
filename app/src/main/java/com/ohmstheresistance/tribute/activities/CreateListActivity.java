package com.ohmstheresistance.tribute.activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDataSource;
import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.database.PersonRepository;
import com.ohmstheresistance.tribute.rv.PersonAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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

                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        Person person = new Person("Omar", "9191919191","soloproject@omarraymond.org");
                        Person person2 = new Person ("Catherine", "4445554333","catherine@google.gov");
                        personRepository.addPerson(person);
                        emitter.onComplete();
                    }
                })

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Object>() {

                            @Override
                            public void accept(Object o) throws Exception {
                                Toast.makeText(CreateListActivity.this, "Person Added", Toast.LENGTH_LONG).show();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(CreateListActivity.this, "Error Adding Person" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                getInfo();
                            }
                        });
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
                        Toast.makeText(CreateListActivity.this, "Get person info failed" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);

    }

    private void onGetInfoSuccess(List<Person> people) {

        people.size();
        personAdapter.setPersons(people);
        personAdapter.notifyDataSetChanged();
    }

}


