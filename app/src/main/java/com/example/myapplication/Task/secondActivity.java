package com.example.myapplication.Task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

public class secondActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText Name,Phone;
    ImageView bImage;
    Button bSubmitBtn;
    Bundle extras;
    Bitmap bitmapImage;
    Uri cameraGalleryImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        extras = new Bundle();
       Name  =findViewById(R.id.bName);
        Phone  =findViewById(R.id.bPhoneNumber);
        bImage = findViewById(R.id.bImage);
        bSubmitBtn = findViewById(R.id.bBtn);
        Intent i = getIntent();

        String name = i.getStringExtra("NAME");
        Name.setText(name);
        String phone = i.getStringExtra("PHONE");
        Phone.setText(phone);
        cameraGalleryImage = Uri.parse(i.getStringExtra("IMAGE"));
        bImage.setImageURI(cameraGalleryImage);
        /* if (i.hasExtra("IMAGE")){

           cameraGalleryImage= bImage.setImageURI(Uri.parse(i.getStringExtra("IMAGE")));

        }*/

        bImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        bSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(secondActivity.this, "Plase enter Name ", Toast.LENGTH_SHORT).show();
                } else if (phone.length() <= 9 || phone.length() >= 11) {
                    Toast.makeText(getApplicationContext(), "Please Enter A Valid 10 Number Only!!", Toast.LENGTH_SHORT).show();
                } else if (cameraGalleryImage == null){
                    Toast.makeText(secondActivity.this, "Please Select Image!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), firstActivity.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("PHONE", phone);
                    intent.putExtra("IMAGE",cameraGalleryImage.toString());
                    startActivity(intent);

                }
            }
        });
    }


    //Camera And Gallery Code
    private void selectImage(){
        final CharSequence[] item = {"Gallery","Camera","Cancel"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");

        dialog.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (item[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, 2);

                } else if (item[i].equals("Camera")) {
                    Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startActivityForResult(CameraIntent, REQUEST_IMAGE_CAPTURE);
                    } catch (Exception e) {
                        Log.e("Error", e.getLocalizedMessage());
                    }

                } else {
                    dialogInterface.dismiss();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == RESULT_OK){
                extras = data.getExtras();
                bitmapImage = (Bitmap) extras.get("data");

                cameraGalleryImage = getImageUri(bitmapImage);

                bImage.setImageURI(cameraGalleryImage);
            }
         else{
                final Uri image = data.getData();

                cameraGalleryImage = image;

                bImage.setImageURI(cameraGalleryImage);
         }

    }

    public Uri getImageUri(Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), OutImage, "Title"+System.currentTimeMillis(), null);
        return Uri.parse(path);
    }
}

