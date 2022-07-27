package com.example.myapplication.apicalling.getapiwithpagination;

import com.example.myapplication.apicalling.getapiwithpagination.modals.BaseResponseOfMovieListModel;
import com.example.myapplication.apicalling.getapiwithpagination.modals.MovieListResultModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiEndPoints {

    @GET("movie/top_rated")
    Call<BaseResponseOfMovieListModel> getAllMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page

    );
}
