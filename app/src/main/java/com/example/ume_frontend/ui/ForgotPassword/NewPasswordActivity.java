package com.example.ume_frontend.ui.ForgotPassword;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.chatapp_ume.CheckPassword;
import com.example.chatapp_ume.R;
import com.example.chatapp_ume.ui.activity.DoneActivity;
import com.google.android.material.textfield.TextInputLayout;

public class NewPasswordActivity extends AppCompatActivity {


    private TextInputLayout txtInputNewPass, txtInputConfirmPass;
    private Button btnSubmit;

    private ImageView imgDone;
    private AnimatedVectorDrawableCompat avd;
    private AnimatedVectorDrawable avd2;
    private ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        // EditText
        txtInputNewPass = findViewById(R.id.txtInputLayoutPass);
        txtInputConfirmPass = findViewById(R.id.txtInputLayoutConfirmPass);
        // Button
        btnSubmit = findViewById(R.id.btnSubmitNewPass);

        // ImageView
        imgDone = findViewById(R.id.imgDone);
        //Inflate the view from com.example.neko.ui.activity done to NewPasswordActivity
        View inflateView = getLayoutInflater().inflate(R.layout.activity_done, null, false);
        imgDone = inflateView.findViewById(R.id.imgDone);


        btnSubmit.setOnClickListener(view -> {
            if (!validatePassword() | !confirmPassword()) {
                return ;
            } else {
                startActivity(new Intent(NewPasswordActivity.this, DoneActivity.class));
            }
        });
    }


    private boolean validatePassword() {
        String passwordInput = txtInputNewPass.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            txtInputNewPass.setError("Field cannot be empty");
            return false;
        } else if (!CheckPassword.PASSWORD_NUMBER_PATTERN.matcher(passwordInput).matches()) {
            txtInputNewPass.setError("Password must have 1 number");
            return false;
        } else if (!CheckPassword.PASSWORD_LOWERCASE_PATTERN.matcher(passwordInput).matches()) {
            txtInputNewPass.setError("Password must have 1 lower case");
            return false;
        } else if (!CheckPassword.PASSWORD_UPPERCASE_PATTERN.matcher(passwordInput).matches()) {
            txtInputNewPass.setError("Password must have 1 upper case");
            return false;
        } else if (!CheckPassword.PASSWORD_SPECIAL_PATTERN.matcher(passwordInput).matches()) {
            txtInputNewPass.setError("Password must have 1 special symbol");
            return false;
        } else {
            txtInputNewPass.setError(null);
            return true;
        }
    }

    private boolean confirmPassword() {
        String confirmPassword = txtInputConfirmPass.getEditText().getText().toString().trim();
        String passwordInput = txtInputNewPass.getEditText().getText().toString().trim();
        if (!passwordInput.equals(confirmPassword)) {
            txtInputConfirmPass.setError("Password does not match!");
            return false;
        } else {
            txtInputConfirmPass.setError(null);
            return true;
        }
    }
}