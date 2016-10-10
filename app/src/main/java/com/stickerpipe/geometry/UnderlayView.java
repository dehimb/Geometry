package com.stickerpipe.geometry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Nezhydenko (dehimb@gmail.com)
 */

public class UnderlayView extends View {

    private List<PointF> points = new ArrayList<>();
    private Paint pointsPaint;
    private Paint gridPaint;

    public UnderlayView(Context context) {
        super(context);
        init();
    }

    public UnderlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UnderlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UnderlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        pointsPaint = new Paint();
        pointsPaint.setColor(Color.GREEN);
        pointsPaint.setStyle(Paint.Style.STROKE);
        pointsPaint.setStrokeWidth(3);
        gridPaint = new Paint();
        gridPaint.setColor(Color.WHITE);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(1);
    }

    public void addPoints(PointF... points) {
        for (PointF point : points) {
            if (point != null) {
                this.points.add(point);
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        for (PointF point : points) {
            canvas.drawCircle(point.x, point.y, 10, pointsPaint);
        }

    }

    private void drawBackground(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#FF000000"));
        for (int i = 0; i <= getWidth(); i += 50) {
            setGridPaintParam(i);
            canvas.drawLine(i, 0, i, getHeight(), gridPaint);
        }
        for (int i = 0; i <= getHeight(); i += 50) {
            setGridPaintParam(i);
            canvas.drawLine(0, i, getWidth(), i, gridPaint);
        }
    }

    private void setGridPaintParam(int i) {
        if (i % 250 == 0) {
            gridPaint.setColor(Color.parseColor(("#AFFFFFFF")));
        } else {
            gridPaint.setColor(Color.parseColor(("#50FFFFFF")));
        }
    }
}
