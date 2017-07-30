package com.trautmann.simplechatapp;

import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.model.User;
import com.trautmann.simplechatapp.viewmodel.ChatDetailActivityViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivityViewModelTests {

    ChatDetailActivityViewModel chatDetailActivityViewModel;


    @Before
    public void initChatDetailViewModel() {
        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "This is the last message",
                "2019-10-04T22:44:30.652Z", new User(1, "Brandon", "Brandon.e.trautmann@gmail.com"));
        Chat chat = new Chat(1, "The Chat", null, lastMessage);
        chatDetailActivityViewModel = new ChatDetailActivityViewModel(chat);
    }

    @Test
    public void testsGetTitle() {
        assertEquals("The Chat", chatDetailActivityViewModel.getTitle());
    }

    @Test
    public void testsGetChatId() {
        assertEquals(1, chatDetailActivityViewModel.getChatId());
    }

}
