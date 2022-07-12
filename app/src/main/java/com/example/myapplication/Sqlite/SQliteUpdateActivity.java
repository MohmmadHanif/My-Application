package com.example.myapplication.Sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SQliteUpdateActivity extends AppCompatActivity {

    private TextInputEditText nameEdt, phoneNumberEdt, emailEdt, courseNameEdt;
    private MaterialButton btnUpdateDataSqlite;
    private DataBaseHelper dataBaseHelper;
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlit_update);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nameEdt = findViewById(R.id.etUpdateNameData);
        phoneNumberEdt = findViewById(R.id.etUpdatePhoneData);
        emailEdt = findViewById(R.id.etUpdateEmailData);
        courseNameEdt = findViewById(R.id.etUpdateCourseData);

        btnUpdateDataSqlite = findViewById(R.id.btnUpdateDataSqlite);
        dataBaseHelper = new DataBaseHelper(SQliteUpdateActivity.this);

        String names = getIntent().getStringExtra(NAME);
        String phoneNumber = getIntent().getStringExtra(PHONE_NUMBER);
        String email = getIntent().getStringExtra(EMAIL);
        String courseName = getIntent().getStringExtra(COURSE_NAME);

        nameEdt.setText(names);
        phoneNumberEdt.setText(phoneNumber);
        emailEdt.setText(email);
        courseNameEdt.setText(courseName);
        btnUpdateDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString().trim();
                String phoneNumber = phoneNumberEdt.getText().toString().trim();
                String email = emailEdt.getText().toString().trim();
                String course = courseNameEdt.getText().toString().trim();
                dataBaseHelper.updateData(names,name,phoneNumber,email,course);

                startActivity(new Intent(getApplicationContext(),SqliteMainActivity.class));
                finish();
            }
        });

    }
}