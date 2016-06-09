package com.github.kubode.wiggle;

import android.app.Activity;
import android.os.Bundle;

import com.github.kubode.wiggle.test.R;

public class MockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
