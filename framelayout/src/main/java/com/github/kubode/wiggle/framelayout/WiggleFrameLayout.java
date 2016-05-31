package com.github.kubode.wiggle.framelayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.github.kubode.wiggle.WiggleHelper;

public class WiggleFrameLayout extends FrameLayout {

    private final WiggleHelper helper = new WiggleHelper();

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
        helper.onViewAttachedToWindow(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        helper.onViewDetachedFromWindow(this);
        super.onDetachedFromWindow();
    }
}
