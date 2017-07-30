package com.trautmann.simplechatapp;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.trautmann.simplechatapp.view.ProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<ProfileActivity> activityTestRule =
            new ActivityTestRule<>(ProfileActivity.class);

    @Test
    public void testsInitialState() {

        onView(withId(R.id.profileNameTextView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.profileEmailTextView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.profileLogOutButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.profileLogOutButton))
                .check(matches(withText(R.string.profile_log_out_button_text)));
    }

    @Test
    public void testsEditProfile() {
        onView(withId(R.id.editProfileButton))
                .perform(click());

        onView(withId(R.id.editNameEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.editNameEditText))
                .check(matches(withHint(R.string.name_hint_text)));

        onView(withId(R.id.editEmailEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.editEmailEditText))
                .check(matches(withHint(R.string.email_hint_text)));

        onView(withId(R.id.profileNameTextView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.profileEmailTextView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }
}
