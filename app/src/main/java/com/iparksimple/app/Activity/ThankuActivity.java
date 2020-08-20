package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iparksimple.app.R;

public class ThankuActivity extends AppCompatActivity {

    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);

        Continue = findViewById(R.id.But_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThankuActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
