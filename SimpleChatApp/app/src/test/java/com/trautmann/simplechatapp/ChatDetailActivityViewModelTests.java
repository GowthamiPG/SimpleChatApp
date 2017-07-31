package com.trautmann.simplechatapp;

import android.text.TextUtils;

import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.model.User;
import com.trautmann.simplechatapp.viewmodel.ChatDetailActivityViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

/**
 * Created by Brandon Trautmann
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class ChatDetailActivityViewModelTests {

    ChatDetailActivityViewModel chatDetailActivityViewModel;


    @Before
    public void initChatDetailViewModel() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });

        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "This is the last message",
                "2019-10-04T22:44:30.652Z", new User(1, "Brandon", "Brandon.e.trautmann@gmail.com"));
        Chat chat = new Chat(1, "The Chat", null, lastMessage);
        chatDetailActivityViewModel = new ChatDetailActivityViewModel();
        chatDetailActivityViewModel.setChat(chat);
    }

    @Test
    public void testsGetTitle() {
        assertEquals("The Chat", chatDetailActivityViewModel.getChat().getName());
    }

    @Test
    public void testsGetChatId() {
        assertEquals(1, chatDetailActivityViewModel.getChatId());
    }

    @Test
    public void testsCanRenameChat() {
        assertTrue(chatDetailActivityViewModel.canRenameChat("The new chat name"));
    }

    @Test
    public void testsCantRenameChatDueToNoInput() {
        assertFalse(chatDetailActivityViewModel.canRenameChat(""));
    }

    @Test
    public void testsCanSendMessage(){
        assertTrue(chatDetailActivityViewModel.canSendMessage("A new message"));
    }

    @Test
    public void testsCantSendMessageDueToNoInput() {
        assertFalse(chatDetailActivityViewModel.canSendMessage(""));
    }

}
