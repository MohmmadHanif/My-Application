package com.example.myapplication.room;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.room.Modal.roomStudentModal;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class roomStudentAdapter extends RecyclerView.Adapter<roomStudentAdapter.viewHolder>{

    onclickAdapter onclickAdapter;
    Context context;
    List<roomStudentModal> modalArrayList;

    public roomStudentAdapter(com.example.myapplication.room.onclickAdapter onclickAdapter, Context context, List<roomStudentModal> modalArrayList) {
        this.onclickAdapter = onclickAdapter;
        this.context = context;
        this.modalArrayList = modalArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_studentdata_room_layout,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        roomStudentModal modal = modalArrayList.get(position);
        holder.name.setText(modal.getName());
        holder.phoneNumber.setText(modal.getPhoneNumber());
        holder.email.setText(modal.getEmail());
        holder.courseName.setText(modal.getCourseName());
        holder.gender.setText(modal.getGender());
    }

    @Override
    public int getItemCount() {
        return modalArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView name;
        private final MaterialTextView phoneNumber;
        private final MaterialTextView email;
        private final MaterialTextView courseName;
        private final MaterialTextView gender;
        ImageView roomRecordDelete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvNameRoom);
            phoneNumber = itemView.findViewById(R.id.tvPhoneNumberRoom);
            email = itemView.findViewById(R.id.tvEmailRoom);
            courseName = itemView.findViewById(R.id.tvCourseNameRoom);
            gender = itemView.findViewById(R.id.tvGenderRoom);

            itemView.setOnClickListener(view -> {
                onclickAdapter.onclick(getAdapterPosition());
            });
        }
    }
}
