package com.trautmann.simplechatapp;

import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.model.User;
import com.trautmann.simplechatapp.viewmodel.ChatMessageListItemViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brandon Trautmann
 */

public class ChatMessageListItemViewModelTests {

    private ChatMessageListItemViewModel chatMessageListItemViewModel;

    @Before
    public void initChatMessageListItemViewModel() {
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "A great chat message",
                "2019-10-04T22:44:30.652Z", new User(1, "Brandon", "Some.email@gmail.com"));
        chatMessageListItemViewModel = new ChatMessageListItemViewModel(chatMessage);
    }

    @Test
    public void testsGetSenderName() {
        assertEquals("Brandon", chatMessageListItemViewModel.getSenderName());
    }

    @Test
    public void testsGetMessageBody() {
        assertEquals("A great chat message", chatMessageListItemViewModel.getMessageBody());
    }
}
