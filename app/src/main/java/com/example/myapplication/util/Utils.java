package com.example.myapplication.util;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.AppClass;

public class Utils {
    private static ProgressDialog progressDialog;

    public static void showToast(String message) {
        Toast.makeText(AppClass.context, message, Toast.LENGTH_LONG).show();
    }

    public static void initProgressDialog(Context context, String massage) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(massage);
    }
    public static void showProgressDialog() {
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public static void dismissProgressDialog() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
