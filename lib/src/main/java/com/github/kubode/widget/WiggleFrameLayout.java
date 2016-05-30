package com.github.kubode.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class WiggleFrameLayout extends FrameLayout {

    private final WiggleHelper helper = new WiggleHelper(this);

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
        helper.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        helper.onDetachedFromWindow();
        super.onDetachedFromWindow();
    }
}
