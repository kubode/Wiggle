package com.github.kubode.wiggle.sample;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.kubode.wiggle.WiggleHelper;

public class ItemView extends RelativeLayout {

    public ItemView(Context context, int position) {
        super(context);
        setMinimumHeight((int) (getResources().getDisplayMetrics().density * 80));
        inflate(context, R.layout.item, this);

        TextView frames = (TextView) findViewById(R.id.frames);
        frames.setText("Delay(frames): " + position);
        frames.addOnAttachStateChangeListener(new WiggleHelper(WiggleHelper.DelayType.FRAMES, position));

        TextView millis = (TextView) findViewById(R.id.millis);
        int delayMs = position * 40;
        millis.setText("Delay(ms): " + delayMs);
        millis.addOnAttachStateChangeListener(new WiggleHelper(WiggleHelper.DelayType.MILLISECONDS, delayMs));
    }
}
