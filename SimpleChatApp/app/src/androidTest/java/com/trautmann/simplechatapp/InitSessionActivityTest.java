package com.trautmann.simplechatapp;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.trautmann.simplechatapp.view.InitSessionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

@RunWith(AndroidJUnit4.class)
public class InitSessionActivityTest {

    @Rule
    public ActivityTestRule<InitSessionActivity> activityTestRule =
            new ActivityTestRule<>(InitSessionActivity.class);


    @Test
    public void initialState_test() {

        onView(withId(R.id.emailEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.emailEditText))
                .check(matches(withHint(R.string.email_hint_text)));

        onView(withId(R.id.passwordEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.passwordEditText))
                .check(matches(withHint(R.string.password_hint_text)));

        onView(withId(R.id.loginButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.loginButton))
                .check(matches(withText(R.string.login_button_text)));

        onView(withId(R.id.account_status_prompt_textview))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.account_status_prompt_textview))
                .check(matches(withText(R.string.to_register_button_text)));

    }

    @Test
    public void switchToRegister_test() {
        onView(withId(R.id.account_status_prompt_textview))
                .perform(click());

        onView(withId(R.id.nameEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.nameEditText))
                .check(matches(withHint(R.string.name_hint_text)));

        onView(withId(R.id.emailEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.emailEditText))
                .check(matches(withHint(R.string.email_hint_text)));

        onView(withId(R.id.passwordEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.passwordEditText))
                .check(matches(withHint(R.string.password_hint_text)));

        onView(withId(R.id.confirmPasswordEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.confirmPasswordEditText))
                .check(matches(withHint(R.string.confirm_password_hint_text)));

        onView(withId(R.id.loginButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.loginButton))
                .check(matches(withText(R.string.register_button_text)));

        onView(withId(R.id.account_status_prompt_textview))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.account_status_prompt_textview))
                .check(matches(withText(R.string.back_to_login_text)));

    }


}
