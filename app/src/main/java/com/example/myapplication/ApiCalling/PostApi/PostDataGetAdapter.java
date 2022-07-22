package com.example.myapplication.ApiCalling.PostApi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataGetAdapter extends RecyclerView.Adapter<PostDataGetAdapter.viewHolder> {
    List<PostDataModal> list;
    Context context;
    String acc = "application/json";
    String content = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    PostDataModal modal;
    OnclickInterface onclickInterface;

    public PostDataGetAdapter(List<PostDataModal> list, Context context, OnclickInterface onclickInterface) {
        this.list = list;
        this.context = context;
        this.onclickInterface = onclickInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posted_data_get_layout, parent, false);
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

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.postToGetDataNameTv);
            email = itemView.findViewById(R.id.postToGetDataEmailTv);
            gender = itemView.findViewById(R.id.postToGetDataGenderTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Select Option");
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"Cancel Operation!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();*/
                    onclickInterface.onClick(getAdapterPosition());
                }
            });
        }
    }
}
