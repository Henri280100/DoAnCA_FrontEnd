package com.example.ume_frontend.ui.activity;

import static com.example.ume_frontend.Retrofit.RetrofitClient.getRetrofit;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ume_frontend.API.ApiMethod;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ImageView imgBack;
    TextInputLayout txtEmail, txtPhoneNumber;
    TextInputLayout txtPassword, txtConfirmPassword;
    Button btnDone;
    TextView txtAlreadyHaveAccount, txtLogin;
    TextInputLayout txtUsername;
    RadioButton rdMale, rdFemale;
    RadioGroup rgGender;
    ConstraintLayout layoutVerify;
    RelativeLayout layoutSignin;
    Button btnOk, btnSend;
    EditText txtVerifyCode;
    String verifyCode;
    String gender = "1";
    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imgBack = findViewById(R.id.imgBack);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumb);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

        btnDone = findViewById(R.id.btnDone);

        txtAlreadyHaveAccount = findViewById(R.id.txtAlreadyHaveAccount);
        txtLogin = findViewById(R.id.txtLogin);
        txtUsername = findViewById(R.id.txtUsername);
        rdMale = findViewById(R.id.rdMale);
        rdFemale = findViewById(R.id.rdFemale);
        rgGender = findViewById(R.id.rgGender);
        layoutSignin = findViewById(R.id.layoutSignin);
        layoutVerify = findViewById(R.id.layoutVerification);


        btnOk = findViewById(R.id.btnOk);
        btnSend = findViewById(R.id.btnSend);
        txtVerifyCode = findViewById(R.id.txtVerifyCode);

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Just a minute");
        progressDialog.setCanceledOnTouchOutside(false);
//        final String[] numberToPass = {"1"}; // 1 for male
//        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int childCount = radioGroup.getChildCount();
//                RadioButton btn = (RadioButton) radioGroup.findViewById(childCount);
//                switch (btn.getId()){
//                    case R.id.rdMale:
//                        numberToPass[0] = "1";
//                        break;
//                    case R.id.rdFemale:
//                        numberToPass[0] = "2";
//                        break;
//                }
//            }
//        });


        /**
         * TEXTVIEW LOGIN
         * (NOTE: IF USER ALREADY HAS ACCOUNT)
         */
        txtLogin.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));

        /**
         * BACK FUNCTION
         * Hmm...Just create this back function if user don't wanna use the login textview
         * Just for fun :D
         */
        imgBack.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));

        rgGender.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.rdMale:
                        gender = "1";
                        break;
                    case R.id.rdFemale:
                        gender = "2";
                        break;
                }
            }
        });

        /// BUTTON DONE ////
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutSignin.setVisibility(View.GONE);
                layoutVerify.setVisibility(View.VISIBLE);
                verificationEmail(txtEmail.getEditText().getText().toString());
                progressDialog.show();
//                createUser();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (txtVerifyCode.getText().toString().equals(verifyCode)) {
                        createUser();
                        layoutVerify.setVisibility(View.GONE);
                        layoutSignin.setVisibility(View.VISIBLE);
                    } else {
                        txtVerifyCode.setError("Wrong verification code!");
                        txtVerifyCode.setText("");
                        txtVerifyCode.requestFocus();
                    }
                } catch (NullPointerException ex) {
                    txtVerifyCode.setError("Something wrong");
                    ex.getMessage();
                }
            }
        });
    }


    private void verificationEmail(String email) {
        ApiMethod method = getRetrofit().create(ApiMethod.class);
        Call<String> call = method.sendCodeToEmail(email);
        try {
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    progressDialog.dismiss();
                    verifyCode = response.body();
                    if (!verifyCode.equals("")) {
                        Toast.makeText(RegisterActivity.this, "Check your mail", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        txtVerifyCode.setError("Wrong email!");
                        txtVerifyCode.setText("");
                        txtVerifyCode.requestFocus();
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    txtVerifyCode.setError("Please check your wifi");
                }
            });
        } catch (Exception ex) {
            txtVerifyCode.setError("Something wrong");
        }
    }

    private void createUser() {
        UserModel.Account userAccount = new UserModel.Account(null, null, Boolean.valueOf(rgGender.toString()),
                null, null, null, txtUsername.getEditText().getText().toString(),
                null, txtPassword.getEditText().getText().toString(), txtPhoneNumber.getEditText().getText().toString(),
                null, null, txtEmail.getEditText().getText().toString());
        ApiMethod method = getRetrofit().create(ApiMethod.class);
        Call<UserModel> call = method.createUser(userAccount);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body().getMessage().equals("success")) {
                    Log.d("Log", "Add successful");
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
//        Call<String> registerCall = method.createUser(userAccount);
//
//        registerCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.message().equals("success")) {
//                    Toast.makeText(RegisterActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
//                    Log.d("Log", "Add successful!");
//                    startActivity(new Intent(RegisterActivity.this, Main.class));
//                } else {
//                    Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.d("Log", t.getMessage());
//            }
//        });
    }


}