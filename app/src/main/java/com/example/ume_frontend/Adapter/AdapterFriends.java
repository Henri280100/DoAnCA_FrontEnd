package com.example.ume_frontend.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ume_frontend.Model.ListUser;
import com.example.ume_frontend.R;
import com.example.ume_frontend.ui.Chat.ChatActivity;
import com.example.ume_frontend.ui.activity.FriendsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterFriends extends RecyclerView.Adapter<AdapterFriends.userViewHolder>implements Filterable {
    List<ListUser.Data> dataList;
    List<ListUser.Data> dataListold;

    public AdapterFriends(List<ListUser.Data> dataList) {
        this.dataList = dataList;
        this.dataListold = dataList;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card,parent,false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        ListUser.Data user= dataList.get(position);
        if(user==null)
        {
            return;
        }
        else {
            holder.txtUsername.setText(user.getUserName());
            if(user.getIsOnline().equals("true"))
                holder.txtStatus.setText("online");
            else holder.txtStatus.setText("offline");
            Picasso.get().load(user.getUrlAvarta()).into(holder.imgUserImage);
            holder.imgUserImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(holder.itemView.getContext(), ChatActivity.class);
                    intent.putExtra("fkey",user);
                    intent.putExtra("mkey", FriendsActivity.myAccount);
                    v.getContext().startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(holder.itemView.getContext(), ChatActivity.class);
                    intent.putExtra("fkey",user);
                    intent.putExtra("mkey", FriendsActivity.myAccount);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(dataList!=null)
            return  dataList.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch= constraint.toString();
                if(strSearch.isEmpty())
                {
                    dataList=dataListold;
                }else {
                    List<ListUser.Data> list= new ArrayList<>();
                    for (ListUser.Data dt: dataListold) {
                        if(dt.getUserName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(dt);
                        }
                    }
                    dataList=list;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=dataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataList=(List<ListUser.Data>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class userViewHolder extends RecyclerView.ViewHolder{

        ImageView backgroundImage;
        CircleImageView imgUserImage;
        TextView txtUsername, txtStatus;
        Button btnUnFriend;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImage = itemView.findViewById(R.id.backgroundImage);
            imgUserImage = itemView.findViewById(R.id.imgUserImage);
            txtUsername = itemView.findViewById(R.id.txtUsername);
//            btnUnFriend = itemView.findViewById(R.id.btnUnFriend);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }

    }


}
