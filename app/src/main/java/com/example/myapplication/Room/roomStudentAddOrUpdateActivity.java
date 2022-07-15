package com.example.myapplication.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class roomStudentAddOrUpdateActivity extends AppCompatActivity {

    TextInputEditText roomStudentName, roomStudentPhoneNumber, roomStudentEmail, roomStudentCourseName;
    Spinner addGenderSpinnerRoom;
    MaterialTextView tvRoom;
    MaterialButton roomAddOrUpdateBtn;
    ImageView roomAddOrUpdateBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_student_addor_update);


        roomStudentName = findViewById(R.id.etRoomNameAddData);
        roomStudentPhoneNumber = findViewById(R.id.etRoomPhoneAddData);
        roomStudentEmail = findViewById(R.id.etRoomEmailAddData);
        roomStudentCourseName = findViewById(R.id.etRoomCourseAddData);
        addGenderSpinnerRoom = findViewById(R.id.addGenderSpinnerRoom);
        tvRoom = findViewById(R.id.tvRoom);
        tvRoom.setText("Add Student Data");
        roomAddOrUpdateBackBtn = findViewById(R.id.roomAddOrUpdateBackBtn);

        roomAddOrUpdateBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        roomAddOrUpdateBtn = findViewById(R.id.btnAddDataRoom);

        String gender[] = {"Pick Any One", "Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender);
        addGenderSpinnerRoom.setAdapter(adapter);



        roomAddOrUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = roomStudentName.getText().toString().trim();
                String phoneNumber = roomStudentPhoneNumber.getText().toString().trim();
                String email = roomStudentEmail.getText().toString().trim();
                String course = roomStudentCourseName.getText().toString().trim();
                String gender = addGenderSpinnerRoom.getSelectedItem().toString().trim();
                Log.e("Gender", gender);


                if (name.isEmpty() || !name.matches("^[A-Za-z]+$")) {
                    roomStudentName.setError("Enter Valid Name");
                    roomStudentName.requestFocus();
                } else if (phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() > 11 || !Patterns.PHONE.matcher(phoneNumber).matches()) {
                    roomStudentPhoneNumber.setError("Enter 10 Digit PhoneNumber");
                    roomStudentPhoneNumber.requestFocus();
                } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    roomStudentEmail.setError("Enter Valid Email");
                    roomStudentEmail.requestFocus();
                } else if (course.isEmpty() || !course.matches("^[A-Za-z]+$")) {
                    roomStudentCourseName.setError("Enter Valid CourseName");
                    roomStudentCourseName.requestFocus();
                } else if (addGenderSpinnerRoom.getSelectedItem().toString().trim().equals("Pick Any One")) {
                    Toast.makeText(roomStudentAddOrUpdateActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                    addGenderSpinnerRoom.requestFocus();
                } else {

                    roomStudentDatabase studentDatabase = roomStudentDatabase.getDatabase(getApplicationContext());
                    roomStudentDAO dao = studentDatabase.roomDAO();

                    roomStudentModal modal = new roomStudentModal(name,phoneNumber,email,course,gender);
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