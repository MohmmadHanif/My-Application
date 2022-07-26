package com.example.myapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.sqlite.Modal.SqliteModal;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DB_NAME = "StudentData";
    private static final String TABLE_NAME = "StudentDetail";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    private static final String GENDER = "gender";
    private static final String MALE = "male";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null,DATABASE_VERSION);
    }

    String createDetailQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT, "
            + PHONE_NUMBER + " TEXT, "
            + EMAIL + " TEXT, "
            + COURSE_NAME + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDetailQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(createDetailQuery);
        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN "+ GENDER + " TEXT DEFAULT 'Male'");
        /*switch(i) {
            case 1:
                db.execSQL(createDetailQuery);
            case 2:
                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN "+ GENDER + " TEXT DEFAULT 'Female'");
        }*/
        onCreate(db);
    }

    /*INSERT*/
    public void insertDataQuery(String name, String phoneNumber, String email, String courseName,String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(PHONE_NUMBER, phoneNumber);
        cv.put(EMAIL, email);
        cv.put(COURSE_NAME, courseName);
        cv.put(GENDER, gender);
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
                        cursor.getString(2),
                        cursor.getString(5)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        return sqliteModalArrayList;
    }

    /*DELETE*/
    public void delete(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, NAME + "=?", new String[]{name});
        db.close();
    }

    /*UPDATE*/
    public void updateData(String checkName, String name, String phoneNumber, String email, String courseName, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(PHONE_NUMBER, phoneNumber);
        values.put(EMAIL, email);
        values.put(COURSE_NAME, courseName);
        values.put(GENDER, gender);

        db.update(TABLE_NAME, values, NAME + " = ? ", new String[]{checkName});
        readAllData();
        db.close();
    }


}
