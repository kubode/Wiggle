package com.github.kubode.wiggle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class MockView extends View {

    private final WiggleHelper helper = new WiggleHelper();

    public MockView(Context context) {
        super(context);
    }

    public MockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
