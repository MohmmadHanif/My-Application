package com.example.myapplication.Sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Sqlite.Adapter.ReadDataSqliteAdapter;
import com.example.myapplication.Sqlite.Modal.SqliteModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class SqliteMainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    TextView tvEmpty;
    ArrayList<SqliteModal> list;
    RecyclerView rvShowAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        FloatingActionButton btnAddDataSqlite = findViewById(R.id.btnSqliteAddData);
        rvShowAllData = findViewById(R.id.rvShowSqlData);
        tvEmpty = findViewById(R.id.tvEmpty);
        dataBaseHelper = new DataBaseHelper(SqliteMainActivity.this);
        list = new ArrayList<>();

        //ADD BUTTON
        btnAddDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SqliteAddDataActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        list = dataBaseHelper.readAllData();
        if (list.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            ReadDataSqliteAdapter adapter = new ReadDataSqliteAdapter(list, SqliteMainActivity.this);
            rvShowAllData.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}