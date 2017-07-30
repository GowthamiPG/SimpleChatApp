package com.trautmann.simplechatapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.view.ChatDetailActivity;

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

public class ChatDetailActivityTest {

    @Rule
    public ActivityTestRule<ChatDetailActivity> activityTestRule =
            new ActivityTestRule<ChatDetailActivity>(ChatDetailActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context context = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent i = new Intent(context, ChatDetailActivity.class);
                    i.putExtra(Constants.IntentArguments.CHAT_NAME, "Cool chat");
                    i.putExtra(Constants.IntentArguments.CHAT_ID, 1);
                    return i;
                }
            };

    @Test
    public void initialState_test() {

        onView(withId(R.id.chatDetailInputEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.chatDetailSendImageButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.chatDetailAppBarLayout))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.editChatButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void editChatName_test() {
        onView(withId(R.id.editChatButton))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.editChatButton))
                .perform(click());

        onView(withText(R.string.rename_chat_dialog_title))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.chatNameEditText))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.chatNameEditText))
                .check(matches(withHint(R.string.new_chat_name_hint_text)));

        onView(withText(R.string.rename_chat_dialog_rename_button_text))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withText(R.string.new_chat_dialog_cancel_button_text))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}
