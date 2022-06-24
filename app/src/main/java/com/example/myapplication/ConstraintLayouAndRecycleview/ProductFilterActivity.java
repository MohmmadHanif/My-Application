package com.example.myapplication.ConstraintLayouAndRecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.myapplication.ConstraintLayouAndRecycleview.Adapters.productAdapter;
import com.example.myapplication.ConstraintLayouAndRecycleview.Adapters.productFilterAdapter;
import com.example.myapplication.ConstraintLayouAndRecycleview.Modals.productFilterModalClass;
import com.example.myapplication.ConstraintLayouAndRecycleview.ProductListActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ProductFilterActivity extends AppCompatActivity {

    ImageView backImage;
    RecyclerView RVFilterInterest;
    productFilterAdapter adapter;
    ArrayList<productFilterModalClass> productFilterModalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_filter);

        getSupportActionBar().hide();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backImage = findViewById(R.id.backImage);
        RVFilterInterest = findViewById(R.id.RVFilter);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        productFilterModalList = new ArrayList<>();
        adapter = new productFilterAdapter(productFilterModalList, getApplicationContext());
        RVFilterInterest.setAdapter(adapter);


        productFilterModalList.add(new productFilterModalClass(R.drawable.music));
        productFilterModalList.add(new productFilterModalClass(R.drawable.world));
        productFilterModalList.add(new productFilterModalClass(R.drawable.snow));
        productFilterModalList.add(new productFilterModalClass(R.drawable.camera));
        productFilterModalList.add(new productFilterModalClass(R.drawable.training));
        productFilterModalList.add(new productFilterModalClass(R.drawable.music));
        productFilterModalList.add(new productFilterModalClass(R.drawable.world));
        productFilterModalList.add(new productFilterModalClass(R.drawable.snow));
        productFilterModalList.add(new productFilterModalClass(R.drawable.camera));
        productFilterModalList.add(new productFilterModalClass(R.drawable.training));
        productFilterModalList.add(new productFilterModalClass(R.drawable.music));
        productFilterModalList.add(new productFilterModalClass(R.drawable.world));
        productFilterModalList.add(new productFilterModalClass(R.drawable.snow));
        productFilterModalList.add(new productFilterModalClass(R.drawable.camera));
        productFilterModalList.add(new productFilterModalClass(R.drawable.training));
        productFilterModalList.add(new productFilterModalClass(R.drawable.music));
        productFilterModalList.add(new productFilterModalClass(R.drawable.world));
        productFilterModalList.add(new productFilterModalClass(R.drawable.snow));
        productFilterModalList.add(new productFilterModalClass(R.drawable.camera));
        productFilterModalList.add(new productFilterModalClass(R.drawable.training));


    }

}