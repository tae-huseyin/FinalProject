package com.theappexperts.finalproject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by TheAppExperts on 13/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void checkActivityHasRecyclerView(){
        onView(withId(R.id.frag_container)).check(matches(isDisplayed()));
        onView(withId(R.id.rvListOfRecipes)).perform(scrollToPosition(10))
                .check(matches(isDisplayed()));
        onView(withId(R.id.rvListOfRecipes)).perform(scrollToPosition(4));
    }


}
