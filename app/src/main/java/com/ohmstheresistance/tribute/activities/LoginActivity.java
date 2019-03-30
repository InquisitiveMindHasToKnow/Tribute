package com.ohmstheresistance.tribute.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private int loginCounter = 5;
    private TextView loginCounterTextView;
    private ImageView welcomeImageView;
    private Button submitButton;
    private Button resetButton;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userEmailEditText = findViewById(R.id.user_email_edittext);
        userPasswordEditText = findViewById(R.id.user_password_edittext);
        loginCounterTextView = findViewById(R.id.login_countdown);
        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);


        loginCounterTextView.setText("Login Attempts Remaining: 5");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeKeyboard();
                checkCredentials(userEmailEditText.getText().toString(), userPasswordEditText.getText().toString());

            }
        });

        resetButton.setEnabled(false);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginCounter = 5;
                if (loginCounter > 0) {
                    resetButton.setEnabled(false);
                    submitButton.setEnabled(true);
                    loginCounterTextView.setText("Login Attempts Remaining: " + String.valueOf(loginCounter));
                }
            }
        });
    }

    private void checkCredentials(String loginUserName, String loginPassword) {
        if ((loginUserName.equals("Omar")) && (loginPassword.equals("Password"))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            loginCounter--;

            loginCounterTextView.setText("Login Attempts Remaining: " + String.valueOf(loginCounter));

            if (loginCounter == 0) {
                submitButton.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Too many incorrect login attempts. Please try again later!",
                        Toast.LENGTH_LONG).show();

                resetButton.setEnabled(true);
            }

        }

    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
