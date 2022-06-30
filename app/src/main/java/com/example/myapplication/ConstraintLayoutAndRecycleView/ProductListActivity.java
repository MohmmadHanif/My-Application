package com.example.myapplication.ConstraintLayoutAndRecycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ConstraintLayoutAndRecycleView.Adapters.productAdapter;
import com.example.myapplication.ConstraintLayoutAndRecycleView.Modals.productModalClass;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    ArrayList<productModalClass> productList;
    productAdapter adapter;
    TextView totalProduct;
    ImageView filterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        productRecyclerView = findViewById(R.id.recycleView);
        totalProduct = findViewById(R.id.totalProduct);
        filterImage = findViewById(R.id.filterImage);
        productList = new ArrayList<>();

        filterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductFilterActivity.class));
            }
        });

        productList.add(new productModalClass("Rs" + "100", "90", "1.5 KG", "Apple- Best of Himalaya's", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.apple, "veg"));
        productList.add(new productModalClass("Rs" + "250", "185", "1 KG", "Fresh Chicken Lag Piece", "Express Delivery  (Tomorrow Evening)", R.drawable.nonveg, R.drawable.chicken, "nonveg"));
        productList.add(new productModalClass("Rs" + "500", "433", "500 G", "Nutella - Best Taste of Hazelnuts", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.nutella, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "200 G", "Fresh Wheat Bread", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.bread, "veg"));
        productList.add(new productModalClass("Rs" + "70", "50", "100 G", "Best Milk Toast", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.toast, "veg"));
        productList.add(new productModalClass("Rs" + "40", "35", "200 G", "Meggi ", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.maggi, "veg"));
        productList.add(new productModalClass("Rs" + "30", "23", "1 KG", "Banana- Best of Himalaya's", "Express Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.banana, "veg"));
        productList.add(new productModalClass("Rs" + "110", "90", "100 G", "Amul- Butter", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.butter, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "1.5 KG", "Apple- Best of Himalaya's", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.apple, "veg"));
        productList.add(new productModalClass("Rs" + "250", "185", "1 KG", "Fresh Chicken Lag Piece", "Express Delivery  (Tomorrow Evening)", R.drawable.nonveg, R.drawable.chicken, "nonveg"));
        productList.add(new productModalClass("Rs" + "500", "433", "500 G", "Nutella - Best Taste of Hazelnuts", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.nutella, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "200 G", "Fresh Wheat Bread", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.bread, "veg"));
        productList.add(new productModalClass("Rs" + "70", "50", "100 G", "Best Milk Toast", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.toast, "veg"));
        productList.add(new productModalClass("Rs" + "40", "35", "200 G", "Meggi ", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.maggi, "veg"));
        productList.add(new productModalClass("Rs" + "30", "23", "1 KG", "Banana- Best of Himalaya's", "Express Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.banana, "veg"));
        productList.add(new productModalClass("Rs" + "110", "90", "100 G", "Amul- Butter", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.butter, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "1.5 KG", "Apple- Best of Himalaya's", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.apple, "veg"));
        productList.add(new productModalClass("Rs" + "250", "185", "1 KG", "Fresh Chicken Lag Piece", "Express Delivery  (Tomorrow Evening)", R.drawable.nonveg, R.drawable.chicken, "nonveg"));
        productList.add(new productModalClass("Rs" + "500", "433", "500 G", "Nutella - Best Taste of Hazelnuts", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.nutella, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "200 G", "Fresh Wheat Bread", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.bread, "veg"));
        productList.add(new productModalClass("Rs" + "70", "50", "100 G", "Best Milk Toast", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.toast, "veg"));
        productList.add(new productModalClass("Rs" + "40", "35", "200 G", "Meggi ", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.maggi, "veg"));
        productList.add(new productModalClass("Rs" + "30", "23", "1 KG", "Banana- Best of Himalaya's", "Express Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.banana, "veg"));
        productList.add(new productModalClass("Rs" + "110", "90", "100 G", "Amul- Butter", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.butter, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "1.5 KG", "Apple- Best of Himalaya's", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.apple, "veg"));
        productList.add(new productModalClass("Rs" + "250", "185", "1 KG", "Fresh Chicken Lag Piece", "Express Delivery  (Tomorrow Evening)", R.drawable.nonveg, R.drawable.chicken, "nonveg"));
        productList.add(new productModalClass("Rs" + "500", "433", "500 G", "Nutella - Best Taste of Hazelnuts", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.nutella, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "200 G", "Fresh Wheat Bread", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.bread, "veg"));
        productList.add(new productModalClass("Rs" + "70", "50", "100 G", "Best Milk Toast", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.toast, "veg"));
        productList.add(new productModalClass("Rs" + "40", "35", "200 G", "Meggi ", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.maggi, "veg"));
        productList.add(new productModalClass("Rs" + "30", "23", "1 KG", "Banana- Best of Himalaya's", "Express Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.banana, "veg"));
        productList.add(new productModalClass("Rs" + "110", "90", "100 G", "Amul- Butter", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.butter, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "1.5 KG", "Apple- Best of Himalaya's", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.apple, "veg"));
        productList.add(new productModalClass("Rs" + "250", "185", "1 KG", "Fresh Chicken Lag Piece", "Express Delivery  (Tomorrow Evening)", R.drawable.nonveg, R.drawable.chicken, "nonveg"));
        productList.add(new productModalClass("Rs" + "500", "433", "500 G", "Nutella - Best Taste of Hazelnuts", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.nutella, "veg"));
        productList.add(new productModalClass("Rs" + "100", "90", "200 G", "Fresh Wheat Bread", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.bread, "veg"));
        productList.add(new productModalClass("Rs" + "70", "50", "100 G", "Best Milk Toast", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.toast, "veg"));
        productList.add(new productModalClass("Rs" + "40", "35", "200 G", "Meggi ", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.maggi, "veg"));
        productList.add(new productModalClass("Rs" + "30", "23", "1 KG", "Banana- Best of Himalaya's", "Express Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.banana, "veg"));
        productList.add(new productModalClass("Rs" + "110", "90", "100 G", "Amul- Butter", "Standard Delivery  (Tomorrow Evening)", R.drawable.veg, R.drawable.butter, "veg"));

        totalProduct.setText(productList.size() + " Products found");

        adapter = new productAdapter(this, productList);
        productRecyclerView.setAdapter(adapter);


    }
}