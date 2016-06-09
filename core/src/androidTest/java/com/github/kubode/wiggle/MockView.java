package com.github.kubode.wiggle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MockView extends TextView {

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

    @SuppressLint("SetTextI18n")
    @Override
    public void setTranslationY(float translationY) {
        super.setTranslationY(translationY);
        setText("translationY=" + translationY);
    }
}
