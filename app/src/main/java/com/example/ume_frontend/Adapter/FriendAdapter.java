package com.example.ume_frontend.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp_ume.Model.UserModel;
import com.example.chatapp_ume.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.myViewHolder> {

    Context mContext;
    List<UserModel.Account> account;

    public FriendAdapter(Context mContext, List<UserModel.Account> account) {
        this.mContext = mContext;
        this.account = account;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_card,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        UserModel.Account user= account.get(position);
        if(user!=null)
        {
            return;
        }
        else {
            holder.txtUsername.setText(user.getUserName());
            if(user.getIsOnline().equals("true"))
                holder.txtStatus.setText("online");
            else holder.txtStatus.setText("offline");
            Picasso.get().load(user.getUrlAvarta()).into(holder.imgUserImage);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView backgroundImage;
        CircleImageView imgUserImage;
        TextView txtUsername, txtStatus;
        Button btnUnFriend;

       public myViewHolder(@NonNull View itemView) {
           super(itemView);
           backgroundImage = itemView.findViewById(R.id.backgroundImage);
           imgUserImage = itemView.findViewById(R.id.imgUserImage);
           txtUsername = itemView.findViewById(R.id.txtUsername);
           btnUnFriend = itemView.findViewById(R.id.btnUnFriend);
           txtStatus = itemView.findViewById(R.id.txtStatus);
       }
   }
}
