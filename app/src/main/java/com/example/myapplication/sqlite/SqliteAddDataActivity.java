package com.example.myapplication.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SqliteAddDataActivity extends AppCompatActivity {

    private TextInputEditText nameEdt, phoneNumberEdt, emailEdt, courseNameEdt;
    private MaterialButton btnAddDataSqlite;
    private Spinner addGenderSpinner;
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
        addGenderSpinner = findViewById(R.id.addGenderSpinner);
        btnAddDataSqlite = findViewById(R.id.btnAddDataSqlite);
        dataBaseHelper = new DataBaseHelper(SqliteAddDataActivity.this);

        String gender[] = {"Pick Any One", "Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender);
        addGenderSpinner.setAdapter(adapter);
        btnAddDataSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString().trim();
                String phoneNumber = phoneNumberEdt.getText().toString().trim();
                String email = emailEdt.getText().toString().trim();
                String course = courseNameEdt.getText().toString().trim();
                String gender = addGenderSpinner.getSelectedItem().toString().trim();
                Log.e("Gender", gender);


                if (name.isEmpty() || !name.matches("^[A-Za-z ]+$")) {
                    nameEdt.setError("Enter Valid Name");
                    nameEdt.requestFocus();
                } else if (phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() > 11 || !Patterns.PHONE.matcher(phoneNumber).matches()) {
                    phoneNumberEdt.setError("Enter 10 Digit PhoneNumber");
                    phoneNumberEdt.requestFocus();
                } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEdt.setError("Enter Valid Email");
                    emailEdt.requestFocus();
                } else if (course.isEmpty() || !course.matches("^[A-Za-z]+$")) {
                    courseNameEdt.setError("Enter Valid CourseName");
                    courseNameEdt.requestFocus();
                } else if (addGenderSpinner.getSelectedItem().toString().trim().equals("Pick Any One")) {
                    Toast.makeText(SqliteAddDataActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                    addGenderSpinner.requestFocus();
                } else {
                    dataBaseHelper.insertDataQuery(name, phoneNumber, email, course,gender);
                    Toast.makeText(SqliteAddDataActivity.this, "Insert SuccessFull!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

}