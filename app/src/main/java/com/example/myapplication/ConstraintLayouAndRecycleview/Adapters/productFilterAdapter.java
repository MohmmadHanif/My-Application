package com.example.myapplication.ConstraintLayouAndRecycleview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ConstraintLayouAndRecycleview.Modals.productFilterModalClass;
import com.example.myapplication.R;

import java.util.ArrayList;

public class productFilterAdapter extends RecyclerView.Adapter<productFilterAdapter.viewholder> {

    ArrayList<productFilterModalClass> filterModalClassArrayList;
    Context context;

    public productFilterAdapter(ArrayList<productFilterModalClass> filterModalClassArrayList, Context context) {
        this.filterModalClassArrayList = filterModalClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_interests_item_view,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.imgProductFilterInterests.setImageResource(filterModalClassArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return filterModalClassArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView imgProductFilterInterests;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            imgProductFilterInterests = itemView.findViewById(R.id.imgProductFilterInterests);
        }
    }
}
