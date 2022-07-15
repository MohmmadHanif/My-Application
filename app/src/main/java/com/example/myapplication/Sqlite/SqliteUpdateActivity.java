package com.example.myapplication.Sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SqliteUpdateActivity extends AppCompatActivity {

    private TextInputEditText nameEdt, phoneNumberEdt, emailEdt, courseNameEdt;  /*genderEdt*/
    private MaterialButton btnUpdateDataSqlite;
    private DataBaseHelper dataBaseHelper;
    private Spinner updateGenderSpinner;
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    private static final String GENDER = "gender";

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
        updateGenderSpinner = findViewById(R.id.updateGenderSpinner);

        btnUpdateDataSqlite = findViewById(R.id.btnUpdateDataSqlite);
        dataBaseHelper = new DataBaseHelper(SqliteUpdateActivity.this);

        String genderSpinnerName[] = {"Pick Any One", "Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, genderSpinnerName);
        updateGenderSpinner.setAdapter(adapter);
        String names = getIntent().getStringExtra(NAME);
        String phoneNumber = getIntent().getStringExtra(PHONE_NUMBER);
        String email = getIntent().getStringExtra(EMAIL);
        String courseName = getIntent().getStringExtra(COURSE_NAME);
        String genderSpinner = getIntent().getStringExtra(GENDER);


        nameEdt.setText(names);
        phoneNumberEdt.setText(phoneNumber);
        emailEdt.setText(email);
        courseNameEdt.setText(courseName);
        if (genderSpinner != null) {
            int spinnerPosition = adapter.getPosition(genderSpinner);
            updateGenderSpinner.setSelection(spinnerPosition);
        }

        btnUpdateDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString().trim();
                String phoneNumber = phoneNumberEdt.getText().toString().trim();
                String email = emailEdt.getText().toString().trim();
                String course = courseNameEdt.getText().toString().trim();
                String gender = updateGenderSpinner.getSelectedItem().toString().trim();


                if (name.isEmpty() || !name.matches("^[A-Za-z]+$")) {
                    nameEdt.setError("Enter Valid Name");
                    nameEdt.requestFocus();
                } else if (phoneNumber.length() != 10 || !Patterns.PHONE.matcher(phoneNumber).matches()) {
                    phoneNumberEdt.setError("Enter 10 Digit PhoneNumber");
                    phoneNumberEdt.requestFocus();
                } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEdt.setError("Enter Valid Email");
                    emailEdt.requestFocus();
                } else if (course.isEmpty() || !course.matches("^[A-Za-z]+$")) {
                    courseNameEdt.setError("Enter Valid CourseName");
                    courseNameEdt.requestFocus();
                } else if (updateGenderSpinner.getSelectedItem().toString().trim().equals("Pick Any One")) {
                    Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
                    updateGenderSpinner.requestFocus();
                } else {
                    dataBaseHelper.updateData(names,name,phoneNumber,email,course, gender);
                    Toast.makeText(getApplicationContext(), "Update SuccessFull!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}