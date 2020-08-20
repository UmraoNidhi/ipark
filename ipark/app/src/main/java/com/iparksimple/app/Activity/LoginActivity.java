package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iparksimple.app.R;

public class LoginActivity extends AppCompatActivity {

    TextView SignUp,forgot_password;
    Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignIn = findViewById(R.id.Button_SignIn);
        forgot_password = findViewById(R.id.Forgot_password);
        forgot_password.setText(Html.fromHtml("<u>Forgot Password </u>"));
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        SignUp = findViewById(R.id.Sign_up);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
    }
}
