package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddPersonActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";

    private Button addPersonButton;
    private PersonRepository personRepository;
    private List<Person> personList = new ArrayList<>();
    private EditText addPersonNameEditText;
    private EditText addPersonNumberEditText;
    private EditText addPersonEmailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        addPersonNameEditText = findViewById(R.id.add_person_name_edittext);
        addPersonNumberEditText = findViewById(R.id.add_person_number_edittext);
        addPersonEmailEditText = findViewById(R.id.add_person_email_edittext);

        PersonDatabase personDatabase = PersonDatabase.getInstance(this);
        personRepository = PersonRepository.getInstance(PersonDataSource.getPersonInstance(personDatabase.personDao()));

        addPersonButton = findViewById(R.id.add_person_submit_button);
        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveIntent = new Intent(AddPersonActivity.this, CreateListActivity.class);
                Person person = new Person(addPersonNameEditText.getText().toString(),
                        addPersonNumberEditText.toString(), addPersonEmailEditText.getText().toString());

                        personList.add(person);


                if (TextUtils.isEmpty(addPersonNameEditText.getText()) || TextUtils.isEmpty(addPersonNumberEditText.getText())
                        || TextUtils.isEmpty(addPersonEmailEditText.getText())){
                    setResult(RESULT_CANCELED, saveIntent);
                } else {
                    String person_name= addPersonNameEditText.getText().toString();
                    String person_number = addPersonNumberEditText.getText().toString();
                    String person_email = addPersonEmailEditText.getText().toString();


                    saveIntent.putExtra(PERSON_NAME, person_name);
                    saveIntent.putExtra(PERSON_NUMBER, person_number);
                    saveIntent.putExtra(PERSON_EMAIL, person_email);

                    setResult(RESULT_OK, saveIntent);

                    Toast.makeText(AddPersonActivity.this, "Person Data Added", Toast.LENGTH_LONG).show();


                    Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                        @Override
                        public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                            Person person = new Person(person_name, person_number, person_email);

                            personRepository.addPerson(person);
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
                                    Toast.makeText(AddPersonActivity.this, "Error Adding Person" + throwable.getMessage(), Toast.LENGTH_LONG).show();
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