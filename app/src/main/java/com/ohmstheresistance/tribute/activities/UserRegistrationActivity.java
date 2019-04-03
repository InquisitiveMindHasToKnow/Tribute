package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ohmstheresistance.tribute.R;

public class UserRegistrationActivity extends AppCompatActivity {

    private static final String USER_NAME_KEY = "currentUser";
    private SharedPreferences registrationSharedPrefs;
    private EditText usernameEdittext;
    private EditText passwordEdittext;
    private EditText confirmPasswordEdittext;
    private Button registerButton;
    private Intent registrationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        usernameEdittext = findViewById(R.id.user_registration_name_edittext);
        passwordEdittext = findViewById(R.id.user_registration_password_edittext);
        confirmPasswordEdittext = findViewById(R.id.user_registration_confirm_password_edittext);
        registerButton = findViewById(R.id.user_registration_submit_button);

        registrationIntent = getIntent();
        registrationSharedPrefs = getApplicationContext().getSharedPreferences(registrationIntent.getStringExtra(USER_NAME_KEY), MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = registrationSharedPrefs.edit();
                if (usernameEdittext.getText() != null && passwordEdittext.getText() != null && confirmPasswordEdittext.getText() != null &&
                        passwordEdittext.getText().toString().equals(confirmPasswordEdittext.getText().toString())) {
                    editor.putString("user" + usernameEdittext.getText().toString(), usernameEdittext.getText().toString());
                    editor.putString("password" + usernameEdittext.getText().toString(), passwordEdittext.getText().toString());
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
