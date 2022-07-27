package com.example.myapplication.apicalling.getapicaliing;

import com.example.myapplication.apicalling.getapicaliing.Modals.BaseResponseModel;
import com.example.myapplication.apicalling.getapicaliing.Modals.UserDataResp;

import retrofit2.Call;
import retrofit2.http.GET;


public interface getData {
    @GET("/api/users?")
    Call<BaseResponseModel<UserDataResp>> doGetUserList();
}
