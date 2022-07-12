package com.example.myapplication.Sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Sqlite.Adapter.ReadDataSqliteAdapter;
import com.example.myapplication.Sqlite.Modal.SqliteModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SqliteMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        FloatingActionButton btnAddDataSqlite = findViewById(R.id.btnSqliteAddData);
        RecyclerView rvShowAllData = findViewById(R.id.rvShowSqlData);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SqliteMainActivity.this);

        ArrayList<SqliteModal> list = new ArrayList<>();
        list = dataBaseHelper.readAllData();

        if (list.size() == 0) {
            Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show();
        } else {
            ReadDataSqliteAdapter adapter = new ReadDataSqliteAdapter(list, SqliteMainActivity.this);
            rvShowAllData.setAdapter(adapter);

        }
        btnAddDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SqliteAddDataActivity.class));
            }
        });

    }
}