package com.trautmann.simplechatapp;

import android.content.Context;
import android.text.TextUtils;

import com.trautmann.simplechatapp.viewmodel.InitSessionViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

/**
 * Created by Brandon Trautmann
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class InitSessionActivityViewModelTests {

    @Mock
    Context mockContext;

    private InitSessionViewModel initSessionViewModel;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
        initSessionViewModel = new InitSessionViewModel(mockContext);
    }

    @Test
    public void testsValidLoginInputs() {
        String email = "email.address@gmail.com";
        String password = "secret";
        assertTrue(initSessionViewModel.areValidLoginInputs(email, password));
    }

    @Test
    public void testsInvalidLoginInputsDueToEmptyEmail() {
        String email = "";
        String password = "secret";
        assertFalse(initSessionViewModel.areValidLoginInputs(email, password));
    }

    @Test
    public void testsInvalidLoginInputsDueToEmptyPassword() {
        String email = "email.address@gmail.com";
        String password = "";
        assertFalse(initSessionViewModel.areValidLoginInputs(email, password));
    }

    @Test
    public void testsInvalidLoginInputsDueToEmptyBothFields() {
        String email = "";
        String password = "";
        assertFalse(initSessionViewModel.areValidLoginInputs(email, password));
    }

    @Test
    public void testsValidRegisterInputs() {
        String name = "Brandon";
        String email = "email.address@gmail.com";
        String password = "secret";
        String confirmPassword = "secret";
        assertTrue(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToEmptyName() {
        String name = "";
        String email = "email.address@gmail.com";
        String password = "secret";
        String confirmPassword = "secret";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToEmptyEmail() {
        String name = "Brandon";
        String email = "";
        String password = "secret";
        String confirmPassword = "secret";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToEmptyPassword() {
        String name = "Brandon";
        String email = "email.address@gmail.com";
        String password = "";
        String confirmPassword = "secret";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToEmptyConfirmPassword() {
        String name = "Brandon";
        String email = "email.address@gmail.com";
        String password = "secret";
        String confirmPassword = "";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToAllEmptyFields() {
        String name = "";
        String email = "";
        String password = "";
        String confirmPassword = "";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }

    @Test
    public void testsInvalidRegisterInputsDueToMismatchingPasswords() {
        String name = "Brandon";
        String email = "email.address@gmail.com";
        String password = "secret";
        String confirmPassword = "secret1";
        assertFalse(initSessionViewModel.areValidRegisterInputs(name, email, password, confirmPassword));
    }


}
