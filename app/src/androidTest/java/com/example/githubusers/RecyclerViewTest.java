package com.example.githubusers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.githubusers.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    private static final String TAG = "MAIN_ACTIVITY_TEST";
    private String appName = getResourceString(R.string.app_name);

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Log.e(TAG, "Test Setup Error: " + e.getMessage());
        }
    }

    @Test
    public void testItemClickByPosition() {
        onView(withId(R.id.rvUsers))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(4, click()));
    }

    // Helpers
    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            Log.e(TAG, "Sleep Thread Error: " + e.getMessage());
        }
    }


}
