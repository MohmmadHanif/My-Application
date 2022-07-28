package com.example.myapplication.androidactivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.apicalling.getapicaliing.ApiCallingActivity;
import com.example.myapplication.apicalling.apicallingcrud.ApiCallingShowDataActivity;
import com.example.myapplication.apicalling.getapiwithpagination.MovieMainActivity;
import com.example.myapplication.apicalling.getapiwithpagination.modals.MovieListResultModal;
import com.example.myapplication.canvas.CanvasFirstActivity;
import com.example.myapplication.constraintlayoutandrecycleview.ProductListActivity;
import com.example.myapplication.R;
import com.example.myapplication.jsonparsing.JsonParsingMainActivity;
import com.example.myapplication.room.roomStudentMainActivity;
import com.example.myapplication.sqlite.SqliteMainActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("On Create", "on create is running");

        Button constraintLayoutDemoBtn = findViewById(R.id.constantLayoutDemo);
        Button canvasDemoBtn = findViewById(R.id.canvasDemo);
        Button sqliteDemo = findViewById(R.id.sqliteDemo);
        Button roomDemo = findViewById(R.id.roomDemo);
        Button apiDemo = findViewById(R.id.apiDemo);
        Button postApiDemo = findViewById(R.id.apiCrudDemo);
        Button apiPagination = findViewById(R.id.apiPagination);
        Button jsonParsing = findViewById(R.id.jsonParsing);

        constraintLayoutDemoBtn.setOnClickListener(this);
        canvasDemoBtn.setOnClickListener(this);
        sqliteDemo.setOnClickListener(this);
        roomDemo.setOnClickListener(this);
        apiDemo.setOnClickListener(this);
        postApiDemo.setOnClickListener(this);
        apiPagination.setOnClickListener(this);
        jsonParsing.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.constantLayoutDemo) {
            startActivity(new Intent(this, ProductListActivity.class));
        } else if (v.getId() == R.id.canvasDemo) {
            startActivity(new Intent(this, CanvasFirstActivity.class));
        }else if (v.getId() == R.id.sqliteDemo) {
            startActivity(new Intent(this, SqliteMainActivity.class));
        }else if (v.getId() == R.id.roomDemo) {
            startActivity(new Intent(this, roomStudentMainActivity.class));
        }else if (v.getId() == R.id.apiDemo) {
            startActivity(new Intent(this, ApiCallingActivity.class));
        }else if (v.getId() == R.id.apiCrudDemo) {
            startActivity(new Intent(this, ApiCallingShowDataActivity.class));
        }else if (v.getId() == R.id.apiPagination) {
            startActivity(new Intent(this, MovieMainActivity.class));
        }else if (v.getId() == R.id.jsonParsing) {
            startActivity(new Intent(this, JsonParsingMainActivity.class));
        }
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