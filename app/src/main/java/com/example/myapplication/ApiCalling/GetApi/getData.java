package com.example.myapplication.ApiCalling.GetApi;

import retrofit2.Call;
import retrofit2.http.GET;


public interface getData {
    @GET("/api/users?")
    Call<BaseResponseModel<UserDataResp>> doGetUserList();

}
