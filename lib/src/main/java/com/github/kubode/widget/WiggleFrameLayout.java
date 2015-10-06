package com.github.kubode.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;

public class WiggleFrameLayout extends FrameLayout {

    private Choreographer.FrameCallback callback;

    public WiggleFrameLayout(Context context) {
        super(context);
    }

    public WiggleFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WiggleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WiggleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr,
                             int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        callback = new Choreographer.FrameCallback() {
            int[] location;

            @Override
            public void doFrame(long frameTimeNanos) {
                if (location == null) {
                    location = new int[2];
                    injectLocation();
                } else {
                    int oldX = location[0];
                    int oldY = location[1];
                    injectLocation();
                    setTranslationX(oldX - location[0]);
                    setTranslationY(oldY - location[1]);
                }
                Choreographer.getInstance().postFrameCallback(this);
            }

            private void injectLocation() {
                ((View) getParent()).getLocationInWindow(location);
            }
        };
        Choreographer.getInstance().postFrameCallback(callback);
    }

    @Override
    protected void onDetachedFromWindow() {
        Choreographer.getInstance().removeFrameCallback(callback);
        callback = null;
        super.onDetachedFromWindow();
    }
}
