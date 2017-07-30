package com.trautmann.simplechatapp;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.trautmann.simplechatapp.view.MainActivity;
import com.trautmann.simplechatapp.view.ProfileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Brandon Trautmann
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);


    @Test
    public void testsProfileButtonClick() {
        onView(withId(R.id.profileButton))
                .perform(click());

        intended(hasComponent(ProfileActivity.class.getName()));
    }
}
