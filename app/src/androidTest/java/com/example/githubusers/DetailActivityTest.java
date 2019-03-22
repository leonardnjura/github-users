package com.example.githubusers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.githubusers.view.DetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;



@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private static final String TAG = "DETAIL_ACTIVITY_TEST";
    private String userNameLabel = getResourceString(R.string.label_username);

    @Rule
    public ActivityTestRule<DetailActivity> rule = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Before
    public void launchIntentActivity() {
        Intent intent = new Intent();
        intent.putExtra("EXTRA_USERNAME", "nellyk");
        rule.launchActivity(intent);
        sleep(5000);
    }

    @Test
    public void testInternetConnection(){
        assertTrue(isConnected(rule.getActivity()));
    }

    @Test
    public void testSuccessfulLaunchIntentActivity() {
        sleep(3000);
        onView(withId(R.id.detail_view)).check(matches(isDisplayed()));
        onView(withId(R.id.username_details)).check(matches(isDisplayed()));
        onView(withId(R.id.tvUserNameLabel)).check(matches(withText(userNameLabel)));
        onView(withId(R.id.tvUserName)).check(matches(withText("@nellyk")));
    }

    @Test
    public void testActionBarShareIcon() {
            onView(withId(R.id.action_share)).perform(click());
        sleep(3000);
    }

    @Test
    public void testCollapsingToolbar() {
        onView(withId(R.id.appBarLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.ivUserAvatar)).check(matches(isDisplayed()))
                .perform(ViewActions.swipeUp());
        onView(withId(R.id.tbCustom)).check(matches(isDisplayed()));
        sleep(3000);
    }

    @Test
    public void testSwipeToRefresh() {
        onView(withId(R.id.detail_view))
                .perform(ViewActions.swipeDown());
    }

    @Test
    public void testConfigurationChangeSaveAndRestore() {
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        sleep(3000);
        rule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    }

    // Helpers
    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            Log.e(TAG, "Sleep Thread Error: " + e.getMessage());
        }
    }

}
