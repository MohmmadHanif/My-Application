package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.DemoOOPS.Encapsulation;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("On Create","on create is running");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,A2.class));
            }
        });

        }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("On Start", "On Start Is Running");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("On Resume", "On Resume Is Running");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("On Pause", "On Pause is Running");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("On Stop", "On Stop is Runnig");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("On Restart","On Restart is Ruunig");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("On Destroy","On Destroy is Runnuig");
    }
}