package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_forgot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotActivity extends AppCompatActivity {
    TextInputEditText editText;
    String Username;
    ImageView back;
    Button Continue;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editText = findViewById(R.id.et_details);
        back = findViewById(R.id.Image_back);
        progressBar = findViewById(R.id.Progress_bar);
        Continue = findViewById(R.id.Button_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = editText.getText().toString();
                if (!Username.isEmpty()){
                    ForgotPassword();
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(ForgotActivity.this,"Enter your registered email id / mobile no.",Toast.LENGTH_LONG).show();
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void ForgotPassword(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_forgot>call = apiInterface.ForgotPassword(Username);
        call.enqueue(new Callback<Result_forgot>() {
            @Override
            public void onResponse(Call<Result_forgot> call, Response<Result_forgot> response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    boolean status = response.body().getStatus();
                    String message = response.body().getMessage();
                    String Username = response.body().getData().getUsername();
                    if (Username!=null){
                        Intent intent= new Intent(ForgotActivity.this,ResetActivity.class);
                        startActivity(intent);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result_forgot> call, Throwable t) {
                Log.e("exception",":"+t);

            }
        });
    }
}
