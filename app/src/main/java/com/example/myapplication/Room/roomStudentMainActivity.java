package com.example.myapplication.Room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Room.Modal.roomStudentModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class roomStudentMainActivity extends AppCompatActivity implements onclickAdapter {

    RecyclerView rvRoomShowData;
    List<roomStudentModal> modalArrayList;
    roomStudentAdapter adapter;
    public static final String UPDATE_TAG = "boolean";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    private static final String GENDER = "gender";
    private static final String ID = "id";

    roomStudentDatabase studentDatabase;
    roomStudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        FloatingActionButton addStudentDataRoom = findViewById(R.id.addStudentDataRoom);
        rvRoomShowData = findViewById(R.id.rvRoomShowData);

        studentDatabase = roomStudentDatabase.getDatabase(getApplicationContext());
        dao = studentDatabase.roomDAO();
        addStudentDataRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), roomStudentAddOrUpdateActivity.class);
                i.putExtra(UPDATE_TAG, false);
                startActivity(i);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        modalArrayList = dao.getAllData();
        adapter = new roomStudentAdapter(this, this, modalArrayList);
        rvRoomShowData.setAdapter(adapter);
    }

    @Override
    public void onclick(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Select Operation");
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                dao.deleteData(modalArrayList.get(position).getId());
                adapter.notifyItemRemoved(position);
                modalArrayList.remove(position);
            }
        });

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                roomStudentModal modal = modalArrayList.get(position);
                Intent intent = new Intent(getApplicationContext(), roomStudentAddOrUpdateActivity.class);
                intent.putExtra(ID, String.valueOf(modal.getId()));
                intent.putExtra(NAME, modal.getName());
                intent.putExtra(PHONE_NUMBER, modal.getPhoneNumber());
                intent.putExtra(EMAIL, modal.getEmail());
                intent.putExtra(COURSE_NAME, modal.getCourseName());
                intent.putExtra(GENDER, modal.getGender());
                intent.putExtra(UPDATE_TAG, true);
                /*intent.putExtra(UPDATE_TAG,true);
                intent.putExtra(UPDATE_TAG,true);*/
                startActivity(intent);
            }
        });

        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(roomStudentMainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}