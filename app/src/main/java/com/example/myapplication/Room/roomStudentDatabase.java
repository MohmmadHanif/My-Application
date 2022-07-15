package com.example.myapplication.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Room.Modal.roomStudentModal;

@Database(entities = {roomStudentModal.class}, version = 1 , exportSchema = false)
public abstract class roomStudentDatabase extends RoomDatabase {

    public abstract roomStudentDAO roomDAO();

    public static roomStudentDatabase instance;

    static roomStudentDatabase getDatabase(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), roomStudentDatabase.class,"StudentData.db").allowMainThreadQueries().build();
        }
        return instance;
    }
}
