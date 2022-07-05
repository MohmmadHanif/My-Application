package com.example.myapplication.Canvas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

import java.util.Random;

public class CanvasDemo extends AppCompatActivity implements View.OnClickListener {

    MyCanvas myCanvas;
    Canvas canvas;

    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private Button btnSquare, btnCircle, btnTriangle, btnUndo, btnState;
    private int mSuareCount = 0, mCircleCount = 0, mTriangelCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_canvas_demo);
        initViews();
    }

    private void initViews() {
        mContext = getApplicationContext();
        mResources = getResources();
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnCircle = (Button) findViewById(R.id.btnLine);
        btnTriangle = (Button) findViewById(R.id.btnTriangle);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btnSquare.setOnClickListener(this);
        btnCircle.setOnClickListener(this);
        btnTriangle.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSquare:
                drawSquare(null);
                mSuareCount++;
                break;

            case R.id.btnLine:
                drawCircle(null);
                mCircleCount++;
                break;

            case R.id.btnTriangle:
                drawTriangle(null);
                mTriangelCount++;
                break;

            case R.id.btnUndo:

                break;

        }
    }

    private void drawSquare(ImageView imageView) {
        Bitmap bitmap = Bitmap.createBitmap(
                50, // Width
                50, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.LTGRAY);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);

        int padding = 50;
        Rect rectangle = new Rect(
                padding, // Left
                padding, // Top
                canvas.getWidth() - padding, // Right
                canvas.getHeight() - padding // Bottom
        );
        canvas.drawRect(rectangle, paint);
        addViews(bitmap, imageView, 1);

        // Display the newly created bitmap on app interface
        if (imageView == null) {
            imageView = new ImageView(this);
        }
        imageView.setImageBitmap(bitmap);

        final ImageView finalImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawCircle(finalImageView);
                mSuareCount--;
                mCircleCount++;
            }
        });
    }

    private void drawCircle(ImageView imageView) {
        Bitmap bitmap = Bitmap.createBitmap(
                50, // Width
                50, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        int radius = Math.min(canvas.getWidth(), canvas.getHeight() / 2);
        int padding = 5;
        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getHeight() / 2, // cy
                radius - padding, // Radius
                paint // Paint
        );

        addViews(bitmap, imageView, 2);

        // Display the newly created bitmap on app interface
        if (imageView == null) {
            imageView = new ImageView(this);
        }
        imageView.setImageBitmap(bitmap);

        final ImageView finalImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawTriangle(finalImageView);
                mCircleCount--;
                mTriangelCount++;
            }
        });

    }

    private void drawTriangle(ImageView imageView) {
        Bitmap bitmap = Bitmap.createBitmap(
                500, // Width
                500, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                bitmap.getHeight());


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        Point point1_draw = new Point(90, 0);
        Point point2_draw = new Point(0, 180);
        Point point3_draw = new Point(180, 180);

        Path path = new Path();
        path.moveTo(point1_draw.x, point1_draw.y);
        path.lineTo(point2_draw.x, point2_draw.y);
        path.lineTo(point3_draw.x, point3_draw.y);
        path.lineTo(point1_draw.x, point1_draw.y);
        path.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#3F51B5"));
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //addViews(bitmap,imageView);

        addViews(bitmap, imageView, 3);

        if (imageView == null) {
            imageView = new ImageView(this);
        }
        imageView.setImageBitmap(bitmap);
        final ImageView finalImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSquare(finalImageView);
                mSuareCount++;
                mTriangelCount--;
            }
        });

    }

    private void addViews(Bitmap bitmap, ImageView imageView, final int value) {
        final int min = 20;
        final int max = 80;


        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher_round);
        final int w = d.getIntrinsicWidth();
        final int random = new Random().nextInt((max - min) + 1) + min;

        RelativeLayout relative4 = (RelativeLayout) findViewById(R.id.relative4);
        int width = relative4.getMeasuredWidth();
        int height = relative4.getMeasuredHeight();
        if (imageView == null) {
            imageView = new ImageView(this);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.setMargins(new Random().nextInt((width - 0) + 1), new Random().nextInt((height - 0) + 1), 10, 10);
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bitmap);


        if (imageView != null) {
            ViewGroup parent = (ViewGroup) imageView.getParent();
            if (parent != null) {
                parent.removeView(imageView);
            }
        }

        relative4.addView(imageView);

        final ImageView finalImageView = imageView;


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (value) {
                    case 1:
                        drawCircle(finalImageView);
                        mSuareCount--;
                        mCircleCount++;
                        break;

                    case 2:
                        drawTriangle(finalImageView);
                        mCircleCount--;
                        mTriangelCount++;
                        break;

                    case 3:
                        drawSquare(finalImageView);
                        mTriangelCount--;
                        mSuareCount++;
                        break;

                }

            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View v) {
                switch (value) {
                    case 1:
                        relative4.removeView(finalImageView);
                        mSuareCount--;
                        break;

                    case 2:
                        relative4.removeView(finalImageView);
                        mCircleCount--;
                        break;

                    case 3:
                        relative4.removeView(finalImageView);
                        mTriangelCount--;
                        break;
                }
                return true;
            }
        });


    }
}