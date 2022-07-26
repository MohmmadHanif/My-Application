package com.example.myapplication.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas extends View {


    public static final int RECTANGLE = 3;
    public static final int OVAL = 5;
    public static final int SMOOTHLINE = 2;

    public int mCurrentShape;

    public Path mPath;
    public Paint mPaint;
    public Paint nPaint;
    public Bitmap mBitmap;
    public Canvas mCanvas;
    int countTouch;

    public boolean isDrawing = false;

    public float mStartX;
    public float mStartY;
    public float mEndX;
    public float mEndY;
    public float xPos;
    public float yPos;


    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);


        if (isDrawing) {
            switch (mCurrentShape) {
                case RECTANGLE:
                    onDrawRectangle(canvas);
                    break;
                case SMOOTHLINE:
                    onDrawLine(canvas);
                    break;
                case OVAL:
                    onDrawOval(canvas);
                    break;

                default:
                    onDrawLine(canvas);
            }
        }
    }

    public void clear() {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    protected void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        nPaint = new Paint(Paint.DITHER_FLAG);
        nPaint.setAntiAlias(true);
        nPaint.setDither(true);
        nPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        nPaint.setStyle(Paint.Style.FILL);
        nPaint.setStrokeJoin(Paint.Join.ROUND);
        nPaint.setStrokeCap(Paint.Cap.ROUND);
        nPaint.setStrokeWidth(5);

    }

/*    protected void reset() {
        mPath = new Path();
        countTouch = 0;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPos = event.getX();
        yPos = event.getY();
        switch (mCurrentShape) {
            case SMOOTHLINE:
                onTouchEventSmoothLine(event);
                break;
            case RECTANGLE:
                onTouchEventRectangle(event);
                break;
            case OVAL:
                onTouchEventOval(event);
                break;
            default:
                onTouchEventSmoothLine(event);
        }
        return true;
    }


    private void onDrawLine(Canvas canvas) {

        canvas.drawLine(xPos, yPos, xPos, yPos, mPaint);

    }

    private void onTouchEventSmoothLine(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                mCanvas.drawPath(mPath, mPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mPath.reset();
                invalidate();
                break;
        }
    }

    private void onDrawOval(Canvas canvas) {
        //  canvas.drawCircle(mStartX, mStartY, calculateRadius(mStartX, mStartY, xPos, yPos), mPaint);
        canvas.drawOval(mStartX, mStartY, mEndX, mEndY, mPaint);
    }

    private void onTouchEventOval(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = xPos;
                mStartY = yPos;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mEndX = event.getX();
                mEndY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                //mCanvas.drawCircle(mStartX, mStartY, calculateRadius(mStartX, mStartY, xPos, yPos), mPaintFinal);
                mCanvas.drawOval(mStartX, mStartY, mEndX, mEndY, mPaint);
                invalidate();
                break;
        }
    }


    protected float calculateRadius(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt(
                Math.pow(x1 - x2, 2) +
                        Math.pow(y1 - y2, 2)
        );
    }

    private void onDrawRectangle(Canvas canvas) {
        drawRectangle(canvas, mPaint);
    }

    private void onTouchEventRectangle(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = xPos;
                mStartY = yPos;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                drawRectangle(mCanvas, mPaint);
                invalidate();
                break;
        }
    }

    private void drawRectangle(Canvas canvas, Paint paint) {
        float right = Math.max(mStartX, xPos);
        float left = Math.min(mStartX, xPos);
        float bottom = Math.max(mStartY, yPos);
        float top = Math.min(mStartY, yPos);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public int drawClear() {
        mPath = new Path();
        invalidate();
        return 0;
    }

    /*Paint paint;
    Path path;
    int mStartX;
    int mStartY;
    int mEndX;
    int mEndY;
    float xPos,yPos;

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

       // canvas.drawRect(mStartX, mStartY, mEndX, mEndY, paint);
        *//*canvas.drawOval(mStartX, mStartY, mEndX, mEndY, paint);*//*
     *//*canvas.drawLine(150f, 153f, 280, 300, paint);
            canvas.drawRect(650f,550f,1000,750f,paint);*//*
     *//*path.addRect(mStartX, mStartY, mEndX, mEndY, Path.Direction.CCW);*//*
     *//*  path.addOval(mStartX, mStartY, mEndX, mEndY, Path.Direction.CW);*//*



    }

    public void rect(){
        path.addRect(mStartX,mStartY,mEndX,mEndY,Path.Direction.CCW);
        invalidate();
    }
    public void oval(){
          path.addOval(mStartX, mStartY, mEndX, mEndY, Path.Direction.CW);
          invalidate();
    }
    public void line(){
        path.lineTo(xPos,yPos);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xPos = event.getX();
        yPos = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                mStartX = (int) event.getX();
                mStartY = (int) event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                *//*path.lineTo(xPos,yPos);*//*
                mEndY = (int) event.getY();
                mEndX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                mEndX = (int) event.getX();
                mEndY = (int) event.getY();
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
    }*/
}