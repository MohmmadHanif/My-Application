package com.example.myapplication.apicalling.getapicaliing;

import retrofit2.Call;
import retrofit2.http.GET;


public interface getData {
    @GET("/api/users?")
    Call<BaseResponseModel<UserDataResp>> doGetUserList();

}
