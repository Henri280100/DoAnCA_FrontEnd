package com.example.ume_frontend.ui.activity;

import static com.example.ume_frontend.Retrofit.RetrofitClient.getRetrofit;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ume_frontend.API.ApiMethod;
import com.example.ume_frontend.Adapter.AdapterFriends;
import com.example.ume_frontend.Model.ListUser;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.R;
import com.example.ume_frontend.Retrofit.RetrofitClient;
import com.example.ume_frontend.ui.CircleTransform;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserLandingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    View navHeader;
    private ImageView imgProfile;
    private TextView txtName;
    Toolbar toolbar;
    private Handler handler;
    private ActionBarDrawerToggle drawerToggle;

    TextView  txtsearch;
    ImageView imgfind;
    public RecyclerView recyclerchatFriends;

    private Uri filePath;
    private String avatar;

    FirebaseStorage storage;
    StorageReference storageReference;
    public static UserModel.Account account1;
    int idUser;
    List<ListUser.Data> data;
    private AdapterFriends friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);
        account1=MainActivity.myAccount;

        idUser = Integer.parseInt(account1.getIdUser());
        recyclerchatFriends= findViewById(R.id.recyclerchatFriends);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerchatFriends.setLayoutManager(linearLayoutManager);


        imgfind = findViewById(R.id.imgfind);
        txtsearch = findViewById(R.id.txtSearch);
        // get the FB storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        drawerLayout = findViewById(R.id.drawer);
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
        handler = new Handler();
        // navigation View
        navigationView = findViewById(R.id.navi);
        navigationView.setNavigationItemSelectedListener(this);
//        navHeader = navigationView.getHeaderView(0);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
        }
        ///// IMAGE PROFILE
        imgProfile = navHeader.findViewById(R.id.img_profile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
                choosePicture();
            }
        });
        //// NAME
        txtName = navHeader.findViewById(R.id.txtUsername);

        imgfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtsearch.getText().toString().equals(""))
                {
                    txtsearch.setError("chưa nhập");
                    txtsearch.requestFocus();
                }
                else

                    getListUser();
            }
        });

        getdata();
    }

//    void updateOndatabase()
//    {
//        ApiMethod method =  getRetrofit().create(ApiMethod.class);
//        Call<UserProfile> call= method.userProfile(String.valueOf(account1.getUserName()), avatar);
//        call.enqueue(new Callback<UserProfile>() {
//            @Override
//            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
//                Toast.makeText(Main.this, "thanh cong", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<UserProfile> call, Throwable t) {
//                Toast.makeText(Main.this, "kiem tra", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    private void getdata() {
        ApiMethod get= RetrofitClient.getRetrofit().create(ApiMethod.class);
        Call<ListUser> call=get.getListChatfriends(idUser);
        call.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                data=response.body().getData();
                if(data.size()!=0 && response.message() != "success")
                {
                    friendsAdapter = new AdapterFriends(data);
                    recyclerchatFriends.setAdapter(friendsAdapter);
                }
            }
            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {

            }
        });
    }

    public void getListUser() {
        ApiMethod method =  getRetrofit().create(ApiMethod.class);
        String phoneNumber= txtsearch.getText().toString();
        Call<UserModel> call = method.searchingUser(phoneNumber);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body().getMessage().equals("success")){
                    UserModel.Account user = response.body().getAccount();
                    Intent intent = new Intent(UserLandingPage.this, ProfileActivity.class);
                    intent.putExtra("fkey", user);
                    startActivity(intent);
                }
                else Toast.makeText(UserLandingPage.this, "Không tìm thấy", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UserLandingPage.this, "Kiểm tra kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ///// UPLOAD IMAGE TO FIREBASE /////
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        imgProfile.setImageURI(result);
                        filePath = result;
                        updateImageToFB();

                    }
                }
            });

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void updateImageToFB() {
        if (filePath != null) {
            String str = System.currentTimeMillis() + "." + getFileExtension(filePath);
            StorageReference fileRef = storageReference.child(str);
            fileRef.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageReference.child(str).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(@NonNull Uri uri) {
                                Glide.with(UserLandingPage.this).load(uri)
                                        .transform(new CircleTransform())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(imgProfile);
                                avatar = uri.toString();
                            }
                        });
                    }
                }
            });
        } else {
            Toast.makeText(UserLandingPage.this, "Update Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
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
//            startActivity(new Intent(this, Main.class).putExtra("data", account));
            Intent intent = new Intent(this, UserLandingPage.class);
            intent.putExtra("data", account1);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("data", account1);
            startActivity(intent);

        } else if (id == R.id.nav_friend) {
            Intent intent = new Intent(this, FriendsActivity.class);
            intent.putExtra("data", account1);
            startActivity(intent);
        }
//        else if (id == R.id.nav_settings) {
//            startActivity(new Intent(this, SettingsActivity.class).putExtra("data", account));
//        } else if (id == R.id.nav_about_us) {
//            startActivity(new Intent(this, AboutUsActivity.class));
//        } else if (id == R.id.nav_privacy_policy) {
//            startActivity(new Intent(this, PrivacyPolicyActivity.class));
//        }
//        else if (id == R.id.nav_logout) {
//            handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(UserLandingPage.this, ProgressActivity.class);
//                    startActivity(intent);
//                }
//            }, 3000);
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();


        txtName.setText(account1.getUserName());

        if (account1.getUrlAvarta() != null) {
            Glide.with(UserLandingPage.this).load(account1.getUrlAvarta()).into(imgProfile);
        } else {
            imgProfile.setImageResource(R.drawable.default_image1);
        }
    }

}