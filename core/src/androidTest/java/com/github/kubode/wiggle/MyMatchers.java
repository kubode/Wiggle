package com.github.kubode.wiggle;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MyMatchers {
    public static Matcher<View> withTranslationY(final Matcher<Float> translationYMatcher) {
        return new TypeSafeMatcher<View>(View.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with translationY: ");
                translationYMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(View view) {
                return translationYMatcher.matches(view.getTranslationY());
            }
        };
    }
}
