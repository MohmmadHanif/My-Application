package com.example.myapplication.ApiCalling.PostApi;

import androidx.room.Delete;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApiInterface {


    @POST("v2/users")
    Call<PostDataModal> sendData(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization,
            @Body PostDataModal modal
            /* @Field("name") String name,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("status") String status*/);

    @GET("v2/users")
    Call<List<PostDataModal>> getData(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization
    );

    @DELETE("v2/users/{id}")
    Call<PostDataModal> deleteBook(
            @Header("Accept") String Accept,
            @Header("Content-Type") String Content,
            @Header("Authorization") String Authorization,
            @Path("id") int bookId

    );


}
