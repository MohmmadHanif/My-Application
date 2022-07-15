package com.example.myapplication.Room;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.Room.Modal.roomStudentModal;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;

public class roomStudentAdapter extends RecyclerView.Adapter<roomStudentAdapter.viewHolder>{

    onclickAdapter onclickAdapter;
    Context context;
    ArrayList<roomStudentModal> modalArrayList;

    public roomStudentAdapter(com.example.myapplication.Room.onclickAdapter onclickAdapter, Context context, ArrayList<roomStudentModal> modalArrayList) {
        this.onclickAdapter = onclickAdapter;
        this.context = context;
        this.modalArrayList = modalArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_student_show_item,parent,false);
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
        private MaterialTextView name, phoneNumber, email, courseName,gender;
        ImageView roomRecordDelete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvNameRoom);
            phoneNumber = itemView.findViewById(R.id.tvPhoneNumberRoom);
            email = itemView.findViewById(R.id.tvEmailRoom);
            courseName = itemView.findViewById(R.id.tvCourseNameRoom);
            gender = itemView.findViewById(R.id.tvGenderRoom);
            roomRecordDelete = itemView.findViewById(R.id.roomRecordDelete);

/*
            roomRecordDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    roomStudentDatabase database = roomStudentDatabase.getDatabase(context);
                    roomStudentDAO dao = database.roomDAO();

                    dao.delete(modalArrayList.get(getAdapterPosition()).getName());
                    modalArrayList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
*/

            roomRecordDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onclickAdapter.onclick(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
