package com.example.githubusers;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.githubusers.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

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
    public void testLaunchMainActivity() {
        onView(withId(R.id.main_view))
            .check(matches(isDisplayed()));
        sleep(5000);
    }

    @Test
    public void testAppTitle() {
        onView(withId(R.id.tbCustom))
            .check(matches(isDisplayed()));
        onView(ViewMatchers.withText(appName))
            .check(matches(ViewMatchers.withParent(withId(R.id.tbCustom))));
        sleep(3000);
    }

    @Test
    public void testActionBar() {
        try {
            onView(withId(R.id.action_refresh)).perform(click());
        } catch (NoMatchingViewException e) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
            onView(ViewMatchers.withText(R.string.refresh)).perform(click());
        }
        sleep(3000);
    }

    @Test
    public void testMainActivityLayouts(){
        onView(withId(R.id.rvUsers));
        onView(withId(R.id.swipe_refresh))
            .check(matches(isDisplayed()));
        sleep(3000);
    }

    @Test
    public void testConfigurationChangeSaveAndRestore() {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sleep(3000);
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        sleep(3000);
    }

    @Test
    public void testSwipeToRefresh() {
        onView(withId(R.id.main_view))
        .perform(ViewActions.swipeDown());
        sleep(3000);
    }

    @Test
    public void testClickToRefresh() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Refresh"))
            .perform(click());
        sleep(3000);
    }

    @Test
    public void testLaunchDetailActivityOnItemClick() {
        onView(withId(R.id.rvUsers)).perform(RecyclerViewActions.scrollToPosition(20));
        onView(withId(R.id.rvUsers)).perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
        sleep(3000);
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
