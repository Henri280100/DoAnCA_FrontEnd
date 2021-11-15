package com.example.ume_frontend.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp_ume.Model.ListMessages;
import com.example.chatapp_ume.R;
import com.example.chatapp_ume.ui.Chat.ChatActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class adepterchat extends RecyclerView.Adapter<adepterchat.chatViewHolder> {
    List<ListMessages.Data> listmess;
    public adepterchat(List<ListMessages.Data> listmess) {
        this.listmess = listmess;
    }



    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat,parent,false);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        ListMessages.Data message= listmess.get(position);

        if(message==null)
        {
            return;
        }
        else {
            ChatActivity.Mycode= message.getCode();
            if(message.getIdUser().equals(ChatActivity.myAccount.getIdUser()))
            {
                holder.imgFriend.setVisibility(View.GONE);
                holder.txtFriendChat.setVisibility(View.GONE);
                holder.txtFriendSeen0n.setVisibility(View.GONE);
                holder.imgFriendDelete.setVisibility(View.GONE);

                holder.imgUser.setVisibility(View.VISIBLE);
                holder.txtUserChat.setVisibility(View.VISIBLE);
                holder.imgUserDelete.setVisibility(View.VISIBLE);
                holder.txtUserSeenOn.setVisibility(View.VISIBLE);

                Picasso.get().load(ChatActivity.myAccount.getUrlAvarta()).into(holder.imgUser);
                holder.txtUserChat.setText(message.getContent());
                holder.txtUserSeenOn.setText(message.getCreateOn());
            }
            else {
                holder.imgFriend.setVisibility(View.VISIBLE);
                holder.txtFriendChat.setVisibility(View.VISIBLE);
                holder.txtFriendSeen0n.setVisibility(View.VISIBLE);
                holder.imgFriendDelete.setVisibility(View.VISIBLE);

                holder.imgUser.setVisibility(View.GONE);
                holder.txtUserChat.setVisibility(View.GONE);
                holder.imgUserDelete.setVisibility(View.GONE);
                holder.txtUserSeenOn.setVisibility(View.GONE);

                Picasso.get().load(ChatActivity.friendAccount.getUrlAvarta()).into(holder.imgFriend);
                holder.txtFriendChat.setText(message.getContent());
                holder.txtFriendSeen0n.setText(message.getCreateOn());
            }
        }

    }

    @Override
    public int getItemCount() {
        if(listmess!=null)
            return listmess.size();
        return 0;
    }

    public  class chatViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgUser;
        ImageView imgFriendDelete,imgUserDelete;
        CircleImageView imgFriend;
        TextView txtFriendChat, txtUserChat;
        TextView txtFriendSeen0n, txtUserSeenOn;

        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser= itemView.findViewById(R.id.imgUser);
            imgFriend= itemView.findViewById(R.id.imgFriend);
            txtFriendChat= itemView.findViewById(R.id.txtFriendChat);
            txtUserChat= itemView.findViewById(R.id.txtUserChat);
            txtFriendSeen0n= itemView.findViewById(R.id.txtFriendSeen0n);
            txtUserSeenOn= itemView.findViewById(R.id.txtUserSeenOn);
            imgFriendDelete= itemView.findViewById(R.id.imgFriendDelete);
            imgUserDelete= itemView.findViewById(R.id.imgUserDelete);
        }
    }

}
