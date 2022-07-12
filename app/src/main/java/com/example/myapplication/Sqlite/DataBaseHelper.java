package com.example.myapplication.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.Sqlite.Modal.SqliteModal;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "StudentData";
    private static final String TABLE_NAME = "StudentDetail";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDetailQuery = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONE_NUMBER + " TEXT, "
                + EMAIL + " TEXT, "
                + COURSE_NAME + " TEXT"
                + ")";

        db.execSQL(createDetailQuery);

      /*  String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + PHONE_NUMBER + " TEXT, "
                + COURSE_NAME + " TEXT "
                + ")";
        db.execSQL(query);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    /*INSERT*/
    public void insertDataQuery(String name, String phoneNumber, String email, String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(PHONE_NUMBER, phoneNumber);
        cv.put(EMAIL, email);
        cv.put(COURSE_NAME, courseName);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
    /*SHOW ALL RECORD*/
    public ArrayList<SqliteModal> readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<SqliteModal> sqliteModalArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                sqliteModalArrayList.add(new SqliteModal(cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(2)));

            }while (cursor.moveToNext());
        }

        cursor.close();
        return sqliteModalArrayList;
    }

    public void delete(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME,NAME +"=?",new String[]{name});
        db.close();
    }
    public void deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
    /*       db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " +
                ID + " = " + position + ";");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + ID + " = " +
                ID + " -1 " + " WHERE " + ID + " > " + position + ";");
    */

            db.delete(TABLE_NAME, NAME + " = ? ", new String[]{name});
            db.close();

        db.close();
    }

    /*UPDATE*/
    public void updateData(String checkName,String name, String phoneNumber, String email, String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(PHONE_NUMBER, phoneNumber);
        values.put(EMAIL,email);
        values.put(COURSE_NAME, courseName);

        db.update(TABLE_NAME, values, NAME + " = ? ", new String[]{checkName});
        db.close();
    }


}
