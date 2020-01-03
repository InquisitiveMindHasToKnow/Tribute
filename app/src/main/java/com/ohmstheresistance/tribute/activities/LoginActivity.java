package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
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
    private ConstraintLayout loginActivityConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.user_name_edittext);
        loginPassword = findViewById(R.id.user_password_edittext);
        checkBox = findViewById(R.id.remember_me_checkbox);
        userLoginButton = findViewById(R.id.login_button);
        userRegistrationButton = findViewById(R.id.register_button);
        loginActivityConstraintLayout = findViewById(R.id.loginactivity_constraint);

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

                displayLoginPageSnackbar();
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

    private void displayLoginPageSnackbar(){

        Snackbar loginPageSnackbar = Snackbar.make(loginActivityConstraintLayout, "Username or password invalid!", Snackbar.LENGTH_LONG);
        View snackbarView = loginPageSnackbar.getView();
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

        loginPageSnackbar.show();
    }
}
