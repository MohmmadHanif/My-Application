package com.example.myapplication.Sqlite.Adapter;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Sqlite.DataBaseHelper;
import com.example.myapplication.Sqlite.Modal.SqliteModal;
import com.example.myapplication.Sqlite.SQliteUpdateActivity;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ReadDataSqliteAdapter extends RecyclerView.Adapter<ReadDataSqliteAdapter.viewHolder> {

    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    ArrayList<SqliteModal> sqliteModalArrayList;
    Context context;

    public ReadDataSqliteAdapter(ArrayList<SqliteModal> sqliteModalArrayList, Context context) {
        this.sqliteModalArrayList = sqliteModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_data_sqlite_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SqliteModal modal = sqliteModalArrayList.get(position);
        holder.name.setText(modal.getName());
        holder.phoneNumber.setText(modal.getPhoneNumber());
        holder.email.setText(modal.getEmail());
        holder.courseName.setText(modal.getCourseName());

//        holder.readSqliteDataLayout.setOnClickListener(view -> {
// AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//
//            dialog.setIcon(R.drawable.ic_baseline_question_mark_24);
//            dialog.setMessage("Choice Any One Option");
//            dialog.setCancelable(false);
//            dialog.setPositiveButton("Update", (dialogInterface, i) -> {
//                Toast.makeText(context, "You Click on Update!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,SQliteUpdateActivity.class);
//                intent.putExtra("name",modal.getName().trim());
//                intent.putExtra("phoneNumber",modal.getPhoneNumber());
//                intent.putExtra("email",modal.getEmail().trim());
//                intent.putExtra("courseName",modal.getCourseName().trim());
//                context.startActivity(intent);
//            });
//
//
//dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        context.startActivity(new Intent(context,SQliteUpdateActivity.class));
//
//                    }
//                });
//
//            dialog.setNegativeButton("Delete", (dialogInterface, i) -> {
//                DataBaseHelper db = new DataBaseHelper(context);
//                sqliteModalArrayList.remove(holder.getAdapterPosition());
//                notifyItemRemoved(holder.getAdapterPosition());
//
//            });
//
//            dialog.setNeutralButton("Cancel", (dialogInterface, i) -> Toast.makeText(context, "You Click on Cancel!", Toast.LENGTH_SHORT).show());
//
//            dialog.show();
//
//        });

    }

    @Override
    public int getItemCount() {
        return sqliteModalArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView name, phoneNumber, email, courseName;
        ConstraintLayout readSqliteDataLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvNameSqlite);
            phoneNumber = itemView.findViewById(R.id.tvPhoneNumberSqlite);
            email = itemView.findViewById(R.id.tvEmailSqlite);
            courseName = itemView.findViewById(R.id.tvCourseNameSqlite);
            readSqliteDataLayout = itemView.findViewById(R.id.readSqliteDataLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage("Select  operation");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SqliteModal modal = sqliteModalArrayList.get(getAdapterPosition());
                            Intent intent = new Intent(context, SQliteUpdateActivity.class);
                            intent.putExtra(NAME, modal.getName());
                            intent.putExtra(PHONE_NUMBER, modal.getPhoneNumber());
                            intent.putExtra(EMAIL, modal.getEmail());
                            intent.putExtra(COURSE_NAME, modal.getCourseName());
                            context.startActivity(intent);
                        }
                    });

                    dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DataBaseHelper db = new DataBaseHelper(context);
                            db.delete(sqliteModalArrayList.get(getAdapterPosition()).getName());
                            Toast.makeText(context, sqliteModalArrayList.get(getAdapterPosition()).getName() + " Record Delete", Toast.LENGTH_SHORT).show();
//                            sqliteModalArrayList.remove(sqliteModalArrayList.get(getAdapterPosition()));
                            sqliteModalArrayList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            /*notifyAll();*/
                        }
                    });

                    dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, "Cancel Operation", Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.show();
                }
            });

        }
    }
}
