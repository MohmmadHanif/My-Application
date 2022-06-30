package com.example.myapplication.Canvas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class CanvasFirstActivity extends AppCompatActivity {

    MyCanvas myCanvas;
    private Button btnClear;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_first);
        constraintLayout = findViewById(R.id.conslayout);
        btnClear = findViewById(R.id.btnClear);
        myCanvas = new MyCanvas(this, null);
        constraintLayout.addView(myCanvas);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            myCanvas.clearView();

            }
        });
    }

    public static class MyCanvas extends View {

        Paint paint;
        Path path;

        public MyCanvas(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            paint = new Paint();
            path = new Path();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(15f);
            paint.setStrokeCap(Paint.Cap.ROUND);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path, paint);
            /*canvas.drawLine(150f, 153f, 280, 300, paint);*/
            canvas.drawCircle(250f, 550, 180f, paint);
            canvas.drawRect(650f,550f,1000,750f,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float xPos = event.getX();
            float yPos = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(xPos, yPos);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    path.lineTo(xPos, yPos);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_BUTTON_PRESS:
                    path.reset();
                default:
                    return false;
            }

            invalidate();
            return true;

        }

        public void clearView() {
            path = new Path();
            invalidate();
        }
    }
}