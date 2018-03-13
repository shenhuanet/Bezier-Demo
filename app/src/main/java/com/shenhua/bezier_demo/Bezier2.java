package com.shenhua.bezier_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shenhua on 2017/7/26.
 * Email shenhuanet@126.com
 */
public class Bezier2 extends View {

    private Paint mPaint;
    private PointF a, b, c;

    public Bezier2(Context context) {
        this(context, null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
        a = new PointF(0, 0);
        b = new PointF(0, 0);
        c = new PointF(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制数据点和控制点
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(a.x, a.y, mPaint);
        canvas.drawPoint(b.x, b.y, mPaint);
        canvas.drawPoint(c.x, c.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(a.x, a.y, c.x, c.y, mPaint);
        canvas.drawLine(b.x, b.y, c.x, c.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.quadTo(c.x, c.y, b.x, b.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int currentX = w / 2;
        int currentY = h / 2;
        a.x = currentX - 150;
        a.y = currentY;
        b.x = currentX + 150;
        b.y = currentY;

        c.x = currentX;
        c.y = currentY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        c.x = event.getX();
        c.y = event.getY();
        invalidate();
        return true;
    }
}
