package com.example.myapplication.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class CanvasFirstActivity extends AppCompatActivity implements View.OnClickListener {

    MyCanvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_first);
        ConstraintLayout constraintLayout = findViewById(R.id.consLayout);
        myCanvas = new MyCanvas(this, null);
        /*setContentView(myCanvas);*/
        constraintLayout.addView(myCanvas);

        Button btnClear = findViewById(R.id.btnClear);
        Button btnLine = findViewById(R.id.btnLine);
        Button btnOval = findViewById(R.id.btnOval);
        Button btnRectangle = findViewById(R.id.btnRect);

        btnClear.setOnClickListener(this);
        btnLine.setOnClickListener(this);
        btnOval.setOnClickListener(this);
        btnRectangle.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLine:
                myCanvas.mCurrentShape = myCanvas.SMOOTHLINE;

                Toast.makeText(this, "Line Shape Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnOval:
                myCanvas.mCurrentShape = myCanvas.OVAL;
                Toast.makeText(this, "Oval Shape Selected", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnRect:
                myCanvas.mCurrentShape = myCanvas.RECTANGLE;
                Toast.makeText(this, "Rectangle Shape Selected", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnClear:
                myCanvas.clear();
                Toast.makeText(this, "Clear Successful", Toast.LENGTH_SHORT).show();
                break;
            default:

        }
    }
}