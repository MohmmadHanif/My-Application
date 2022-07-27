package com.example.myapplication.apicalling.getapiwithpagination;

import static com.example.myapplication.util.Utils.dismissProgressDialog;
import static com.example.myapplication.util.Utils.initProgressDialog;
import static com.example.myapplication.util.Utils.showProgressDialog;
import static com.example.myapplication.util.Utils.showToast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.apicalling.getapiwithpagination.adapter.MovieAdapter;
import com.example.myapplication.apicalling.getapiwithpagination.modals.BaseResponseOfMovieListModel;
import com.example.myapplication.apicalling.getapiwithpagination.modals.MovieListResultModal;
import com.example.myapplication.util.*;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieMainActivity extends AppCompatActivity {

    MovieAdapter adapter;
    ArrayList<MovieListResultModal> list;
    RecyclerView rvMovie;
    ProgressBar fetchLoader;
    boolean isScrolling = false;
    int currentItem, totalItem, scrollOutItem;
  //  int totalPage =0;
    int page=1;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);

        rvMovie = findViewById(R.id.rvMovie);

        list = new ArrayList<>();
        adapter = new MovieAdapter(list, this);
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvMovie.setLayoutManager(manager);
        rvMovie.setAdapter(adapter);
        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling =true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = manager.getChildCount();
                totalItem = manager.getItemCount();
                scrollOutItem = manager.findFirstCompletelyVisibleItemPosition();

                if (isScrolling && (currentItem + scrollOutItem) == totalItem){
                    isScrolling = false;
                    page++;
                    initProgressDialog(MovieMainActivity.this,"Wait");
                    showProgressDialog();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            fetchData();
                        }
                    },2000);

                }

                /*if (isScrolling && page < totalPage){
                    page++;
                    isScrolling = false;
                    fetchData();
                }*/
            }
        });

        MovieApiEndPoints endPoints = MovieUtils.getRetrofitInstance().create(MovieApiEndPoints.class);
        endPoints.getAllMovieList("e888f0352c073cad34d54dd51e0ecd1e", "en-US", page).enqueue(new Callback<BaseResponseOfMovieListModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<BaseResponseOfMovieListModel> call, Response<BaseResponseOfMovieListModel> response) {
                if (response != null) {
                    //totalPage = response.body().getTotal_pages();
                    list.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BaseResponseOfMovieListModel> call, Throwable t) {
                showToast(t.getLocalizedMessage());
            }
        });
    }

    private void fetchData() {

        MovieApiEndPoints endPoints = MovieUtils.getRetrofitInstance().create(MovieApiEndPoints.class);
        endPoints.getAllMovieList("e888f0352c073cad34d54dd51e0ecd1e", "en-US", page).enqueue(new Callback<BaseResponseOfMovieListModel>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<BaseResponseOfMovieListModel> call, Response<BaseResponseOfMovieListModel> response) {
                if (response != null) {
                    list.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                    dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<BaseResponseOfMovieListModel> call, Throwable t) {
                showToast(t.getLocalizedMessage());
            }
        });
      /* new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               showToast("data Loading");
           }
       },5000);*/
    }

    private List<MovieListResultModal> fetchResults(Response<BaseResponseOfMovieListModel> response) {
        BaseResponseOfMovieListModel topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }
}