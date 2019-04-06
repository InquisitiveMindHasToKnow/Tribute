package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDataSource;
import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.database.PersonRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EditPersonDataActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";
    public static final String PERSON_ID = "person_id";
    private long lastButtonClickTime = 0;

    private Button editPersonButton;
    private PersonRepository personRepository;
    private EditText editPersonNameEditText;
    private EditText editPersonNumberEditText;
    private EditText editPersonEmailEditText;
    private Intent editIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_data);

        editPersonNameEditText = findViewById(R.id.edit_person_name_edittext);
        editPersonNumberEditText = findViewById(R.id.edit_person_number_edittext);
        editPersonEmailEditText = findViewById(R.id.edit_person_email_edittext);
        editPersonButton = findViewById(R.id.edit_person_submit_button);

        PersonDatabase personDatabase = PersonDatabase.getInstance(this);
        personRepository = PersonRepository.getInstance(PersonDataSource.getPersonInstance(personDatabase.personDao()));


        editIntent = getIntent();

        if (editIntent.hasExtra(PERSON_ID)) {
            editPersonNameEditText.setText(editIntent.getStringExtra(PERSON_NAME));
            editPersonNumberEditText.setText(editIntent.getStringExtra(PERSON_NUMBER));
            editPersonEmailEditText.setText(editIntent.getStringExtra(PERSON_EMAIL));

        }

        editPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                Intent modifyPersonIntent = new Intent(EditPersonDataActivity.this, CreateListActivity.class);

                if (TextUtils.isEmpty(editPersonNameEditText.getText()) || TextUtils.isEmpty(editPersonNumberEditText.getText())
                        || TextUtils.isEmpty(editPersonEmailEditText.getText())) {
                    setResult(RESULT_CANCELED, modifyPersonIntent);
                    Toast.makeText(EditPersonDataActivity.this, "Person Data Not Modified. All fields must be filled!", Toast.LENGTH_LONG).show();


                } else {

                    String personName = editPersonNameEditText.getText().toString();
                    String personNumber = editPersonNumberEditText.getText().toString();
                    String personEmail = editPersonEmailEditText.getText().toString();


                    modifyPersonIntent.putExtra(PERSON_NAME, personName);
                    modifyPersonIntent.putExtra(PERSON_NUMBER, personNumber);
                    modifyPersonIntent.putExtra(PERSON_EMAIL, personEmail);

                    int personID = getIntent().getIntExtra(PERSON_ID, -1);
                    if (personID != -1) {
                        modifyPersonIntent.putExtra(PERSON_ID, personID);

                    }

                    setResult(RESULT_OK, modifyPersonIntent);

                    Person person = new Person(personName, personNumber, personEmail);
                    person.setPersonID(personID);


                    Toast.makeText(EditPersonDataActivity.this, "Person Data Updated", Toast.LENGTH_LONG).show();

                    Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                        @Override
                        public void subscribe(ObservableEmitter<Object> emitter) throws Exception {


                            personRepository.updatePerson(person);
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
                                    Toast.makeText(EditPersonDataActivity.this, "Error Updating Person Data" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {

                                }
                            });

                }
                finish();
            }
        });

    }
}


