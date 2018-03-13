package com.shenhua.bezier_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    PullView pullView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullView = (PullView) findViewById(R.id.pullView);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    private float mTouchStartY;
    private static final float TOUCH_MOVE_MAX_Y = 300;
    private static final float SLIPPAGE_FACTOR = 0.5f;// 拖动阻力因子 0~1

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                if (y >= mTouchStartY) {
                    float moveSize = (y - mTouchStartY) * SLIPPAGE_FACTOR;
                    float progress = moveSize >= TOUCH_MOVE_MAX_Y ? 1 : (moveSize / TOUCH_MOVE_MAX_Y);
                    pullView.setProgress(progress);
                }
                return true;
            case MotionEvent.ACTION_UP:
                pullView.release();
                return true;
            default:
                break;

        }
        return false;
    }

    private ScheduledExecutorService scheduledExecutorService;
    int i = 0;

    @Override
    protected void onResume() {
        super.onResume();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    Log.d("shenhuaLog -- " + MainActivity.class.getSimpleName(), "run: " + Thread.currentThread().getName());
                    Log.d("shenhuaLog -- " + MainActivity.class.getSimpleName(), "run: >>> " + ++i);
                }
            }, 50, TimeUnit.MILLISECONDS);
        }
    }
}
