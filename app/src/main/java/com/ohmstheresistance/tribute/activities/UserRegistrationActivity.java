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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
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

    private ConstraintLayout userRegistrationConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registrationUserName = findViewById(R.id.user_registration_name_edittext);
        registrationPassword = findViewById(R.id.user_registration_password_edittext);
        confirmPassword = findViewById(R.id.user_registration_confirm_password_edittext);
        registerButton = findViewById(R.id.user_registration_submit_button);
        userRegistrationConstraintLayout = findViewById(R.id.user_registration_constraint_layout);

        registrationIntent = getIntent();
        registrationSharedPrefs = getApplicationContext().getSharedPreferences(registrationIntent.getStringExtra("testKey"), MODE_PRIVATE);

        registerButton.setOnClickListener(view -> {
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

                displayRegistrationPageSnackbar();
            }
        });
    }

    private void displayRegistrationPageSnackbar(){

        Snackbar registrationPageSnackbar = Snackbar.make(userRegistrationConstraintLayout, "Try again. Both passwords have to match.", Snackbar.LENGTH_LONG);
        View snackbarView = registrationPageSnackbar.getView();
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

        registrationPageSnackbar.show();
    }
}