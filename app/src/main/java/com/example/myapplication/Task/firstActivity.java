package com.example.myapplication.Task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.util.ArrayList;
import java.util.List;

public class firstActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    EditText Name, PhoneNumber;
    ImageView aImage;
    Button aSubmitBtn;
    Bundle bundle;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap bitmapImage2;
    Uri galleryCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        bundle = new Bundle();
        Name = findViewById(R.id.aName);
        PhoneNumber = findViewById(R.id.aPhoneNumber);
        aImage = findViewById(R.id.aImage);
        aSubmitBtn = findViewById(R.id.aBtn);


        aImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkAndRequestPermissions(firstActivity.this)) {
                    chooseImage();
                }
            }
        });


        Intent i = getIntent();

        String name = i.getStringExtra("NAME");
        Name.setText(name);
        String phone = i.getStringExtra("PHONE");
        PhoneNumber.setText(phone);

        if (i.hasExtra("IMAGE")) {
            galleryCamera = Uri.parse(i.getStringExtra("IMAGE"));
            aImage.setImageURI(galleryCamera);
        }

        aSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String phone = PhoneNumber.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(firstActivity.this, "Plase enter Name ", Toast.LENGTH_SHORT).show();
                } else if (phone.length() <= 9 || phone.length() >= 11) {
                    Toast.makeText(getApplicationContext(), "Please Enter A Valid 10 Number Only!!", Toast.LENGTH_SHORT).show();
                } else if (galleryCamera == null){
                    Toast.makeText(firstActivity.this, "Please Select Image!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("PHONE", phone);
                    intent.putExtra("IMAGE", galleryCamera.toString());
                    startActivity(intent);

                }
            }
        });


    }


    private void chooseImage() {
        final CharSequence[] item = {"Gallery", "Camera", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Image");

        builder.setItems(item, new DialogInterface.OnClickListener() {
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

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            bundle = data.getExtras();
            bitmapImage2 = (Bitmap) bundle.get("data");


            galleryCamera = getImageUri(bitmapImage2);
            aImage.setImageURI(getImageUri(bitmapImage2));
        } else {
            final Uri imageUri = data.getData();

            galleryCamera = imageUri;
            aImage.setImageURI(imageUri);
        }
    }


    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            bundle = data.getExtras();
//            bitmapImage2= (Bitmap) bundle.get("data");
//
//
//            getImageUri(bitmapImage2);
//            aImage.setImageURI( getImageUri(bitmapImage2));
//
//
//        } else {
//            bundle.putString("data",data.getData().toString());
//            try {
//                bitmapImage2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            aImage.setImageBitmap(bitmapImage2);
//        }
//    }

    public Uri getImageUri(Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000, true);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), OutImage, "Title" + System.currentTimeMillis(), null);
        return Uri.parse(path);
    }

    public static boolean checkAndRequestPermissions(final Activity context) {

        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                                    "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();
                } else if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "FlagUp Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    chooseImage();
                }
                break;
        }
    }
}