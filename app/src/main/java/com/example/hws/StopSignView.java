package com.example.hws;

import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class StopSignView extends View {

    private Paint outerCirclePaint;
    private Paint innerRectanglePaint;
    private Paint borderPaint;

    public StopSignView(Context context) {
        super(context);
        init();
    }

    public StopSignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StopSignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.RED);
        outerCirclePaint.setStyle(Paint.Style.FILL);

        innerRectanglePaint = new Paint();
        innerRectanglePaint.setColor(Color.WHITE);
        innerRectanglePaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 10;

        // Отрисовка красного круга
        canvas.drawCircle(centerX, centerY, radius, outerCirclePaint);

        // Отрисовка белого прямоугольника
        float rectWidth = radius * 1.5f;
        float rectHeight = radius * 0.6f;
        float rectLeft = centerX - rectWidth / 2;
        float rectTop = centerY - rectHeight / 2;
        float rectRight = rectLeft + rectWidth;
        float rectBottom = rectTop + rectHeight;
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, innerRectanglePaint);

        // Отрисовка черной окантовки по кругу
        RectF borderRect = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(borderRect, 0, 360, false, borderPaint);
    }
}
