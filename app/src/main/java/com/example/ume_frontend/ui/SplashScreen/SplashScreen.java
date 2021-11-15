package com.example.ume_frontend.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp_ume.R;
import com.example.chatapp_ume.ui.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    Handler timer;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timer = new Handler();
        timer.postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 5000);

        /// ANIMATIONS
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.gif);
        logo = findViewById(R.id.txtLogo);
        slogan = findViewById(R.id.txtSlogan);

        logo.setAnimation(topAnim);
        image.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
    }
}