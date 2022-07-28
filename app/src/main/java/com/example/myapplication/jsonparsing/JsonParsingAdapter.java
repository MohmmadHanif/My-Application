package com.example.myapplication.jsonparsing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class JsonParsingAdapter extends RecyclerView.Adapter<JsonParsingAdapter.viewHolder> {
    Context context;
    ArrayList<JsonParsingProductListModal> listModals;

    public JsonParsingAdapter(Context context, ArrayList<JsonParsingProductListModal> listModals) {
        this.context = context;
        this.listModals = listModals;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_parsing_layout,parent,false);
        return new viewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        JsonParsingProductListModal modal = listModals.get(position);
        Glide.with(context).load(modal.getProductImage()).placeholder(R.drawable.imagenotfoundicon).into(holder.imgProductJsonParsing);
        holder.tvProductNameJsonParsing.setText(modal.getProductName());
        holder.tvProductPriceJsonParsing.setText("Product Price: "+ modal.getProductPrice());
        holder.tvProductQuantityJsonParsing.setText(modal.getProductQuantity());
        holder.tvProductRatingJsonParsing.setText(modal.getProductRating());
        holder.tvProductTypeJsonParsing.setText("Type: "+modal.getProductType());
    }

    @Override
    public int getItemCount() {
        return listModals.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imgProductJsonParsing;
        MaterialTextView tvProductNameJsonParsing,tvProductPriceJsonParsing,tvProductQuantityJsonParsing,tvProductRatingJsonParsing, tvProductTypeJsonParsing;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductJsonParsing = itemView.findViewById(R.id.imgProductJsonParsing);
            tvProductNameJsonParsing = itemView.findViewById(R.id.tvProductNameJsonParsing);
            tvProductPriceJsonParsing = itemView.findViewById(R.id.tvProductPriceJsonParsing);
            tvProductQuantityJsonParsing = itemView.findViewById(R.id.tvProductQuantityJsonParsing);
            tvProductRatingJsonParsing = itemView.findViewById(R.id.tvProductRatingJsonParsing);
            tvProductTypeJsonParsing = itemView.findViewById(R.id.tvProductTypeJsonParsing);
        }
    }
}
