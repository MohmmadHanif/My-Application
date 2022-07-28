package com.example.myapplication.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonParsingMainActivity extends AppCompatActivity {
    RecyclerView rvJsonParsing;
    JsonParsingAdapter adapter;
    ArrayList<JsonParsingProductListModal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing_main);
        rvJsonParsing = findViewById(R.id.rvJsonParsing);
        list = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray(getJsonFromAssets(this));

            for (int i=0;i<= jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
             list.add(new JsonParsingProductListModal(
                     object.getString("productId"),
                     object.getString("productName"),
                     object.getString("productPrice"),
                     object.getString("productDescription"),
                     object.getString("productType"),
                     object.getString("productImage"),
                     object.getString("productRating"),
                     object.getString("productQuantity")
                     ));

            }

        }catch (Exception e){
            Log.e("TAG", "onCreate: "+e.getLocalizedMessage());
        }


        adapter = new JsonParsingAdapter(this,list);
        rvJsonParsing.setAdapter(adapter);
    }

    public static String getJsonFromAssets(Context context) {
        String jsonString = null;
        try {
            InputStream inputStream = context.getAssets().open("productList.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}