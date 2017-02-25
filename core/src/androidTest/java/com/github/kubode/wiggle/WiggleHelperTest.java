package com.github.kubode.wiggle;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.github.kubode.wiggle.MyMatchers.withLastNon0TranslationY;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@RunWith(AndroidJUnit4.class)
public class WiggleHelperTest {

    @Rule
    public ActivityTestRule<MockActivity> rule = new ActivityTestRule<>(MockActivity.class);

    @Before
    public void setUp() {
        rule.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                rule.getActivity().getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
        onView(withId(R.id.view)).perform(scrollTo());
    }

    @Test
    public void lastNon0TranslationYGreaterThan0WhenSwipeUp() {
        onView(withId(R.id.scrollView)).perform(swipeUp());
        onView(withId(R.id.view)).check(matches(withLastNon0TranslationY(greaterThan(0f))));
    }

    @Test
    public void lastNon0TranslationYLessThan0WhenSwipeDown() {
        onView(withId(R.id.scrollView)).perform(swipeDown());
        onView(withId(R.id.view)).check(matches(withLastNon0TranslationY(lessThan(0f))));
    }
}
