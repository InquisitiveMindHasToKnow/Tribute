package com.ohmstheresistance.tribute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ohmstheresistance.tribute.R;

public class LoginActivity extends AppCompatActivity {

   private int loginCounter = 5;
   private TextView loginCounterTextView;
   private Button submitButton;
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

        loginCounterTextView.setText("Login Attempts Remaining: 5");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkCredentials(userEmailEditText.getText().toString(), userPasswordEditText.getText().toString());

         }
        });
    }

    private void checkCredentials(String loginUserName, String loginPassword){
        if((loginUserName.equals("soloproject@omarraymond.org")) && (loginPassword.equals("Password"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            loginCounter--;

            loginCounterTextView.setText("Login Attempts Remaining: " + String.valueOf(loginCounter));

            if(loginCounter == 0){
                submitButton.setEnabled(false);

                Toast.makeText(getApplicationContext(), "Too many incorrect login attempts. Please try again later!",
                        Toast.LENGTH_LONG).show();
            }

        }
    }

}

