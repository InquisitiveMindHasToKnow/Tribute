package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;

public class UserRegistrationActivity extends AppCompatActivity {

    private SharedPreferences registrationSharedPrefs;
    private EditText registrationUserName;
    private EditText registrationPassword;
    private EditText confirmPassword;
    private Intent registrationIntent;
    private long lastButtonClickTime = 0;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registrationUserName = findViewById(R.id.user_registration_name_edittext);
        registrationPassword = findViewById(R.id.user_registration_password_edittext);
        confirmPassword = findViewById(R.id.user_registration_confirm_password_edittext);
        registerButton = findViewById(R.id.user_registration_submit_button);

        registrationIntent = getIntent();
        registrationSharedPrefs = getApplicationContext().getSharedPreferences(registrationIntent.getStringExtra("testKey"), MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                SharedPreferences.Editor editor = registrationSharedPrefs.edit();


                if (registrationUserName.getText() != null && registrationPassword.getText() != null && confirmPassword.getText() != null &&
                        registrationPassword.getText().toString().equals(confirmPassword.getText().toString()
                        )) {
                    editor.putString("user" + registrationUserName.getText().toString(), registrationUserName.getText().toString());
                    editor.putString("password" + registrationUserName.getText().toString(), registrationPassword.getText().toString());
                    editor.apply();
                    finish();
                }

                if (!registrationPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Try again. Both passwords have to match", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}