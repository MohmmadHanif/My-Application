package com.example.myapplication.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Room.Modal.roomStudentModal;
import com.example.myapplication.Sqlite.SqliteAddDataActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.regex.Pattern;

public class roomStudentAddOrUpdateActivity extends AppCompatActivity {

    TextInputEditText roomStudentName, roomStudentPhoneNumber, roomStudentEmail, roomStudentCourseName;
    Spinner addGenderSpinnerRoom;
    MaterialTextView tvRoom;
    MaterialButton roomAddOrUpdateBtn;
    ImageView roomAddOrUpdateBackBtn;
    public static final String UPDATE_TAG = "boolean";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    private static final String GENDER = "gender";
    private static final String ID = "id";
    boolean isUpdate = false;
    int id;

    roomStudentDatabase studentDatabase;
    roomStudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_student_addor_update);

        roomAddOrUpdateBtn = findViewById(R.id.btnAddDataRoom);
        roomStudentName = findViewById(R.id.etRoomNameAddData);
        roomStudentPhoneNumber = findViewById(R.id.etRoomPhoneAddData);
        roomStudentEmail = findViewById(R.id.etRoomEmailAddData);
        roomStudentCourseName = findViewById(R.id.etRoomCourseAddData);
        addGenderSpinnerRoom = findViewById(R.id.addGenderSpinnerRoom);
        tvRoom = findViewById(R.id.tvRoom);

        String gender[] = {"Pick Any One", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender);
        addGenderSpinnerRoom.setAdapter(adapter);
        roomAddOrUpdateBackBtn = findViewById(R.id.roomAddOrUpdateBackBtn);

        roomStudentDatabase studentDatabase = roomStudentDatabase.getDatabase(getApplicationContext());
        roomStudentDAO dao = studentDatabase.roomDAO();

        //Set Activity and Button Name
        //Get Data And Set Data in Activity
        isUpdate = getIntent().getBooleanExtra(UPDATE_TAG, false);
        if(isUpdate){
            tvRoom.setText("Update Student Data");
            roomAddOrUpdateBtn.setText("Update");
            id = Integer.parseInt(getIntent().getStringExtra(ID));
            String getName = getIntent().getStringExtra(NAME);
            String getPhone = getIntent().getStringExtra(PHONE_NUMBER);
            String getEmail = getIntent().getStringExtra(EMAIL);
            String getCourse = getIntent().getStringExtra(COURSE_NAME);
            String getGender = getIntent().getStringExtra(GENDER);
            roomStudentName.setText(getName);
            roomStudentPhoneNumber.setText(getPhone);
            roomStudentEmail.setText(getEmail);
            roomStudentCourseName.setText(getCourse);

            if (getGender != null) {
                int genderPotion = adapter.getPosition(getGender);
                addGenderSpinnerRoom.setSelection(genderPotion);
            }
        }

        //BackBtn
        roomAddOrUpdateBackBtn.setOnClickListener(view -> onBackPressed());


        roomAddOrUpdateBtn.setOnClickListener(view -> {

            String name = roomStudentName.getText().toString().trim();
            String phoneNumber = roomStudentPhoneNumber.getText().toString().trim();
            String email = roomStudentEmail.getText().toString().trim();
            String course = roomStudentCourseName.getText().toString().trim();
            String gender1 = addGenderSpinnerRoom.getSelectedItem().toString().trim();

            if (isUpdate) {

                String setGender = addGenderSpinnerRoom.getSelectedItem().toString();
                if (name.isEmpty() || !name.matches("[a-zA-Z][a-zA-Z ]*")) {
                    roomStudentName.setError("Enter Valid Name");
                    roomStudentName.requestFocus();
                } else if (name.length() < 3) {
                    roomStudentName.setError("Name Must be 3 Words");
                    roomStudentName.requestFocus();
                }else if (phoneNumber.isEmpty()  || !Patterns.PHONE.matcher(phoneNumber).matches()) {
                    roomStudentPhoneNumber.setError("Enter PhoneNumber");
                    roomStudentPhoneNumber.requestFocus();
                } else if (phoneNumber.length() < 10 || phoneNumber.length() > 11) {
                    roomStudentPhoneNumber.setError("PhoneNUmber Must Be 10 Digit");
                    roomStudentPhoneNumber.requestFocus();
                } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    roomStudentEmail.setError("Enter Valid Email");
                    roomStudentEmail.requestFocus();
                } else if (course.isEmpty() || !course.matches("^[A-Za-z]+$")) {
                    roomStudentCourseName.setError("Enter Valid CourseName");
                    roomStudentCourseName.requestFocus();
                }else if (course.length() < 2) {
                    roomStudentCourseName.setError("CourseName Must Be 3 Digit");
                    roomStudentCourseName.requestFocus();
                } else if (addGenderSpinnerRoom.getSelectedItem().toString().trim().equals("Pick Any One")) {
                    Toast.makeText(roomStudentAddOrUpdateActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                    addGenderSpinnerRoom.requestFocus();
                } else {
                    dao.updateRecord(id,name,phoneNumber,email,course,gender1);
                    finish();
                }
            } else {

                String names = roomStudentName.getText().toString().trim();
                String phoneNumbers= roomStudentPhoneNumber.getText().toString().trim();
                String emails = roomStudentEmail.getText().toString().trim();
                String courses = roomStudentCourseName.getText().toString().trim();
                String gender1s = addGenderSpinnerRoom.getSelectedItem().toString().trim();

                if (names.isEmpty() || !names.matches("[a-zA-Z][a-zA-Z ]*")) {
                    roomStudentName.setError("Enter Valid Name");
                    roomStudentName.requestFocus();
                } else if (names.length() < 3) {
                    roomStudentName.setError("Name Must be 3 Words");
                    roomStudentName.requestFocus();
                }else if (phoneNumbers.isEmpty()  || !Patterns.PHONE.matcher(phoneNumbers).matches()) {
                    roomStudentPhoneNumber.setError("Enter PhoneNumber");
                    roomStudentPhoneNumber.requestFocus();
                } else if (phoneNumbers.length() < 10 || phoneNumbers.length() > 11) {
                    roomStudentPhoneNumber.setError("PhoneNUmber Must Be 10 Digit");
                    roomStudentPhoneNumber.requestFocus();
                } else if (emails.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
                    roomStudentEmail.setError("Enter Valid Email");
                    roomStudentEmail.requestFocus();
                } else if (courses.isEmpty() || !courses.matches("^[A-Za-z]+$")) {
                    roomStudentCourseName.setError("Enter Valid CourseName");
                    roomStudentCourseName.requestFocus();
                }else if (courses.length() < 2) {
                    roomStudentCourseName.setError("CourseName Must Be 3 Digit");
                    roomStudentCourseName.requestFocus();
                } else if (addGenderSpinnerRoom.getSelectedItem().toString().trim().equals("Pick Any One")) {
                    Toast.makeText(roomStudentAddOrUpdateActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                    addGenderSpinnerRoom.requestFocus();
                } else {
                    roomStudentModal modal = new roomStudentModal(names, phoneNumbers, emails, courses, gender1);
                    dao.insert(modal);
                    finish();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}