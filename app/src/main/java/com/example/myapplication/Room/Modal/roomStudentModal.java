package com.example.myapplication.Room.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudentDetail_tbl")
public class roomStudentModal {

    @PrimaryKey(autoGenerate = true)
    public
    int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "courseName")
    public String courseName;
    @ColumnInfo(name = "gender")
    public String gender;

    public roomStudentModal(String name, String phoneNumber, String email, String courseName, String gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.courseName = courseName;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGender() {
        return gender;
    }
}
