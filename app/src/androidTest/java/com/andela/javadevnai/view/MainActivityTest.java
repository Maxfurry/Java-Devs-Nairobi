package com.andela.javadevnai.view;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.andela.javadevnai.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String knownJavaDeveloperUsername = "@TheDancerCodes";

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void customIntentToStartActivity() {
        Intent intent = new Intent();
        mMainActivityTestRule.launchActivity(intent);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void display_recycler_view() {
        onView(withId(R.id.my_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void display_detail_view() {
        onView(withId(R.id.my_recycler_view))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(knownJavaDeveloperUsername)), click()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.dev_detail)).check(matches(isDisplayed()));
    }
}
