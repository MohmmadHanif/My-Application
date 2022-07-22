package com.example.myapplication.ApiCalling.PostApi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.AndroidActivityLifeCycle.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiCallingActivity extends AppCompatActivity {
    String acc = "application/json";
    String Content = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    PostApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_api_calling_actvity);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextInputEditText edName = findViewById(R.id.edNamePostApi);
        TextInputEditText edEmail = findViewById(R.id.edEmailPostApi);
        Spinner spPostApi = findViewById(R.id.spPostApi);
        AppCompatButton btnPostData = findViewById(R.id.btnPostData);


       /* btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (edName.getText().toString().isEmpty() && edEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(edName.getText().toString(), edEmail.getText().toString(),spPostApi.getSelectedItem().toString());
            }
        });
    }

    private void postData(String name, String email, String gender) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        PostApiInterface retrofitAPI = retrofit.create(PostApiInterface.class);

        // passing data from our text fields to our modal class.
        PostDataModal modal = new PostDataModal(name,email,gender,"Active");

        // calling a method to create a post and passing our modal class.
        Call<PostDataModal> call = retrofitAPI.sendData(acc,Content,auth,modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<PostDataModal>() {
            @Override
            public void onResponse(Call<PostDataModal> call, Response<PostDataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(getApplicationContext(), "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.

                // on below line we are setting empty text
                // to our both edit text.



                PostDataModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getEmail();

                // below line we are setting our
                // string to our text view.
            }

            @Override
            public void onFailure(Call<PostDataModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(PostApiCallingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/
        btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String email = edEmail.getText().toString().trim();
                String gender = spPostApi.getSelectedItem().toString().trim();
                if (name.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(PostApiCallingActivity.this, "Please Fill All Field", Toast.LENGTH_SHORT).show();
                } else {

                    apiInterface = PostApiInstance.getPostInstance().create(PostApiInterface.class);
                    PostDataModal modal = new PostDataModal(name, email, gender, "Active");
                    Call<PostDataModal> call = apiInterface.sendData(acc, acc, auth, modal);

                    call.enqueue(new Callback<PostDataModal>() {
                        @Override
                        public void onResponse(Call<PostDataModal> call, Response<PostDataModal> response) {
                            PostDataModal responseFromAPI = response.body();
                            if (responseFromAPI != null) {
                                Toast.makeText(PostApiCallingActivity.this, "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Email : " + responseFromAPI.getEmail() + "\n" + "Gender : " + responseFromAPI.getGender(), Toast.LENGTH_SHORT).show();
                                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Email : " + responseFromAPI.getEmail() + "\n" + "Gender : " + responseFromAPI.getGender();
                                finish();
                            } else {
                                Toast.makeText(PostApiCallingActivity.this, "List is Empty", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostDataModal> call, Throwable t) {
                            Toast.makeText(PostApiCallingActivity.this, "List id Empty", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}