package com.example.myapplication.OOPS;

public class Encapsulation {



}

class Student{
    private int student_Rollno;
    private String name;
    private String Collage;

    public int getStudent_Rollno(){
        return student_Rollno;
    }

    public void setStudent_Rollno(int student_Rollno){
        this.student_Rollno = student_Rollno;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCollage(){
        return Collage;
    }

    public void setCollage(String collage){
        this.Collage = collage;
    }

}

