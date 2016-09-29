package com.github.kubode.wiggle;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Choreographer;
import android.view.View;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Helper class for implement wiggle motion.
 * </p>
 * <p>
 * There are two ways of implement wiggle motion using {@code WiggleHelper}:
 * use in extended view class and use as {@link View.OnAttachStateChangeListener}.
 * Constructor must be called from a thread that already has a Looper associated with it.
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

    public enum DelayType {
        FRAMES(R.styleable.WiggleHelper_delayFrames, 1) {
            @NonNull
            @Override
            protected Frame extractFrameToFixPosition(long delayFrames, @NonNull Deque<Frame> frameDeque) {
                if (frameDeque.size() > delayFrames) {
                    return frameDeque.pollFirst();
                }
                return frameDeque.getFirst();
            }
        },
        MILLISECONDS(R.styleable.WiggleHelper_delayMillis, 0) {
            @NonNull
            @Override
            protected Frame extractFrameToFixPosition(long delayMillis, @NonNull Deque<Frame> frameDeque) {
                Frame last = frameDeque.getLast();
                Frame first = frameDeque.getFirst();
                long diffNanos = first.frameTimeNanos - last.frameTimeNanos;
                long diffMillis = TimeUnit.NANOSECONDS.toMillis(diffNanos);
                if (diffMillis >= delayMillis) {
                    return frameDeque.pollFirst();
                }
                return first;
            }
        };

        @StyleableRes
        private final int styleableRes;
        private final long defaultValue;

        DelayType(@StyleableRes int styleableRes, long defaultValue) {
            this.styleableRes = styleableRes;
            this.defaultValue = defaultValue;
        }

        @NonNull
        protected abstract Frame extractFrameToFixPosition(long value, @NonNull Deque<Frame> frameDeque);
    }

    private static final Pair<DelayType, Long> DEFAULT_TYPE_AND_VALUE = new Pair<>(DelayType.FRAMES, DelayType.FRAMES.defaultValue);

    private final Choreographer choreographer = Choreographer.getInstance();

    private final DelayType delayType;
    private final long delayValue;

    private Choreographer.FrameCallback callback;

    private static Pair<DelayType, Long> obtainTypeAndValue(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.WiggleHelper, defStyleAttr, defStyleRes);
        try {
            for (DelayType delayType : DelayType.values()) {
                if (arr.hasValue(delayType.styleableRes)) {
                    long delayValue = arr.getInteger(delayType.styleableRes, (int) delayType.defaultValue);
                    return new Pair<>(delayType, delayValue);
                }
            }
        } finally {
            arr.recycle();
        }
        return DEFAULT_TYPE_AND_VALUE;
    }

    public WiggleHelper(DelayType delayType, long delayValue) {
        this.delayType = delayType;
        this.delayValue = delayValue;
    }

    private WiggleHelper(Pair<DelayType, Long> typeAndValue) {
        this(typeAndValue.first, typeAndValue.second);
    }

    public WiggleHelper() {
        this(DEFAULT_TYPE_AND_VALUE);
    }

    public WiggleHelper(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        this(obtainTypeAndValue(context, attrs, defStyleAttr, defStyleRes));
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        callback = new MyFrameCallback(v);
        choreographer.postFrameCallback(callback);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        choreographer.removeFrameCallback(callback);
        callback = null;
    }

    private class MyFrameCallback implements Choreographer.FrameCallback {

        private final View view;
        private final Deque<Frame> frameDeque = new LinkedList<>();

        private MyFrameCallback(View view) {
            this.view = view;
        }

        @Override
        public void doFrame(long frameTimeNanos) {
            int[] location = new int[2];
            ((View) view.getParent()).getLocationInWindow(location);
            int xNow = location[0];
            int yNow = location[1];
            frameDeque.add(new Frame(frameTimeNanos, xNow, yNow));
            Frame frameToFixPosition = delayType.extractFrameToFixPosition(delayValue, frameDeque);
            int xFix = frameToFixPosition.x;
            int yFix = frameToFixPosition.y;
            view.setTranslationX(xFix - xNow);
            view.setTranslationY(yFix - yNow);
            choreographer.postFrameCallback(this);
        }
    }

    private static class Frame {
        private final long frameTimeNanos;
        private final int x;
        private final int y;

        private Frame(long frameTimeNanos, int x, int y) {
            this.frameTimeNanos = frameTimeNanos;
            this.x = x;
            this.y = y;
        }
    }
}
