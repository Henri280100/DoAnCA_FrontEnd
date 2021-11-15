package com.example.ume_frontend.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ume_frontend.API.ApiMethod;
import com.example.ume_frontend.Adapter.AdapterFriends;
import com.example.ume_frontend.Model.ListUser;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.R;
import com.example.ume_frontend.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsActivity extends AppCompatActivity {



    private RecyclerView friendRecView;
    private AdapterFriends friendsAdapter;
    List<ListUser.Data> data;
    public static UserModel.Account myAccount= MainActivity.myAccount;
    int id;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        id= Integer.parseInt(myAccount.getIdUser());
        toolbar = findViewById(R.id.app_bar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tìm Kiếm");
        //LoadListFriends("");

        friendRecView = findViewById(R.id.recViewFriend);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        friendRecView.setLayoutManager(linearLayoutManager);
        getdata();


    }



    private void getdata() {
        ApiMethod get=RetrofitClient.getRetrofit().create(ApiMethod.class);
        Call<ListUser> call=get.getListfriends(id);
        call.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                data=response.body().getData();
                if(data.size()!=0 && response.message() != "success")
                {
                    friendsAdapter = new AdapterFriends(data);
                    friendRecView.setAdapter(friendsAdapter);
                }
            }
            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_friends, menu);
        MenuItem menuItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                friendsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                friendsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return  true;

    }
}