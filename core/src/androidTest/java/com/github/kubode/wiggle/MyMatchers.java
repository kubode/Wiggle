package com.github.kubode.wiggle;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class MyMatchers {
    public static Matcher<View> withLastNon0TranslationY(final Matcher<Float> lastNon0TranslationYMatcher) {
        return new BoundedMatcher<View, MockView>(MockView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with lastTranslationY: ");
                lastNon0TranslationYMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(MockView view) {
                return lastNon0TranslationYMatcher.matches(view.getLastNon0TranslationY());
            }
        };
    }
}
