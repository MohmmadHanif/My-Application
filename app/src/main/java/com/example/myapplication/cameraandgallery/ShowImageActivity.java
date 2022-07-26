package com.example.myapplication.cameraandgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class ShowImageActivity extends AppCompatActivity {
    Button ShowActivityBtn;
    ImageView ShowImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

         ShowActivityBtn = findViewById(R.id.ShowBtn);
         ShowImage = findViewById(R.id.imageView);

        ShowActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),SelectImageActivity.class);
                //startActivityForResult(intent, 2);

                startActivityForResult(new Intent(getApplicationContext(),SelectImageActivity.class),2);

            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2)
        {
            Bundle extras = data.getBundleExtra("data");
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ShowImage.setImageBitmap(imageBitmap);

        }else {
            Toast.makeText(this, "Somthing Wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}