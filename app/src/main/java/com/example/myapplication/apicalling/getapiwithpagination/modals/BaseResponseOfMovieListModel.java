package com.example.myapplication.apicalling.getapiwithpagination.modals;

import java.util.ArrayList;

public class BaseResponseOfMovieListModel {
    int page;
    int total_pages;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    ArrayList<MovieListResultModal> results;

    public BaseResponseOfMovieListModel(int page, ArrayList<MovieListResultModal> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieListResultModal> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieListResultModal> results) {
        this.results = results;
    }
}
