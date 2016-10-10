package com.stickerpipe.geometry;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView labelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UnderlayView underlayView = (UnderlayView) findViewById(R.id.underlay);
        PointF demoPointA = new PointF(400, 800);
        PointF demoPointB = new PointF(800, 850);
        underlayView.addPoints(demoPointA, demoPointB);

        RelativeLayout parenContainer = (RelativeLayout) findViewById(R.id.parent_container);
        StampView stampView = new StampView(this);
        int stampSize = 400;
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(stampSize, stampSize);
        stampView.setLayoutParams(lp);
        PointF stampPointA = new PointF(200, 100);
        PointF stampPointB = new PointF(250, 300);
        stampView.setControlPoints(stampPointA, stampPointB);
        parenContainer.addView(stampView);

        float scale = Geometry.calculateScaleFactorByTwoLines(stampPointA, stampPointB, demoPointA, demoPointB);
        float rotationAngle = Geometry.angleBetween2Lines(stampPointA, stampPointB, demoPointA, demoPointB);

        PointF rotatedA = Geometry.rotatePoint(stampPointA.x, stampPointA.y, stampSize / 2, stampSize / 2, rotationAngle);
        PointF scaledA = Geometry.scalePoint(rotatedA.x, rotatedA.y, stampSize / 2, stampSize / 2, scale);

        stampView.setScaleX(scale);
        stampView.setScaleY(scale);
        stampView.setRotation(rotationAngle);

        stampView.setX(demoPointA.x - scaledA.x);
        stampView.setY(demoPointA.y - scaledA.y);

        labelView = (TextView) findViewById(R.id.lable_view);
        labelView.setText("Angle: " + rotationAngle + "\n"
                + "Scale: " + scale + "\n"
        );
    }
}