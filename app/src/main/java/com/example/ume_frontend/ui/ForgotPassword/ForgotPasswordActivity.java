package com.example.ume_frontend.ui.ForgotPassword;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp_ume.R;
import com.example.chatapp_ume.ui.activity.MainActivity;
import com.google.android.material.textfield.TextInputLayout;


public class ForgotPasswordActivity extends AppCompatActivity {

    Button btnSendEmail, btnSendEmailAgain;
    TextInputLayout editTxtForgotPass;
    ImageView imgBack;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        // Initialize
//        mAuth = FirebaseAuth.getInstance();
        btnSendEmail = findViewById(R.id.btnSendEmail);
        editTxtForgotPass = findViewById(R.id.editTextEmail);
        imgBack = findViewById(R.id.imgBack);

        // Set Event
        imgBack.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        btnSendEmail.setOnClickListener(view -> {
           String forgotPassword = editTxtForgotPass.getEditText().getText().toString();
//            progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
//            progressDialog.setTitle("Please wait...");
//            progressDialog.setMessage("Just a minute");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();
            if (forgotPassword.isEmpty()) {
                editTxtForgotPass.setError("Field cannot be empty!");
            } else {
                progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
                progressDialog.setTitle("Please wait...");
                progressDialog.setMessage("Just a minute");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                sendEmail();
            }
        });
        
        btnSendEmailAgain.setOnClickListener(view -> {
           sendEmailAgain(); 
        });
    }

    private void sendEmail() {
        
    }

    private void sendEmailAgain() {
    }

//    private void createNewPassword() {
//        ApiMethod method = getRetrofit().create(ApiMethod.class);
//        Call<String> call = method.createNewPassword(editTxtForgotPass.getEditText().getText().toString().trim());
//        try {
//            call.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    progressDialog.dismiss();
//                    mail = response.body();
//                    if (mail.equals("success")) {
//                        Toast.makeText(ForgotPasswordActivity.this, "Check your email" , Toast.LENGTH_SHORT).show();
//                        editTxtForgotPass.setError(null);
//                    } else {
//                        progressDialog.dismiss();
//                        editTxtForgotPass.setError("Email does not exit");
//                    }
//                }
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    editTxtForgotPass.setError("Please Check your wifi connection!");
//                    progressDialog.dismiss();
//                }
//            });
//        } catch (NullPointerException ex) {
//            progressDialog.dismiss();
//            editTxtForgotPass.setError("Something wrong with server!?");
//        }
//    }


}





















