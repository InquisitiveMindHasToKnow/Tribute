package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private long lastButtonClickTime = 0;
    private EditText loginUserName;
    private EditText loginPassword;
    private CheckBox checkBox;
    private Button userLoginButton;
    private Button userRegistrationButton;
    private SharedPreferences loginSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.user_name_edittext);
        loginPassword = findViewById(R.id.user_password_edittext);
        checkBox = findViewById(R.id.remember_me_checkbox);
        userLoginButton = findViewById(R.id.login_button);
        userRegistrationButton = findViewById(R.id.register_button);

        loginSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        if (loginSharedPreferences.getBoolean("isChecked", false)) {
            loginUserName.setText(loginSharedPreferences.getString("username", null));
            loginPassword.setText(loginSharedPreferences.getString("password", null));
            checkBox.setChecked(loginSharedPreferences.getBoolean("isChecked", false));
        }

        userLoginButton.setOnClickListener(v -> {

            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();

            SharedPreferences.Editor editor = loginSharedPreferences.edit();
            if (checkBox.isChecked()) {
                editor.putString("username", loginUserName.getText().toString());
                editor.putString("password", loginPassword.getText().toString());
                editor.putBoolean("isChecked", checkBox.isChecked());
                editor.commit();
            } else {
                editor.putBoolean("isChecked", checkBox.isChecked());
                editor.commit();
            }
            String checkUser = "user" + loginUserName.getText().toString();
            String checkPassword = "password" + loginUserName.getText().toString();

            if (loginUserName.getText().toString().equalsIgnoreCase(loginSharedPreferences.getString(checkUser, null))
                    && loginPassword.getText().toString().equals(loginSharedPreferences.getString(checkPassword, null))) {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                loginIntent.putExtra("currentUser", loginUserName.getText().toString());
                LoginActivity.this.finish();
                startActivity(loginIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Username or password invalid!", Toast.LENGTH_LONG).show();
            }
        });

        userRegistrationButton.setOnClickListener(view -> {
            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                return;
            }
            lastButtonClickTime = SystemClock.elapsedRealtime();
            Intent registerIntent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
            registerIntent.putExtra("testKey", SHARED_PREFS_KEY);
            startActivity(registerIntent);
        });

    }
}