package com.example.myapplication.apicalling.getapicaliing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.apicalling.getapicaliing.Adapter.apiAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallingActivity extends AppCompatActivity {

    apiAdapter adapter;
    ArrayList<UserDataResp> dataArrayList;
    RecyclerView rvApi;
    ProgressDialog progressDialog;
    com.example.myapplication.apicalling.getapicaliing.getData getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_main);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        rvApi = findViewById(R.id.rvApi);
        dataArrayList = new ArrayList<>();
        adapter = new apiAdapter(dataArrayList,getApplicationContext());
        rvApi.setAdapter(adapter);
        getAllData();
    }

    private void getAllData() {
            getData = retrofitClientInstance.getRetrofitInstance().create(getData.class);
            getData.doGetUserList().enqueue(new Callback<BaseResponseModel<UserDataResp>>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponseModel<UserDataResp>> call, @NonNull Response<BaseResponseModel<UserDataResp>> response) {
                   progressDialog.dismiss();
                    if (response.body() != null){
                        dataArrayList.addAll(response.body().getData());
                        adapter = new apiAdapter(dataArrayList,getApplicationContext());
                        rvApi.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponseModel<UserDataResp>> call, @NonNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ApiCallingActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
}