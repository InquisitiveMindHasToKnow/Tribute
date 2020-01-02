package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;


public class AddPersonActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";
    public static final String PERSON_NOTES = "person_notes";
    private long lastButtonClickTime = 0;

    private Button addPersonButton;
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

        addPersonButton = findViewById(R.id.add_person_submit_button);
        addPersonButton.setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();

            Intent addPersonIntent = new Intent(AddPersonActivity.this, CreateListActivity.class);

            if (TextUtils.isEmpty(addPersonNameEditText.getText()) || TextUtils.isEmpty(addPersonNumberEditText.getText())
                    || TextUtils.isEmpty(addPersonEmailEditText.getText()) || TextUtils.isEmpty(addPersonNotesEditText.getText())) {
                setResult(RESULT_CANCELED, addPersonIntent);
                Toast.makeText(AddPersonActivity.this, "All fields must be filled.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(AddPersonActivity.this, "Person Data Added", Toast.LENGTH_LONG).show();
                finish();

            }

        });
    }
}