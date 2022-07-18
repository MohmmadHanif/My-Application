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


    @Query("Delete From StudentDetail_tbl where id  = :id")
    void deleteData(int id);

    @Query("Update StudentDetail_tbl set name = :name, phoneNumber = :phoneNumber , email = :email, courseName = :course, gender = :gender where id = :id")
    void updateRecord(int id,String name, String phoneNumber, String email, String course, String gender);

    @Query("Select * from StudentDetail_tbl")
    List<roomStudentModal> getAllData();
}
