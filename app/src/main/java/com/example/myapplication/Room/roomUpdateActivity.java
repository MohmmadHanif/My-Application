package com.example.myapplication.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class roomUpdateActivity extends AppCompatActivity {

    TextInputEditText roomStudentName, roomStudentPhoneNumber, roomStudentEmail, roomStudentCourseName;
    Spinner addGenderSpinnerRoom;
    MaterialTextView tvRoom;
    MaterialButton roomUpdateBtn;
    ImageView roomAddOrUpdateBackBtn;
    public static final String UPDATE_TAG = "boolean";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";
    private static final String COURSE_NAME = "courseName";
    private static final String GENDER = "gender";
    private static final String ID = "id";
    boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_update);

        roomUpdateBtn = findViewById(R.id.btnUpdateDataRoom);
        roomStudentName = findViewById(R.id.etRoomNameUpdateData);
        roomStudentPhoneNumber = findViewById(R.id.etRoomPhoneUpdateData);
        roomStudentEmail = findViewById(R.id.etRoomEmailUpdateData);
        roomStudentCourseName = findViewById(R.id.etRoomCourseUpdateData);
        addGenderSpinnerRoom = findViewById(R.id.updateGenderSpinnerRoom);
        tvRoom = findViewById(R.id.tvRoom);

        String[] gender = {"Pick Any One", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender);
        addGenderSpinnerRoom.setAdapter(adapter);

        int id = Integer.parseInt(getIntent().getStringExtra(ID));
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


        roomUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = roomStudentName.getText().toString().trim();
                String phoneNumber = roomStudentPhoneNumber.getText().toString().trim();
                String email = roomStudentEmail.getText().toString().trim();
                String course = roomStudentCourseName.getText().toString().trim();
                String gender1 = addGenderSpinnerRoom.getSelectedItem().toString().trim();

                roomStudentDatabase database = roomStudentDatabase.getDatabase(getApplicationContext());
                roomStudentDAO dao = database.roomDAO();

                /*dao.updateRecords(name,name,phoneNumber,email,course,gender1);*/
                dao.updateRecord(id,name,phoneNumber,email,course,gender1);
                finish();
            }
        });
    }
}