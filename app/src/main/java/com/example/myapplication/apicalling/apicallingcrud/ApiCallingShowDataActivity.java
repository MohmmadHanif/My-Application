package com.example.myapplication.apicalling.apicallingcrud;

import static com.example.myapplication.util.Utils.dismissProgressDialog;
import static com.example.myapplication.util.Utils.initProgressDialog;
import static com.example.myapplication.util.Utils.showProgressDialog;
import static com.example.myapplication.util.Utils.showToast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallingShowDataActivity extends AppCompatActivity implements ApiOnclickInterface {
    ApiCallingAdapter adapter;
    List<ApiCallingDataModal> dataArrayList = new ArrayList<>();;
    RecyclerView rvShowApiData;
    ApiCallingInterface postApiInterface;
    String acc = "application/json";
    AppCompatButton btnApiReloadList;
    public static final String UPDATE_TAG = "boolean";

    private static final String MODAL = "modal";
    String content = "application/json";
    String auth = "Bearer 93845419806bc8b2f31829fbd2faef844224bd75a536acd0a10cdfc527475997";
    ApiCallingDataModal modal;
    int userId;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apicalling_showdata);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("API CRUD");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initProgressDialog(this,"Loading...");
        postApiInterface = ApiCallingInstance.getPostInstance().create(ApiCallingInterface.class);
        rvShowApiData = findViewById(R.id.rvPostedDataGet);
        btnApiReloadList = findViewById(R.id.btnApiReloadList);
        adapter = new ApiCallingAdapter(dataArrayList, getApplicationContext(), ApiCallingShowDataActivity.this);
        rvShowApiData.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog();
        getData();

    }

    private void getData() {


        postApiInterface.getData(acc, content, auth).enqueue(new Callback<List<ApiCallingDataModal>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<ApiCallingDataModal>> call, Response<List<ApiCallingDataModal>> response) {

                if (response.body() != null){
                    if (dataArrayList.size() !=0){
                        dataArrayList.clear();
                    }
                    dataArrayList.addAll(response.body());
                    btnApiReloadList.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(@NonNull Call<List<ApiCallingDataModal>> call, @NonNull Throwable t) {
                dismissProgressDialog();
                Utils.showToast(t.getMessage());
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
            startActivity(new Intent(getApplicationContext(), PostAndPutApiCallingActivity.class));
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
    public void onClick(int position, String operation) {
        String edit = "edit";
        this.position = position;
        modal = dataArrayList.get(position);
        userId = modal.getId();
        if (operation == edit) {
            Intent i = new Intent(this, PostAndPutApiCallingActivity.class);
            i.putExtra(MODAL, modal);
            i.putExtra(UPDATE_TAG, true);
            startActivity(i);
        } else {
            ApiCallingBottomSheetFragment bottomSheet = new ApiCallingBottomSheetFragment();
            bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        }
    }

    //Delete Record Method
    public void deleteList(){
        ApiCallingInterface postApiInterface = ApiCallingInstance.getPostInstance().create(ApiCallingInterface.class);
        Call<ApiCallingDataModal> call = postApiInterface.delete(acc, content, auth, userId);

        call.enqueue(new Callback<ApiCallingDataModal>() {
            @Override
            public void onResponse(@NonNull Call<ApiCallingDataModal> call, @NonNull Response<ApiCallingDataModal> response) {
                Utils.showToast("Record Delete Successful");
                adapter.notifyItemRemoved(position);
                dataArrayList.remove(position);
            }

            @Override
            public void onFailure(@NonNull Call<ApiCallingDataModal> call, @NonNull Throwable t) {
            showToast(t.getMessage());
            }
        });
    }


}