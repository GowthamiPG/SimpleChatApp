package com.trautmann.simplechatapp;

import android.content.Context;

import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.model.User;
import com.trautmann.simplechatapp.viewmodel.ChatListItemViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Brandon Trautmann
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatListItemViewModelTests {

    ChatListItemViewModel chatListItemViewModel;

    private static final String FAKE_STRING = "fakeStringFromStringsXml";

    @Mock
    Context mockContext;

    @Before
    public void initChatListItemViewModel() {
        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "This is the last message",
                "2019-10-04T22:44:30.652Z", new User(1, "Brandon", "Brandon.e.trautmann@gmail.com"));
        Chat chat = new Chat(1, "The Chat", null, lastMessage);
        when(mockContext.getString(R.string.chat_last_message,
                chat.getLastChatMessage().getSender().getName(),
                chat.getLastChatMessage().getMessage()))
                .thenReturn(FAKE_STRING);
        chatListItemViewModel = new ChatListItemViewModel(mockContext, chat);
    }

    @Test
    public void getChatName_test() {
        assertEquals("The Chat", chatListItemViewModel.getChatName());
    }

    @Test
    public void getLastChatMessage_test() {
        assertEquals(FAKE_STRING, chatListItemViewModel.getLastChatMessage());
    }

}
