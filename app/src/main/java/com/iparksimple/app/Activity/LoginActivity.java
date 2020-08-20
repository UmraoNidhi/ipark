package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_Login;
import com.iparksimple.app.services.Result_signup;
import com.iparksimple.app.utils.Constants;
import com.iparksimple.app.utils.PreferenceUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView SignUp,forgot_password;
    Button SignIn;
    String User_Name,Password,Type;
    TextInputEditText Username,User_password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            Type = getIntent().getStringExtra(Constants.PreferenceConstants.TYPE);
        }catch (Exception e){
            e.printStackTrace();
        }

        SignIn = findViewById(R.id.Button_SignIn);
        progressBar = findViewById(R.id.Progress_bar);
        SignUp = findViewById(R.id.Sign_up);
        Username = findViewById(R.id.et_email);
        User_password = findViewById(R.id.et_password);
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
               ValidateFields();
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void ValidateFields(){
        User_Name = Username.getText().toString();
        Password = User_password.getText().toString();

        if (!User_Name.isEmpty()){
            if (!User_Name.contains("@")){
                if (User_Name.length()==10){
                    if (!Password.isEmpty()){
                        Login();
                        progressBar.setVisibility(View.VISIBLE);

                    }else {
                        Toast.makeText(this,"Please enter your password.",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this,"Please enter valid mobile no.",Toast.LENGTH_LONG).show();

                }

            }else if (User_Name.contains("@")){

                if (isValidEmail(User_Name)){
                    if (!Password.isEmpty()){
                        Login();
                        progressBar.setVisibility(View.VISIBLE);

                    }else {
                        Toast.makeText(this,"Please enter your password.",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this,"Please enter valid email id.",Toast.LENGTH_LONG).show();

                }

            }


        }else {
            Toast.makeText(this,"Please enter your email id/mobile no.",Toast.LENGTH_LONG).show();
        }



    }


    private void Login(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_Login> call = apiInterface.Login(User_Name,Password);
        call.enqueue(new Callback<Result_Login>() {
            @Override
            public void onResponse(Call<Result_Login> call, Response<Result_Login> response) {
                try {
                    boolean status = response.body().getStatus();
                    Log.e("status",":"+status);
                    String message = response.body().getMessage();
                    if (status == true){
                        if (response.body().getData()!=null){
                            progressBar.setVisibility(View.GONE);
                            String UserId = response.body().getData().getId();
                            String UserEmail = response.body().getData().getEmail();
                            String UserMobile = response.body().getData().getPhone();
                            String Token = response.body().getData().getToken();
                            PreferenceUtil.setAccessTokenFromLogin(LoginActivity.this,Token);
                            PreferenceUtil.setUserId(LoginActivity.this,UserId);
                            PreferenceUtil.setUserEmail(LoginActivity.this,UserEmail);
                            PreferenceUtil.setUserMobile(LoginActivity.this,UserMobile);
                            if (Type!=null){
                                if (Type.equalsIgnoreCase("DetailPage")){
                                    Intent intent = new Intent(LoginActivity.this,VehicleListActivity.class);
                                    startActivity(intent);
                                }
                            } else {
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }

                        }
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result_Login> call, Throwable t) {
                Log.e("exception",":"+t);

            }
        });
    }



}
