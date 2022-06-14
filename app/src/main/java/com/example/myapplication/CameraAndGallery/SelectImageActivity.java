package com.example.myapplication.CameraAndGallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class SelectImageActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView SelectImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        SelectImage = findViewById(R.id.selectImageBtn);

        SelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    Log.e("Error",e.getLocalizedMessage());
                }
               //startActivityForResult(camera_intent,REQUEST_IMAGE_CAPTURE);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            SelectImage.setImageBitmap(imageBitmap);
        }
    }

}