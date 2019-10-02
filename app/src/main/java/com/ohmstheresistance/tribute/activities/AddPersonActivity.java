package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDataSource;
import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.database.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddPersonActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";
    public static final String PERSON_NOTES = "person_notes";
    private long lastButtonClickTime = 0;

    private Button addPersonButton;
    private PersonRepository personRepository;
    private List<Person> personList = new ArrayList<>();
    private EditText addPersonNameEditText;
    private EditText addPersonNumberEditText;
    private EditText addPersonEmailEditText;
    private EditText addPersonNotesEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        addPersonNameEditText = findViewById(R.id.add_person_name_edittext);
        addPersonNumberEditText = findViewById(R.id.add_person_number_edittext);
        addPersonEmailEditText = findViewById(R.id.add_person_email_edittext);
        addPersonNotesEditText = findViewById(R.id.add_person_notes_edittext);

        PersonDatabase personDatabase = PersonDatabase.getInstance(this);
        personRepository = PersonRepository.getInstance(PersonDataSource.getPersonInstance(personDatabase.personDao()));

        addPersonButton = findViewById(R.id.add_person_submit_button);
        addPersonButton.setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();

            Intent addPersonIntent = new Intent(AddPersonActivity.this, CreateListActivity.class);
            Person person = new Person(addPersonNameEditText.getText().toString(),
                    addPersonNumberEditText.toString(), addPersonEmailEditText.getText().toString(), addPersonNotesEditText.getText().toString());

            if (TextUtils.isEmpty(addPersonNameEditText.getText()) || TextUtils.isEmpty(addPersonNumberEditText.getText())
                    || TextUtils.isEmpty(addPersonEmailEditText.getText())  || TextUtils.isEmpty(addPersonNotesEditText.getText())) {
                setResult(RESULT_CANCELED, addPersonIntent);
                Toast.makeText(AddPersonActivity.this, "Person Data Not Added. All fields must be filled.", Toast.LENGTH_LONG).show();
            } else {
                String person_name = addPersonNameEditText.getText().toString();
                String person_number = addPersonNumberEditText.getText().toString();
                String person_email = addPersonEmailEditText.getText().toString();
                String person_notes = addPersonNotesEditText.getText().toString();

                addPersonIntent.putExtra(PERSON_NAME, person_name);
                addPersonIntent.putExtra(PERSON_NUMBER, person_number);
                addPersonIntent.putExtra(PERSON_EMAIL, person_email);
                addPersonIntent.putExtra(PERSON_NOTES, person_notes);

                setResult(RESULT_OK, addPersonIntent);
                personList.add(person);

                Toast.makeText(AddPersonActivity.this, "Person Data Added", Toast.LENGTH_LONG).show();


                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) {
                        Person person = new Person(person_name, person_number, person_email, person_notes);
                        personRepository.addPerson(person);

                        emitter.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(o -> {

                        }, throwable -> Toast.makeText(AddPersonActivity.this, "Error Adding Person" + throwable.getMessage(), Toast.LENGTH_LONG).show(), new Action() {
                            @Override
                            public void run() {

                            }
                        });

            }
            finish();

        });
    }

}