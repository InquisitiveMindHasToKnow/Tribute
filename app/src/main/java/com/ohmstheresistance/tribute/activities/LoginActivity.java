package com.ohmstheresistance.tribute.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "sharedPrefs";
    private long lastButtonClickTime = 0;
    private SharedPreferences loginSharedPreferences;
    private EditText userPasswordEditText;
    private EditText userNameEditText;
    private Button registerButton;
    private Button loginButton;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        userNameEditText = findViewById(R.id.user_name_edittext);
        userPasswordEditText = findViewById(R.id.user_password_edittext);
        checkBox = findViewById(R.id.remember_me_checkbox);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);


        if (loginSharedPreferences.getBoolean("isChecked", false)) {
            userNameEditText.setText(loginSharedPreferences.getString("username", null));
            userPasswordEditText.setText(loginSharedPreferences.getString("password", null));
            checkBox.setChecked(loginSharedPreferences.getBoolean("isChecked", false));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                SharedPreferences.Editor editor = loginSharedPreferences.edit();
                if (checkBox.isChecked()) {
                    editor.putString("username", userNameEditText.getText().toString());
                    editor.putString("password", userPasswordEditText.getText().toString());
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.apply();

                } else {
                    editor.putBoolean("isChecked", checkBox.isChecked());
                    editor.commit();
                }
                String checkUserName = "user" + userNameEditText.getText().toString();
                String checkPassword = "password" + userPasswordEditText.getText().toString();

                if (userNameEditText.getText().toString().equalsIgnoreCase(loginSharedPreferences.getString(checkUserName, null))
                        && userPasswordEditText.getText().toString().equals(loginSharedPreferences.getString(checkPassword, null))) {
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.putExtra("currentUser", userNameEditText.getText().toString());
                    closeKeyboard();
                    startActivity(loginIntent);

                } else {
                    Toast.makeText(LoginActivity.this, "Username or password invalid! Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();

                Intent registrationIntent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
                registrationIntent.putExtra("userTest", SHARED_PREFS_KEY);
                registrationIntent.putExtra("userPasswordTest", SHARED_PREFS_KEY);
                startActivity(registrationIntent);
            }
        });

    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
