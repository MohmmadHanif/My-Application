package com.example.myapplication.apicalling.apicallingcrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ApiCallingAdapter extends RecyclerView.Adapter<ApiCallingAdapter.viewHolder> {
    List<ApiCallingDataModal> list;
    Context context;
    String acc = "application/json";
    String content = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    ApiCallingDataModal modal;
    ApiOnclickInterface onclickInterface;

    public ApiCallingAdapter(List<ApiCallingDataModal> list, Context context, ApiOnclickInterface onclickInterface) {
        this.list = list;
        this.context = context;
        this.onclickInterface = onclickInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apicalling_data_get_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        modal = list.get(position);
        holder.name.setText(modal.getName());
        holder.email.setText(modal.getEmail());
        holder.gender.setText(modal.getGender());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView name, email, gender;
        private ImageView apiPopupMenu;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.postToGetDataNameTv);
            email = itemView.findViewById(R.id.postToGetDataEmailTv);
            gender = itemView.findViewById(R.id.postToGetDataGenderTv);
            apiPopupMenu = itemView.findViewById(R.id.apiPopupMenu);

            apiPopupMenu.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(context, apiPopupMenu);
                popupMenu.getMenuInflater().inflate(R.menu.api_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    int id = menuItem.getItemId();
                    if (R.id.menu_edit == id) {
                        onclickInterface.onClick(getAdapterPosition(), "edit");
                    } else {
                        onclickInterface.onClick(getAdapterPosition(), "delete");
                    }
                return true;
                });
                popupMenu.show();
            });
        }
    }
}
