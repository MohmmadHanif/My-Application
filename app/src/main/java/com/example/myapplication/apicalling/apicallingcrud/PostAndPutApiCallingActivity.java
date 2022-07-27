package com.example.myapplication.apicalling.apicallingcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.util.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAndPutApiCallingActivity extends AppCompatActivity {
    String acc = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    private static final String MODAL = "modal";
    ApiCallingInterface apiInterface;
    public static final String UPDATE_TAG = "boolean";
    private Boolean isUpdate = false;
    int id;
    TextInputEditText edName, edEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_post_and_put_apicalling);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("API CRUD");
        MaterialTextView tvPostData = findViewById(R.id.tvPostData);
        edName = findViewById(R.id.edNamePostApi);
        edEmail = findViewById(R.id.edEmailPostApi);
        Spinner spGenderPostApi = findViewById(R.id.spPostApi);
        AppCompatButton btnPostData = findViewById(R.id.btnPostData);
        isUpdate = getIntent().getBooleanExtra(UPDATE_TAG, false);
        apiInterface = ApiCallingInstance.getPostInstance().create(ApiCallingInterface.class);
        if (isUpdate) {
            tvPostData.setText("Update");
            btnPostData.setText("Update Data");
            ApiCallingDataModal modal = (ApiCallingDataModal) getIntent().getSerializableExtra(MODAL);
            String name = modal.getName();
            String email = modal.getEmail();
            String gender = modal.getGender();
            id = modal.getId();
            edEmail.setText(email);
            edName.setText(name);
            spGenderPostApi.setSelection(getIndex(spGenderPostApi, gender));
        }

        btnPostData.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String email = edEmail.getText().toString().trim();
            String gender = spGenderPostApi.getSelectedItem().toString().trim();
            if (checkValidation(name, email, gender)) {
                addValue(name, email, gender);
            }
        });
    }

    private void addValue(String name, String email, String gender) {
        ApiCallingDataModal modal = new ApiCallingDataModal(name, email, gender, "Active");
        Call<ApiCallingDataModal> call;
        if (isUpdate) {
            call = apiInterface.update(acc, acc, auth, modal.getId(), modal);
        } else {
            call = apiInterface.sendData(acc, acc, auth, modal);
        }
        call.enqueue(new Callback<ApiCallingDataModal>() {
            @Override
            public void onResponse(Call<ApiCallingDataModal> call, Response<ApiCallingDataModal> response) {
                ApiCallingDataModal responseFromAPI = response.body();
                if (responseFromAPI != null) {
                    Utils.showToast("Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Email : " + responseFromAPI.getEmail() + "\n" + "Gender : " + responseFromAPI.getGender());
                    finish();
                } else {
                    Utils.showToast("List is Empty");
                }
            }

            @Override
            public void onFailure(Call<ApiCallingDataModal> call, Throwable t) {
                Utils.showToast(t.getMessage());
            }
        });
    }

    private boolean checkValidation(String name, String email, String gender){
        if (name.isEmpty()) {
            edName.setError("Name Field Can't Be Empty!!");
            edName.requestFocus();
            return false;
        } else if (!name.matches("[a-zA-Z][a-zA-Z ]*")) {
            edName.setError("Please Enter Valid Name!");
            edName.requestFocus();
            return false;
        } else if (email.isEmpty()) {
            edEmail.setError("Email Field Can't Be Empty !!");
            edEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Please Enter Valid Email!");
            edEmail.requestFocus();
            return false;
        }else if (gender.matches("Pick Any One")) {
           Utils.showToast("Please Select Gender");
            return false;
        }
        return true;
    }


    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}