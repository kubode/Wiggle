package com.github.kubode.wiggle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MockView extends TextView {

    private final WiggleHelper helper = new WiggleHelper();
    private float lastNon0TranslationY;

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

    @Override
    public void setTranslationY(float translationY) {
        super.setTranslationY(translationY);
        if (translationY != 0f) setLastNon0TranslationY(translationY);
    }

    @SuppressLint("SetTextI18n")
    private void setLastNon0TranslationY(float lastNon0TranslationY) {
        this.lastNon0TranslationY = lastNon0TranslationY;
        setText("lastNon0TranslationY=" + lastNon0TranslationY);
    }

    public float getLastNon0TranslationY() {
        return lastNon0TranslationY;
    }
}
