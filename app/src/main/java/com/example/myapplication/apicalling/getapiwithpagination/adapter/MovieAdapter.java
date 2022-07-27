package com.example.myapplication.apicalling.getapiwithpagination.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.apicalling.getapiwithpagination.modals.BaseResponseOfMovieListModel;
import com.example.myapplication.apicalling.getapiwithpagination.modals.MovieListResultModal;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.viewHolder> {
    ArrayList<MovieListResultModal> movieListResultModalArrayList;
    Context context;

    public MovieAdapter(ArrayList<MovieListResultModal> movieListResultModalArrayList, Context context) {
        this.movieListResultModalArrayList = movieListResultModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_getapi_movie_list_layout,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        MovieListResultModal modal = movieListResultModalArrayList.get(position);
        holder.tvMovieOverview.setText(modal.getOverview());
        holder.tvMovieRating.setText(String.valueOf(modal.getVote_average()));
        holder.tvMovieTitle.setText(modal.getTitle());
        holder.tvMovieReleaseDate.setText(modal.getRelease_date());
        holder.tvMovieTotalVoteCount.setText("Total Vote: " +modal.getVote_count());
        Glide.with(context).load("https://image.tmdb.org/t/p/w150" + modal.getPoster_path()+"?api_key=e888f0352c073cad34d54dd51e0ecd1e").into(holder.imgMoviePoster);
                }

    @Override
    public int getItemCount() {
        return movieListResultModalArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView imgMoviePoster,imgMovieStartIcon;
        MaterialTextView tvMovieRating, tvMovieTitle, tvMovieOverview, tvMovieReleaseDate, tvMovieTotalVoteCount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgMoviePoster = itemView.findViewById(R.id.imgMoviePoster);
            imgMovieStartIcon = itemView.findViewById(R.id.imgMovieStartIcon);
            tvMovieRating = itemView.findViewById(R.id.tvMovieRating);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieOverview = itemView.findViewById(R.id.tvMovieOverview);
            tvMovieReleaseDate = itemView.findViewById(R.id.tvMovieReleaseDate);
            tvMovieTotalVoteCount = itemView.findViewById(R.id.tvMovieTotalVoteCount);
        }
    }
}
