package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.R;

public class ForgotActivity extends AppCompatActivity {
    TextInputEditText editText;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editText = findViewById(R.id.et_details);
        back = findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
