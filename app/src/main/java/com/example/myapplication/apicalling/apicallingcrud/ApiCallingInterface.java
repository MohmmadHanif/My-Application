package com.example.myapplication.apicalling.apicallingcrud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCallingInterface {


    @POST("v2/users")
    Call<ApiCallingDataModal> sendData(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization,
            @Body ApiCallingDataModal modal
            /* @Field("name") String name,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("status") String status*/);

    @GET("v2/users")
    Call<List<ApiCallingDataModal>> getData(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization
    );

    @DELETE("v2/users/{id}")
    Call<ApiCallingDataModal> delete(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization,
            @Path("id") int bookId

    );

    @PUT("v2/users/{id}")
    Call<ApiCallingDataModal> update(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization,
            @Path("id") int Id,
            @Body ApiCallingDataModal modal
    );
}
