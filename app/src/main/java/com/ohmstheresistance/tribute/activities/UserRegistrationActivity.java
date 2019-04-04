package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ohmstheresistance.tribute.R;

public class UserRegistrationActivity extends AppCompatActivity {

    private SharedPreferences registrationSharedPrefs;
    private EditText registrationUsernameEdittext;
    private EditText registrationPasswordEdittext;
    private EditText confirmPasswordEdittext;
    private long lastButtonClickTime = 0;
    private Button registerButton;
    private Intent registrationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registrationUsernameEdittext = findViewById(R.id.user_registration_name_edittext);
        registrationPasswordEdittext = findViewById(R.id.user_registration_password_edittext);
        confirmPasswordEdittext = findViewById(R.id.user_registration_confirm_password_edittext);
        registerButton = findViewById(R.id.user_registration_submit_button);

        registrationIntent = getIntent();
        registrationSharedPrefs = getApplicationContext().getSharedPreferences(registrationIntent.getStringExtra("userTest"), MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                SharedPreferences.Editor editor = registrationSharedPrefs.edit();

                if (registrationUsernameEdittext.getText() != null && registrationPasswordEdittext.getText() != null && confirmPasswordEdittext.getText() != null &&
                        registrationPasswordEdittext.getText().toString().equals(confirmPasswordEdittext.getText().toString())) {
                    editor.putString("username1" + registrationUsernameEdittext.getText().toString(), registrationUsernameEdittext.getText().toString());
                    editor.putString("password1" + registrationPasswordEdittext.getText().toString(), registrationPasswordEdittext.getText().toString());
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
