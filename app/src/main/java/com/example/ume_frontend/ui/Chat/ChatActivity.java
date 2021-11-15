package com.example.ume_frontend.ui.Chat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ume_frontend.API.ApiMethod;
import com.example.ume_frontend.Adapter.adepterchat;
import com.example.ume_frontend.Model.ListMessages;
import com.example.ume_frontend.Model.ListUser;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.R;
import com.example.ume_frontend.Retrofit.RetrofitClient;
import com.example.ume_frontend.ui.activity.MainActivity;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {


    public static UserModel.Account myAccount;
    public static ListUser.Data friendAccount;
    private RecyclerView recycleChat;
    private adepterchat adapterchat;
    private List<ListMessages.Data> list;
    

    ListMessages.Data messagerc;

    ImageView imSendChat,imPicture;
    TextView txtchatMess;
    HubConnection hubchat;
    public  static String Mycode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        myAccount = MainActivity.myAccount;
        friendAccount = (ListUser.Data) getIntent().getSerializableExtra("fkey");

        recycleChat= findViewById(R.id.recycleChat);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recycleChat.setLayoutManager(linearLayoutManager);
        imSendChat= findViewById(R.id.imSendSms);
        txtchatMess= findViewById(R.id.txtChatSms);
        imPicture= findViewById(R.id.imPicture);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);

        hubchat= HubConnectionBuilder.create("http://10.0.2.2:8090/chatHub").build();
        hubchat.on("ReceiveMessage",( friends,user, message)-> {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(friendAccount.getIdUser().equals(friends) && myAccount.getIdUser().equals(user)) {
                            messagerc = new ListMessages.Data(myAccount.getIdUser(),
                                    myAccount.getCode(), null, null, null,
                                    strDate, null, friendAccount.getIdUser(), message, null);
                            list.add(messagerc);
                            adapterchat = new adepterchat(list);
                            adapterchat.notifyDataSetChanged();
                            recycleChat.setAdapter(adapterchat);
                        }
                    }
                });

            }catch (Exception e){
                txtchatMess.setError("failt");
            }
        },String.class, String.class,String.class);
        hubchat.start();
        getlistchat();
        imSendChat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message= txtchatMess.getText().toString();
                txtchatMess.setText("");
                String user= myAccount.getIdUser().toString();
                String friends= friendAccount.getIdUser().toString();
                if(!message.isEmpty()){

                    hubchat.send("SendMessage",friends,user,message);
                }
                messagerc = new ListMessages.Data(myAccount.getIdUser(),
                        myAccount.getCode(), null, null, null,
                        strDate, null, friendAccount.getIdUser(), message, null);
                sendmess(messagerc);
            }
        });

    }
    void sendmess(ListMessages.Data message)
    {

        ApiMethod get= RetrofitClient.getRetrofit().create(ApiMethod.class);
        Call<String> call = get.sendmess(message);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String t= response.body().toString();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ChatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void  getlistchat()
    {
        ApiMethod get= RetrofitClient.getRetrofit().create(ApiMethod.class);
        Call<ListMessages> call= get.getListchat(Integer.parseInt(myAccount.getIdUser()),Integer.parseInt(friendAccount.getIdUser()));
        call.enqueue(new Callback<ListMessages>() {
            @Override
            public void onResponse(Call<ListMessages> call, Response<ListMessages> response) {
                list=response.body().getData();
                if(response.body().getMessage().equals("success"))
                {
                    adapterchat= new adepterchat(list);
                    recycleChat.setAdapter(adapterchat);
                }
            }

            @Override
            public void onFailure(Call<ListMessages> call, Throwable t) {
                txtchatMess.setError(t.getMessage());
            }
        });
    }

}