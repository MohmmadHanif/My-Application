package com.example.myapplication.Sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SqliteAddDataActivity extends AppCompatActivity {

    private TextInputEditText nameEdt, phoneNumberEdt, emailEdt, courseNameEdt;
    private MaterialButton btnAddDataSqlite;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_add_data);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nameEdt = findViewById(R.id.etNameAddData);
        phoneNumberEdt = findViewById(R.id.etPhoneAddData);
        emailEdt = findViewById(R.id.etEmailAddData);
        courseNameEdt = findViewById(R.id.etCourseAddData);

        btnAddDataSqlite = findViewById(R.id.btnAddDataSqlite);
        dataBaseHelper = new DataBaseHelper(SqliteAddDataActivity.this);

        btnAddDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString().trim();
                String phoneNumber = phoneNumberEdt.getText().toString().trim();
                String email = emailEdt.getText().toString().trim();
                String course = courseNameEdt.getText().toString().trim();

                if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || course.isEmpty()) {
                    Toast.makeText(SqliteAddDataActivity.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                dataBaseHelper.insertDataQuery(name, phoneNumber, email, course);
                Toast.makeText(SqliteAddDataActivity.this, "Insert SuccessFull!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SqliteAddDataActivity.this, SqliteMainActivity.class));
                finish();
            }
        });

    }
}