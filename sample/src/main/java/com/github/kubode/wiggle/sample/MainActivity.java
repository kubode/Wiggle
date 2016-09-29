package com.github.kubode.wiggle.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.kubode.wiggle.WiggleHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<Integer>(this, 0) {

            {
                for (int i = 0; i < 1000; i++) {
                    add(i);
                }
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                @SuppressLint("ViewHolder")
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                TextView frames = (TextView) view.findViewById(R.id.frames);
                TextView millis = (TextView) view.findViewById(R.id.millis);
                frames.setText("Delay frames: " + position);
                frames.addOnAttachStateChangeListener(new WiggleHelper(WiggleHelper.DelayType.FRAMES, position));
                millis.setText("Delay milliseconds: " + position * 10);
                millis.addOnAttachStateChangeListener(new WiggleHelper(WiggleHelper.DelayType.MILLISECONDS, position * 10));
                return view;
            }
        });
    }
}
