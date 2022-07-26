package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.room.Modal.roomStudentModal;

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
