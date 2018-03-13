package com.shenhua.bezier_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shenhua on 2017/7/26.
 * Email shenhuanet@126.com
 */
public class Bezier3 extends View {

    private Paint bgPaint;
    private Paint itPaint;
    private Path mPath;
    private Paint pathPaint;
    private int width;
    private int r;
    private int r2;
    private int progress = 0;
    private Paint helperDotPaint;
    /**
     * 开始点
     */
    private Point startPoint = new Point();
    /**
     * 控制点
     */
    private Point controlPoint = new Point();
    /**
     * 结束点
     */
    private Point endPoint = new Point();

    public Bezier3(Context context) {
        this(context, null);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        itPaint = new Paint(bgPaint);
        itPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccentA40));
        mPath = new Path();
        pathPaint = new Paint(bgPaint);
        helperDotPaint = new Paint(bgPaint);
        helperDotPaint.setColor(Color.BLACK);
        helperDotPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        r = width / 4;
        r2 = width / 13;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将坐标原点切换到右上角顶点
        canvas.translate(width, 0);
        // 画底部圆
        canvas.drawCircle(0, 0, r, bgPaint);
        // 画路径
        canvas.drawPath(mPath, pathPaint);
        // 画小球
        canvas.drawCircle(-progress * 4, progress * 4, r2, itPaint);

        canvas.drawPoint(controlPoint.x, controlPoint.y, helperDotPaint);

    }

    public void up(int progress) {
        this.progress = progress;
        updatePath(progress);
        invalidate();
    }

    private void updatePath(int progress) {
        Path path = mPath;
        path.reset();
        startPoint.set(-r, 0);
        path.moveTo(startPoint.x, startPoint.y);
        endPoint.set(0, r);
        if (progress > 50) {
            controlPoint.set(-(100 - progress) * 8, (100 - progress) * 8);
        } else {
            controlPoint.set(-progress * 8, progress * 8);
        }
        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
    }
}
