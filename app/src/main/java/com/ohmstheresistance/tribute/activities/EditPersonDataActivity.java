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


public class EditPersonDataActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_NUMBER = "person_number";
    public static final String PERSON_EMAIL = "person_email";
    public static final String PERSON_ID = "person_id";
    public static final String PERSON_NOTES = "person_notes";

    private long lastButtonClickTime = 0;

    private Button editPersonButton;
    private EditText editPersonNameEditText;
    private EditText editPersonNumberEditText;
    private EditText editPersonEmailEditText;
    private EditText editPersonNotesEditText;
    private Intent editIntent;

    private ConstraintLayout editPersonDataConstraintLayout;

    private String editPersonDataSnackBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_data);

        editPersonNameEditText = findViewById(R.id.edit_person_name_edittext);
        editPersonNumberEditText = findViewById(R.id.edit_person_number_edittext);
        editPersonEmailEditText = findViewById(R.id.edit_person_email_edittext);
        editPersonNotesEditText = findViewById(R.id.edit_person_notes_edittext);
        editPersonButton = findViewById(R.id.edit_person_submit_button);
        editPersonDataConstraintLayout = findViewById(R.id.editpersondata_constraint);

        overridePendingTransition(0, 0);

        editIntent = getIntent();

        if (editIntent.hasExtra(PERSON_ID)) {
            editPersonNameEditText.setText(editIntent.getStringExtra(PERSON_NAME));
            editPersonNumberEditText.setText(editIntent.getStringExtra(PERSON_NUMBER));
            editPersonEmailEditText.setText(editIntent.getStringExtra(PERSON_EMAIL));
            editPersonNotesEditText.setText(editIntent.getStringExtra(PERSON_NOTES));

        }

        editPersonButton.setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();

            Intent modifyPersonIntent = new Intent(EditPersonDataActivity.this, CreateListActivity.class);

            if (TextUtils.isEmpty(editPersonNameEditText.getText()) || TextUtils.isEmpty(editPersonNumberEditText.getText())
                    || TextUtils.isEmpty(editPersonEmailEditText.getText()) || TextUtils.isEmpty(editPersonNotesEditText.getText())) {
                setResult(RESULT_CANCELED, modifyPersonIntent);

                editPersonDataSnackBarText = "All fields must be filled.";
                displayEditPersonDataSnackbar();

            } else {

                String personName = editPersonNameEditText.getText().toString();
                String personNumber = editPersonNumberEditText.getText().toString();
                String personEmail = editPersonEmailEditText.getText().toString();
                String personNotes = editPersonNotesEditText.getText().toString();


                modifyPersonIntent.putExtra(PERSON_NAME, personName);
                modifyPersonIntent.putExtra(PERSON_NUMBER, personNumber);
                modifyPersonIntent.putExtra(PERSON_EMAIL, personEmail);
                modifyPersonIntent.putExtra(PERSON_NOTES, personNotes);


                int personID = getIntent().getIntExtra(PERSON_ID, -1);
                if (personID != -1) {
                    modifyPersonIntent.putExtra(PERSON_ID, personID);

                }

                setResult(RESULT_OK, modifyPersonIntent);
                finish();


            }
        });

    }

    private void displayEditPersonDataSnackbar(){

        Snackbar editPersonSnackbar = Snackbar.make(editPersonDataConstraintLayout, editPersonDataSnackBarText, Snackbar.LENGTH_LONG);
        View snackbarView = editPersonSnackbar.getView();
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

        editPersonSnackbar.show();
    }

}


