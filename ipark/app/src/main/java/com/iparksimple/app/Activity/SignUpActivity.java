package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.R;

public class SignUpActivity extends AppCompatActivity {
    TextView SignIn;
    Button SignUp;
    TextInputEditText Name,Email,Mobile_no,Password,Confirm_password;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignIn = findViewById(R.id.Sign_in);
        SignUp = findViewById(R.id.But_Singup);
        Name = findViewById(R.id.et_fullname);
        Email = findViewById(R.id.et_email);
        Mobile_no = findViewById(R.id.et_Phone);
        Password = findViewById(R.id.et_password);
        Confirm_password = findViewById(R.id.et_co_password);
        checkBox = findViewById(R.id.CheckBox);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
