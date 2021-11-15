package com.example.ume_frontend.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp_ume.R;

public class ProgressActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }

    @Override
    protected void onStart() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ProgressActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
        super.onStart();
    }
}