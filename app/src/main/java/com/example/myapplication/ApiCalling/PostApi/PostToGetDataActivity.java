package com.example.myapplication.ApiCalling.PostApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.AndroidActivityLifeCycle.MainActivity;
import com.example.myapplication.ApiCalling.GetApi.Adapter.apiAdapter;
import com.example.myapplication.ApiCalling.GetApi.UserDataResp;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostToGetDataActivity extends AppCompatActivity implements OnclickInterface {

    PostDataGetAdapter adapter;
    List<PostDataModal> dataArrayList;
    RecyclerView rvApi;
    ProgressDialog progressDialog;
    PostApiInterface postApiInterface;
    String acc = "application/json";
    String content = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    PostDataModal modal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_to_get_data);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        rvApi = findViewById(R.id.rvPostedDataGet);
        dataArrayList = new ArrayList<>();


    }

    @Override
    protected void onResume() {
        super.onResume();
        postApiInterface = PostApiInstance.getPostInstance().create(PostApiInterface.class);
        postApiInterface.getData(acc, content, auth).enqueue(new Callback<List<PostDataModal>>() {
            @Override
            public void onResponse(Call<List<PostDataModal>> call, Response<List<PostDataModal>> response) {
                progressDialog.dismiss();
                dataArrayList = response.body();
                adapter = new PostDataGetAdapter(dataArrayList, getApplicationContext(), PostToGetDataActivity.this);
                rvApi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PostDataModal>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostToGetDataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addDataInApiUsingPost) {
            startActivity(new Intent(getApplicationContext(), PostApiCallingActivity.class));
        } else if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Select Operation");
        alert.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PostToGetDataActivity.this, "cancel Operation", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("Delete ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PostToGetDataActivity.this, "Delete"+dataArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PostToGetDataActivity.this, "Update "+dataArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}