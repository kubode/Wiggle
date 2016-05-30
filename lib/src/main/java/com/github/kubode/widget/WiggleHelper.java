package com.github.kubode.widget;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.view.Choreographer;
import android.view.View;

/**
 * Created by mkubode on 2016/05/30.
 */
public class WiggleHelper implements Choreographer.FrameCallback {

    private final Choreographer choreographer = Choreographer.getInstance();
    private final View view;
    private final int[] location = new int[2];
    private boolean isInitialized;

    @MainThread
    public WiggleHelper(@NonNull View view) {
        this.view = view;
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (isInitialized) {
            int oldX = location[0];
            int oldY = location[1];
            injectLocation();
            view.setTranslationX(oldX - location[0]);
            view.setTranslationY(oldY - location[1]);
        } else {
            injectLocation();
            isInitialized = true;
        }
        choreographer.postFrameCallback(this);
    }

    private void injectLocation() {
        ((View) view.getParent()).getLocationInWindow(location);
    }

    public void onAttachedToWindow() {
        isInitialized = false;
        choreographer.postFrameCallback(this);
    }

    public void onDetachedFromWindow() {
        choreographer.removeFrameCallback(this);
    }
}
