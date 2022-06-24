package com.example.myapplication.ConstraintLayouAndRecycleview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ConstraintLayouAndRecycleview.Modals.productModalClass;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.viewHolder> {
    Context context;
    List<productModalClass> productList;
    int count = 0;

    public productAdapter(Context context, List<productModalClass> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        productModalClass modalClass = productList.get(position);
        holder.vegOrNonVeg.setImageResource(modalClass.getVegOrNonVegImage());
        holder.productImage.setImageResource(modalClass.getProductImage());
        holder.productName.setText(modalClass.getProductName());
        holder.productQuantity.setText(modalClass.getProductQuantity());
        holder.productOldPrice.setText(modalClass.getOldPrice());
        holder.productNewPrice.setText(modalClass.getNewPrice());
        holder.productStatus.setText(modalClass.getProductDeliveryStatus());
        holder.productAddItemCounts.setText(String.valueOf(count));

        //Add Btn
        holder.AddProductBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.AddProductBtn.setVisibility(View.GONE);
                holder.minus.setVisibility(View.VISIBLE);
                holder.plus.setVisibility(View.VISIBLE);
                holder.productAddItemCounts.setVisibility(View.VISIBLE);


            }
        });
        //Plus
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                holder.productAddItemCounts.setText(String.valueOf(count));
            }
        });
        //Minus
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (count <= 0) {

                } else {
                    count--;
                    holder.productAddItemCounts.setText(String.valueOf(count));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView vegOrNonVeg, productImage, plus, minus;
        MaterialTextView productQuantity, productName, productOldPrice, productNewPrice, productStatus, productAddItemCounts;
        MaterialButton AddProductBtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            vegOrNonVeg = itemView.findViewById(R.id.vegOrNonVegImage);
            productImage = itemView.findViewById(R.id.productImage);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productAddItemCounts = itemView.findViewById(R.id.addItemCount);
            productName = itemView.findViewById(R.id.productName);
            productOldPrice = itemView.findViewById(R.id.oldPrice);
            productNewPrice = itemView.findViewById(R.id.newPrice);
            productStatus = itemView.findViewById(R.id.productDeliveryStatus);
            AddProductBtn = itemView.findViewById(R.id.AddProductBtn);
            
        }
    }
}
