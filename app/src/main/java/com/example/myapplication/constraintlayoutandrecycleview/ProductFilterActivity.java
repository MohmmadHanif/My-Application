package com.example.myapplication.constraintlayoutandrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.constraintlayoutandrecycleview.Adapters.productFilterAdapter;
import com.example.myapplication.constraintlayoutandrecycleview.Modals.productFilterModalClass;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ProductFilterActivity extends AppCompatActivity{

    ImageView backImage;
    RecyclerView RVFilterInterest;
    productFilterAdapter adapter;
    ArrayList<productFilterModalClass> productFilterModalList;


    Button btnData, btnPopularity, btnDistance, btnFree, btnPaid, btnRange, btnFollow, btnAny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrant_productfilter);

        getSupportActionBar().hide();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backImage = findViewById(R.id.backImage);
        btnData = findViewById(R.id.btnSortBy1);
        btnPopularity = findViewById(R.id.btnSortBy2);
        btnDistance = findViewById(R.id.btnSortBy3);
        btnFree = findViewById(R.id.free);
        btnPaid = findViewById(R.id.paid);
        btnRange = findViewById(R.id.btnRanger);
        btnFollow = findViewById(R.id.btnFollow);
        btnAny = findViewById(R.id.btnAny);


        RVFilterInterest = findViewById(R.id.RVFilter);

        backImage.setOnClickListener(view -> onBackPressed());

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


        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnData.setSelected(true);
                btnPopularity.setSelected(false);
                btnDistance.setSelected(false);
            }
        });

        btnPopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPopularity.setSelected(true);
                btnData.setSelected(false);
                btnDistance.setSelected(false);
            }
        });

        btnDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDistance.setSelected(true);
                btnData.setSelected(false);
                btnPopularity.setSelected(false);
            }
        });


        btnRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRange.setSelected(true);
                btnFollow.setSelected(false);
                btnAny.setSelected(false);
            }
        });

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRange.setSelected(false);
                btnFollow.setSelected(true);
                btnAny.setSelected(false);
            }
        });

        btnAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRange.setSelected(false);
                btnFollow.setSelected(false);
                btnAny.setSelected(true);
            }
        });

        btnFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPaid.setSelected(false);
                btnFree.setSelected(true);
            }
        });
        btnPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPaid.setSelected(true);
                btnFree.setSelected(false);
            }
        });

    }


}



