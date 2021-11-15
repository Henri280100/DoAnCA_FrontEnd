package com.example.ume_frontend.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.ume_frontend.R;

public class DoneActivity extends AppCompatActivity {

    ImageView imgDone;
    Button btnUpdate;
    private AnimatedVectorDrawableCompat avd;
    private AnimatedVectorDrawable avd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        // Initialize
        imgDone = findViewById(R.id.imgDone);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(view -> {
           startActivity(new Intent(this, MainActivity.class));
        });

    }
    // Animation will show up after user has finished create a new password.
    @Override
    protected void onStart() {
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) imgDone.getDrawable();
        drawable.start();
        super.onStart();
    }
}