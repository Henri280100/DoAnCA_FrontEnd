package com.example.ume_frontend.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.R;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView img_user, imgProfile;
    ImageView bg_profile;
    TextView txt_username, txt_date;
    TextView txtName;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private View navHeader;

    Toolbar toolbar;
    Handler handler;
    ActionBarDrawerToggle drawerToggle;
    int idUser;
    UserModel.Account myAccount;
    User.Data friendsAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myAccount = UserLandingPage.account1;

        friendsAccount = (User.Data) getIntent().getSerializableExtra("fkey");

        if friendsAccount == null
            adapterInfo(myAccount)
            recyclerview.setAdapter(adapterInfo)
        else
             adapterInfo(friendsAccount)
             recyclerview.setAdapter(adapterInfo)

//        img_user = findViewById(R.id.img_user);
//        bg_profile = findViewById(R.id.bg_profile);
//        txt_date = findViewById(R.id.txt_date);
//        txt_username = findViewById(R.id.txt_username);

//        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        drawerLayout = findViewById(R.id.profileDrawer);
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
        handler = new Handler();
        // navigation View
//        navigationView = findViewById(R.id.profileNavi);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        //// NAME
        txtName = navHeader.findViewById(R.id.txtUsername);
        // Handler
        handler = new Handler();
        imgProfile = navHeader.findViewById(R.id.img_profile);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (friendsAccount != null) {
            txt_username.setText(friendsAccount.getUserName());
            Glide.with(ProfileActivity.this).load(friendsAccount.getUrlAvarta()).into(img_user);
            if (friendsAccount.getBirthDay() != null) {
                txt_date.setText(friendsAccount.getBirthDay());
            }

        } else {
            txt_username.setText(myAccount.getUserName());
            Glide.with(ProfileActivity.this).load(myAccount.getUrlAvarta()).into(img_user);
        }

        txtName.setText(UserLandingPage.account1.getUserName());
        if (UserLandingPage.account1.getUrlAvarta() != null) {
            Glide.with(ProfileActivity.this).load(UserLandingPage.account1.getUrlAvarta()).into(imgProfile);
        } else {
            img_user.setImageResource(R.drawable.default_image1);
        }


    }

    ///////////////////////// DRAWER
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(this, UserLandingPage.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.nav_friend) {
            startActivity(new Intent(this, FriendsActivity.class));
        }
//        else if (id == R.id.nav_logout) {
//            handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(ProfileActivity.this, ProgressActivity.class);
//                    startActivity(intent);
//                }
//            }, 3000);
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}