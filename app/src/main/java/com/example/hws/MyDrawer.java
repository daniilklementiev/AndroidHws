package com.example.hws;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyDrawer extends View implements View.OnTouchListener {

    private Paint p = new Paint();
    private List<Point> points = new ArrayList<>();

    public MyDrawer(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
        p.setColor(Color.GREEN);
        p.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //for (Point point : points) {
        //    canvas.drawCircle(point.x, point.y, 7, p);
        //}

        p.setStrokeWidth(10);
        for (int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, p);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Point point = new Point();
        point.x = (int) event.getX();
        point.y = (int) event.getY();
        points.add(point);
        invalidate();
        return true;
    }
}