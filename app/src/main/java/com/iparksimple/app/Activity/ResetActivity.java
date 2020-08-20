package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
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
import com.iparksimple.app.services.Result_reset;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetActivity extends AppCompatActivity {
    TextInputEditText OTP, Password, Confirm_password;
    ImageView back;
    Button next;
    String user_otp,User_password,User_confirm,username;
    HashMap<String,String>request_map = new HashMap<>();
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        OTP = findViewById(R.id.et_otp);
        Password = findViewById(R.id.et_password);
        Confirm_password = findViewById(R.id.et_co_password);
        back = findViewById(R.id.Image_back);
        progressBar = findViewById(R.id.Progress_bar);
        next = findViewById(R.id.Button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateField();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void ValidateField(){
        user_otp = OTP.getText().toString();
        User_password = Password.getText().toString();
        User_confirm = Confirm_password.getText().toString();
        if (!user_otp.isEmpty()){
            if (!User_password.isEmpty()){
                if (!User_confirm.isEmpty()){
                    if (User_password.equals(User_confirm)){
                        ResetPassword();
                        progressBar.setVisibility(View.VISIBLE);

                    }else {
                        Toast.makeText(this,"Password and confirm password should be same",Toast.LENGTH_LONG).show();

                    }


                }else {
                    Toast.makeText(this,"Enter confirm password",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this,"Enter OTP",Toast.LENGTH_LONG).show();
        }
    }


    private void ResetPassword(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        request_map.put("username",username);
        request_map.put("otp",user_otp);
        request_map.put("password",User_password);
        request_map.put("confirm_password",User_confirm);
        Call<Result_reset>call = apiInterface.ResetPassword(request_map);
        call.enqueue(new Callback<Result_reset>() {
            @Override
            public void onResponse(Call<Result_reset> call, Response<Result_reset> response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    boolean status = response.body().getStatus();
                    String message = response.body().getMessage();
                    if (status == true){
                        Toast.makeText(ResetActivity.this, message,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ResetActivity.this, message,Toast.LENGTH_LONG).show();

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Result_reset> call, Throwable t) {
                Log.e("Exception",":"+t);

            }
        });
    }
}
