package com.example.myapplication.ApiCalling.PostApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiInstance {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://gorest.co.in/public/";

    public static Retrofit getPostInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
