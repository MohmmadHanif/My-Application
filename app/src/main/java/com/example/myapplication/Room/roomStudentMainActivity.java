package com.example.myapplication.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.Room.Modal.roomStudentModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class roomStudentMainActivity extends AppCompatActivity implements onclickAdapter {

    RecyclerView rvRoomShowData;
    ArrayList<roomStudentModal> modalArrayList;
    roomStudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        FloatingActionButton addStudentDataRoom = findViewById(R.id.addStudentDataRoom);
        rvRoomShowData = findViewById(R.id.rvRoomShowData);

        addStudentDataRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), roomStudentAddOrUpdateActivity.class);
                startActivityForResult(i,1);
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        roomStudentDatabase studentDatabase = roomStudentDatabase.getDatabase(getApplicationContext());
        roomStudentDAO dao = studentDatabase.roomDAO();
        modalArrayList = new ArrayList<>();
        modalArrayList =(ArrayList) dao.getAllData();
        adapter = new roomStudentAdapter(this,this,modalArrayList);
        rvRoomShowData.setAdapter(adapter);
    }

    @Override
    public void onclick(int position) {
        roomStudentDatabase studentDatabase = roomStudentDatabase.getDatabase(getApplicationContext());
        roomStudentDAO dao = studentDatabase.roomDAO();
        dao.delete(modalArrayList.get(position).getName());
        modalArrayList.remove(position);
    }
}