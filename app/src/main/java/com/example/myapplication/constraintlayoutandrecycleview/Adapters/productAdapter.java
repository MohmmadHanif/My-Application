package com.example.myapplication.constraintlayoutandrecycleview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.constraintlayoutandrecycleview.Modals.productModalClass;
import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.viewHolder> {
    Context context;
    List<productModalClass> productList;

    public productAdapter(Context context, List<productModalClass> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_constrant_recycleview_layout, parent, false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        productModalClass modalClass = productList.get(position);

        if (modalClass.getCheckPotion()) {
            enableView(holder);
        } else {
            disableView(holder);
        }

        holder.vegOrNonVeg.setImageResource(modalClass.getVegOrNonVegImage());
        holder.productImage.setImageResource(modalClass.getProductImage());
        holder.productName.setText(modalClass.getProductName());
        holder.productQuantity.setText(modalClass.getProductQuantity());
        holder.productOldPrice.setText(modalClass.getOldPrice());
        holder.productNewPrice.setText(modalClass.getNewPrice());
        holder.productStatus.setText(modalClass.getProductDeliveryStatus());
        holder.productAddItemCounts.setText(String.valueOf(modalClass.getCurrentCount()));


        //minus  and plus
        /*holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (modalClass.getCurrentCount() <= 1) {
                    holder.addProductBtn.setVisibility(View.VISIBLE);
                    modalClass.setCheckPotion(false);
                } else {
                    modalClass.setCurrentCount(modalClass.getCurrentCount() - 1);
                    notifyDataSetChanged();
                }

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modalClass.setCurrentCount(modalClass.getCurrentCount() + 1);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });*/

    }

    public void enableView(viewHolder holder) {
        holder.addProductBtn.setVisibility(View.GONE);
        holder.minus.setVisibility(View.VISIBLE);
        holder.plus.setVisibility(View.VISIBLE);
        holder.productAddItemCounts.setVisibility(View.VISIBLE);
    }

    public void disableView(viewHolder holder) {
        holder.addProductBtn.setVisibility(View.VISIBLE);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView vegOrNonVeg, productImage, plus, minus;
        MaterialTextView productQuantity, productName, productOldPrice, productNewPrice, productStatus, productAddItemCounts;
        MaterialButton addProductBtn;

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
            addProductBtn = itemView.findViewById(R.id.AddProductBtn);

          //  productModalClass adapterPosition = productList.get(getAdapterPosition());

            addProductBtn.setOnClickListener(view -> {

                productList.get(getAdapterPosition()).setCheckPotion(true);
                addProductBtn.setVisibility(View.GONE);
                minus.setVisibility(View.VISIBLE);
                plus.setVisibility(View.VISIBLE);
                productAddItemCounts.setVisibility(View.VISIBLE);
            });

            plus.setOnClickListener(view -> {
                productList.get(getAdapterPosition()).setCurrentCount(productList.get(getAdapterPosition()).getCurrentCount() + 1);
                notifyItemChanged(getAdapterPosition());
            });
            minus.setOnClickListener(view -> {
                if (productList.get(getAdapterPosition()).getCurrentCount() <= 1) {
                    addProductBtn.setVisibility(View.VISIBLE);
                    productList.get(getAdapterPosition()).setCheckPotion(false);
                } else {
                    productList.get(getAdapterPosition()).setCurrentCount(productList.get(getAdapterPosition()).getCurrentCount() - 1);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }


    }
}
