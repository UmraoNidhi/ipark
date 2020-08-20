package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.R;

public class ResetActivity extends AppCompatActivity {
    TextInputEditText OTP, Password, Confirm_password;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        OTP = findViewById(R.id.et_otp);
        Password = findViewById(R.id.et_password);
        Confirm_password = findViewById(R.id.et_co_password);
        back = findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}
