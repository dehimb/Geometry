package com.stickerpipe.geometry;

import android.graphics.PointF;

/**
 * @author Dmitry Nezhydenko (dehimb@gmail.com)
 */

public class Geometry {
    /**
     * Calculate angle between two lines with two given points
     *
     * @param A1 First point first line
     * @param A2 Second point first line
     * @param B1 First point second line
     * @param B2 Second point second line
     * @return Angle between two lines in degrees
     */
    public static float angleBetween2Lines(PointF A1, PointF A2, PointF B1, PointF B2) {
        float angle1 = (float) Math.atan2(A2.y - A1.y, A1.x - A2.x);
        float angle2 = (float) Math.atan2(B2.y - B1.y, B1.x - B2.x);
        float calculatedAngle = (float) Math.toDegrees(angle1 - angle2);
        if (calculatedAngle < 0) calculatedAngle += 360;
        return calculatedAngle;
    }

    /**
     * Calculate scale factor between two lines with two given points
     *
     * @param A1 First point first line
     * @param A2 Second point first line
     * @param B1 First point second line
     * @param B2 Second point second line
     * @return Scale factor
     */
    public static float calculateScaleFactorByTwoLines(PointF A1, PointF A2, PointF B1, PointF B2) {
        float lengthA = (float) Math.sqrt(Math.pow(A2.x - A1.x, 2) + Math.pow(A2.y - A1.y, 2));
        float lengthB = (float) Math.sqrt(Math.pow(B2.x - B1.x, 2) + Math.pow(B2.y - B1.y, 2));
        return lengthB / lengthA;
    }

    /**
     * Calculate now point coordinates after rotation
     *
     * @param x             Point x
     * @param y             Point y
     * @param cx            Rotation center x
     * @param cy            Rotation center y
     * @param rotationAngle Angle to rotate
     * @return Rotated point
     */
    public static PointF rotatePoint(float x, float y, int cx, int cy, float rotationAngle) {
        // according to Android coordinates system, we need to change rotation direction
        float translatedAngle = 360 - rotationAngle;
        double radianAngle = translatedAngle * Math.PI / 180;
        float deltaX = x - cx;
        float deltaY = y - cy;
        float rotatedDeltaY = (float) (deltaY * Math.cos(radianAngle) - deltaX * Math.sin(radianAngle));
        float rotatedDeltaX = (float) (deltaY * Math.sin(radianAngle) + deltaX * Math.cos(radianAngle));
        return new PointF(rotatedDeltaX + cx, rotatedDeltaY + cy);
    }

    /**
     * Apply translation to point after scaling
     *
     * @param x     Point x
     * @param y     Point y
     * @param cx    Scale center x
     * @param cy    Scale center y
     * @param scale Scale
     * @return Scaled point coordinates
     */
    public static PointF scalePoint(float x, float y, int cx, int cy, float scale) {
        float centerDeltaX = x - cx;
        float centerDeltaY = y - cy;
        float scaledDeltaX = Math.abs(Math.abs(centerDeltaX) - Math.abs(centerDeltaX) * scale);
        float scaledDeltaY = Math.abs(Math.abs(centerDeltaY) - Math.abs(centerDeltaY) * scale);
        float scaledTranslationX, scaledTranslationY;
        if ((centerDeltaX < 0 && scale > 1) || (centerDeltaX > 0 && scale < 1)) {
            scaledTranslationX = -scaledDeltaX;
        } else {
            scaledTranslationX = scaledDeltaX;
        }
        if ((centerDeltaY < 0 && scale > 1) || (centerDeltaY > 0 && scale < 1)) {
            scaledTranslationY = -scaledDeltaY;
        } else {
            scaledTranslationY = scaledDeltaY;
        }
        return new PointF(x + scaledTranslationX, y + scaledTranslationY);
    }
}
