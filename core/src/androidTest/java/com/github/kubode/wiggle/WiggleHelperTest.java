package com.github.kubode.wiggle;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.kubode.wiggle.test.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.github.kubode.wiggle.MyMatchers.withLastNon0TranslationY;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WiggleHelperTest {

    @Rule
    public ActivityTestRule<MockActivity> rule = new ActivityTestRule<>(MockActivity.class);

    @Before
    public void setUp() {
        onView(withId(R.id.view)).perform(scrollTo());
    }

    @Test
    public void lastNon0TranslationYGreaterThan0WhenSwipeUp() {
        onView(withId(R.id.scrollView))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .perform(swipeUp());
        onView(withId(R.id.view)).check(matches(withLastNon0TranslationY(greaterThan(0f))));
    }

    @Test
    public void lastNon0TranslationYLessThan0WhenSwipeDown() {
        onView(withId(R.id.scrollView))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .perform(swipeDown());
        onView(withId(R.id.view)).check(matches(withLastNon0TranslationY(lessThan(0f))));
    }
}
