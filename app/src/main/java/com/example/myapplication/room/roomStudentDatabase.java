package com.example.myapplication.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.DeleteColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.example.myapplication.room.Modal.roomStudentModal;

@Database(entities = {roomStudentModal.class},
        version = 1/*,
        autoMigrations = {@AutoMigration(from = 3,to = 4,spec = roomStudentDatabase.MyAutoMigration.class)}
        */)
public abstract class roomStudentDatabase extends RoomDatabase {
    public abstract roomStudentDAO roomDAO();

    @DeleteColumn(tableName = "StudentDetail_tbl",columnName = "age")
    static class MyAutoMigration implements AutoMigrationSpec {
    }
/*
    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE StudentDetail_tbl ADD COLUMN age TEXT DEFAULT '21'");
           */
/* database.execSQL("CREATE TABLE student (id INTEGER PRIMARY KEY NOT NULL,name TEXT NOT NULL, phoneNumber TEXT NOT NULL, email TEXT NOT NULL, courseName TEXT NOT NULL, gender TEXT NOT NULL)");
            database.execSQL("INSERT INTO student (id, name, phoneNumber,email,courseName,gender) SELECT id,name, phoneNumber,email,courseName,gender FROM StudentDetail_tbl");
            database.execSQL("DROP TABLE StudentDetail_tbl");
            database.execSQL("ALTER TABLE student RENAME TO StudentDetail_tbl");*//*

        }
    };
*/
    /*static Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE StudentDetail_tbl");
            database.execSQL("ALTER TABLE student RENAME TO StudentDetail_tbl");
        }
    };*/

    /*static Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE StudentDetail_tbl");
        }
    };
*/
    public static roomStudentDatabase instance;

    static roomStudentDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            roomStudentDatabase.class, "StudentData.db")
                    //.addMigrations(MIGRATION_1_2/*,MIGRATION_2_3*/)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
