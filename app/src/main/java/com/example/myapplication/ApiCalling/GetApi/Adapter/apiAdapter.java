package com.example.myapplication.ApiCalling.GetApi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ApiCalling.GetApi.UserDataResp;
import com.example.myapplication.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class apiAdapter extends RecyclerView.Adapter<apiAdapter.viewHolder> {

    List<UserDataResp> dataList;
    Context context;

    public apiAdapter(List<UserDataResp> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.api_item_layout,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UserDataResp data = dataList.get(position);
        holder.firstName.setText(data.getFirst_name());
        holder.lastName.setText(data.getLast_name());
        holder.email.setText(data.getEmail());
        Glide.with(context).load(data.getAvatar()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class     viewHolder extends RecyclerView.ViewHolder {

        MaterialTextView firstName, lastName, email;
        CircleImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tvFirstNameApi);
            lastName = itemView.findViewById(R.id.tvLastNameApi);
            email = itemView.findViewById(R.id.tvEmailApi);
            imageView = itemView.findViewById(R.id.profile_image);
        }
    }
}
