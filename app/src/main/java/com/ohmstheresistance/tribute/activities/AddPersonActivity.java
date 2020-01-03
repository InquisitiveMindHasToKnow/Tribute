package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;


public class AddPersonActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";
    public static final String PERSON_NOTES = "person_notes";
    private long lastButtonClickTime = 0;
    private ConstraintLayout addPersonActivityConstraintLayout;

    private Button addPersonButton;
    private EditText addPersonNameEditText;
    private EditText addPersonNumberEditText;
    private EditText addPersonEmailEditText;
    private EditText addPersonNotesEditText;

    private String addPersonDataSnackBarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        addPersonNameEditText = findViewById(R.id.add_person_name_edittext);
        addPersonNumberEditText = findViewById(R.id.add_person_number_edittext);
        addPersonEmailEditText = findViewById(R.id.add_person_email_edittext);
        addPersonNotesEditText = findViewById(R.id.add_person_notes_edittext);
        addPersonActivityConstraintLayout = findViewById(R.id.addpersonactivity_constraint);

        overridePendingTransition(0, 0);

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

                addPersonDataSnackBarText = "All fields must be filled.";
                displayAddPersonDataSnackbar();

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

                finish();

            }

        });
    }

    private void displayAddPersonDataSnackbar(){

        Snackbar addPersonSnackbar = Snackbar.make(addPersonActivityConstraintLayout, addPersonDataSnackBarText, Snackbar.LENGTH_LONG);
        View snackbarView = addPersonSnackbar.getView();
        TextView snackBarTextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);

        snackBarTextView.setBackgroundResource(R.drawable.snackbar_background);
        snackBarTextView.setTextSize(16);
        snackBarTextView.setTypeface(snackBarTextView.getTypeface(), Typeface.BOLD_ITALIC);
        snackBarTextView.setGravity(Gravity.CENTER);
        snackBarTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        snackbarView.setBackgroundColor(Color.TRANSPARENT);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
        layoutParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM|Gravity.CENTER;
        layoutParams.bottomMargin = 120;
        snackbarView.setLayoutParams(layoutParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        addPersonSnackbar.show();
    }
}