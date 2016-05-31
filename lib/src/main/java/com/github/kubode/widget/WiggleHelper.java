package com.github.kubode.widget;

import android.view.Choreographer;
import android.view.View;

/**
 * <p>
 * Helper class for implement wiggle motion.
 * </p>
 * <p>
 * There are two ways of implement wiggle motion using {@code WiggleHelper}:
 * use in extended view class and use as {@link View.OnAttachStateChangeListener}.
 * </p>
 * Extend view class example:
 * <pre>{@code
 * public class WiggleView extends View {
 *     private final WiggleHelper helper = new WiggleHelper();
 *     // Constructors...
 *     &#64;Override
 *     protected void onAttachedToWindow() {
 *         super.onAttachedToWindow();
 *         helper.onViewAttachedToWindow(this);
 *     }
 *     &#64;Override
 *     protected void onDetachedFromWindow() {
 *         helper.onViewDetachedFromWindow(this);
 *         super.onDetachedFromWindow();
 *     }
 * }
 * }</pre>
 * OnAttachStateChangeListener example:
 * <pre>{@code
 * View view = findViewById(R.id.wiggle);
 * view.addOnAttachStateChangeListener(new WiggleHelper());
 * }</pre>
 * Note: Don't share same {@code WiggleHelper} instance with multiple views.
 */
public class WiggleHelper implements View.OnAttachStateChangeListener {

    private final Choreographer choreographer = Choreographer.getInstance();
    private final int[] location = new int[2];

    private boolean isInitialized;
    private View view;

    private final Choreographer.FrameCallback callback = new Choreographer.FrameCallback() {
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
    };

    private void injectLocation() {
        ((View) view.getParent()).getLocationInWindow(location);
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        isInitialized = false;
        view = v;
        choreographer.postFrameCallback(callback);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        view = null;
        choreographer.removeFrameCallback(callback);
    }
}
