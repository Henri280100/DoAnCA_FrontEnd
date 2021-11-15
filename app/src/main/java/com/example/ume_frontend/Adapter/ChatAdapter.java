package com.example.ume_frontend.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp_ume.Model.UserModel;
import com.example.chatapp_ume.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.myViewHolder> {

    Context mContext;
    private List<UserModel.Account> accountData ;

    public ChatAdapter(Context mContext, List<UserModel.Account> accountData) {
        this.mContext = mContext;
        this.accountData = accountData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgUser, imgFriend;
        TextView txtUserChat, txtFriendChat;
        TextView txtUserSeen, txtFriendSeen;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize
            imgUser = itemView.findViewById(R.id.imgUser);
            imgFriend = itemView.findViewById(R.id.imgFriend);
            txtUserChat = itemView.findViewById(R.id.txtUserChat);
            txtFriendChat = itemView.findViewById(R.id.txtFriendChat);
            txtUserSeen = itemView.findViewById(R.id.txtUserSeenOn);
            txtFriendSeen = itemView.findViewById(R.id.txtFriendSeen0n);
        }
    }
}
