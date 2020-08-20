package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.Network_class.Network_state;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_Login;
import com.iparksimple.app.services.Result_signup;
import com.iparksimple.app.utils.PreferenceUtil;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    TextView SignIn;
    Button SignUp;
    TextInputEditText Name,Email,Mobile_no,Password,Confirm_password;
    CheckBox checkBox;
    String UserName,User_email,User_mobile,password,confirm_password;
    ProgressBar progressBar;
    Boolean termsSelected = false;


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
        progressBar = findViewById(R.id.Progress_bar);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    termsSelected = true;
                }else {
                    termsSelected = false;
                    Toast.makeText(SignUpActivity.this,"Please Accept Terms and Conditions",Toast.LENGTH_LONG).show();
                }
            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ValidateForm();

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

    private void ValidateForm(){
            UserName = Name.getText().toString();
            User_email = Email.getText().toString();
            User_mobile = Mobile_no.getText().toString();
            password = Password.getText().toString();
            confirm_password = Confirm_password.getText().toString();

            if (!UserName.isEmpty()){
                if (!User_email.isEmpty()){
                    if (!isValidEmail(User_email)){
                    Toast.makeText(getApplicationContext(), "Enter a valid email id", Toast.LENGTH_LONG).show();
                    }else {
                        if (!User_mobile.isEmpty()){
                            if (User_mobile.length()==10){
                                if (!password.isEmpty()){
                                    if (!confirm_password.isEmpty()){
                                        if (password.equals(confirm_password)){
                                            if (termsSelected){
                                                SignUp();
                                                progressBar.setVisibility(View.VISIBLE);
                                            }else {
                                                Toast.makeText(SignUpActivity.this,"Please Accept Terms and Conditions",Toast.LENGTH_LONG).show();
                                            }
                                        }else {
                                            Toast.makeText(this,"Password and confirm password should be same",Toast.LENGTH_LONG).show();
                                        }
                                    }else {
                                        Toast.makeText(this,"Enter confirm password ",Toast.LENGTH_LONG).show();
                                    }

                                }else {
                                    Toast.makeText(this,"Enter password ",Toast.LENGTH_LONG).show();

                                }
                            }else {
                                Toast.makeText(this,"Enter valid mobile number",Toast.LENGTH_LONG).show();

                            }



                        }else {
                            Toast.makeText(this,"Enter your mobile number",Toast.LENGTH_LONG).show();

                        }

                    }

                }else {
                    Toast.makeText(this,"Enter your email address",Toast.LENGTH_LONG).show();

                }

            }else {
                Toast.makeText(this,"Enter your Name",Toast.LENGTH_LONG).show();
            }

    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void SignUp(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_signup>call = apiInterface.SignUp(User_email,confirm_password,User_mobile);
        call.enqueue(new Callback<Result_signup>() {
            @Override
            public void onResponse(Call<Result_signup> call, Response<Result_signup> response) {
                try {
                    boolean status = response.body().getStatus();
                    String Message = response.body().getMessage();
                    if (status = true){
                        if (Message!=null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignUpActivity.this,"User already registered",Toast.LENGTH_LONG).show();

                        }else {
                            if (response.body().getData()!=null){
                                progressBar.setVisibility(View.GONE);
                                String UserId = response.body().getData().getUserId();
                                String UserEmail = response.body().getData().getEmail();
                                String UserMobile = response.body().getData().getPhone();
                                PreferenceUtil.setUserId(SignUpActivity.this,UserId);
                                PreferenceUtil.setUserEmail(SignUpActivity.this,UserEmail);
                                PreferenceUtil.setUserMobile(SignUpActivity.this,UserMobile);

                                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }

                        }
                    }else {
                        Toast.makeText(SignUpActivity.this,Message,Toast.LENGTH_LONG).show();

                    }



                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<Result_signup> call, Throwable t) {
                Log.e("exception",":"+t);

            }
        });
    }





}
