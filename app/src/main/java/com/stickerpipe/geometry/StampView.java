package com.stickerpipe.geometry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Dmitry Nezhydenko (dehimb@gmail.com)
 */

public class StampView extends View {
    private PointF leftPoint;
    private PointF rightPoint;
    private Paint pointsPaint;
    private Paint textPaint;
    private String color = "#FFFF0000";
    private Paint centerPointPaint;

    public StampView(Context context) {
        super(context);
        init();
    }

    public StampView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StampView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StampView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // redraw when view has measured
        invalidate();
    }

    private void init() {
        pointsPaint = new Paint();
        pointsPaint.setStyle(Paint.Style.STROKE);
        pointsPaint.setStrokeWidth(3);
        textPaint = new Paint();
        pointsPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(32);
        centerPointPaint = new Paint();
        centerPointPaint.setColor(Color.parseColor("#FF0000FF"));
    }

    public void setControlPoints(@NonNull PointF leftPoint, @NonNull PointF rightPoint) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setColor(Color.parseColor(color));
        pointsPaint.setColor(Color.parseColor(color));
        canvas.drawColor(Color.parseColor("#2000FF00"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 5, centerPointPaint);
        if (rightPoint != null && leftPoint != null) {
            canvas.drawCircle(leftPoint.x, leftPoint.y, 3, pointsPaint);
            canvas.drawText("L", leftPoint.x + 30, leftPoint.y, textPaint);
            canvas.drawCircle(rightPoint.x, rightPoint.y, 3, pointsPaint);
            canvas.drawText("R", rightPoint.x + 30, rightPoint.y, textPaint);
        }
    }

    public void setColor(String color) {
        this.color = color;
    }
}
