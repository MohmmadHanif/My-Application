package com.example.myapplication.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Room.Modal.roomStudentModal;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface roomStudentDAO {

    @Insert
    void insert(roomStudentModal modal);

    @Update
    void update(roomStudentModal modal);


    @Query("Delete From StudentDetail_tbl where name = :name")
    void delete(String name);

    @Query("Select * from StudentDetail_tbl")
    List<roomStudentModal> getAllData();
}
