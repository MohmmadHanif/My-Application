package com.example.myapplication.AndroidActivityLifeCycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ApiCalling.GetApi.ApiCallingActivity;
import com.example.myapplication.ApiCalling.PostApi.PostToGetDataActivity;
import com.example.myapplication.Canvas.CanvasFirstActivity;
import com.example.myapplication.ConstraintLayoutAndRecycleView.ProductListActivity;
import com.example.myapplication.R;
import com.example.myapplication.Room.roomStudentMainActivity;
import com.example.myapplication.Sqlite.SqliteMainActivity;


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
        Button postApiDemo = findViewById(R.id.postApiDemo);

        constraintLayoutDemoBtn.setOnClickListener(this);
        canvasDemoBtn.setOnClickListener(this);
        sqliteDemo.setOnClickListener(this);
        roomDemo.setOnClickListener(this);
        apiDemo.setOnClickListener(this);
        postApiDemo.setOnClickListener(this);

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
        }else if (v.getId() == R.id.postApiDemo) {
            startActivity(new Intent(this, PostToGetDataActivity.class));
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