package com.github.kubode.wiggle.framelayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.github.kubode.wiggle.WiggleHelper;

/**
 * Wiggle motion {@link FrameLayout}.
 *
 * @see WiggleHelper
 */
public class WiggleFrameLayout extends FrameLayout {

    private final WiggleHelper helper;

    public WiggleFrameLayout(Context context) {
        this(context, null);
    }

    public WiggleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WiggleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = new WiggleHelper(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WiggleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        helper = new WiggleHelper(context, attrs, defStyleAttr, defStyleRes);
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
