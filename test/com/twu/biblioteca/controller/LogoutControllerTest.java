package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.LogoutController;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.session.SessionManager;
import com.twu.biblioteca.session.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertTrue;

public class LogoutControllerTest {
    User achal = new User("000-0000", "achal", "", "", "");
    SessionManager session;

    @Before
    public void setUp() {
        session = SessionManager.getSession();
    }

    @After
    public void tearDown() {
        SessionManager.clearSession();
    }

    @Test
    public void shouldLetUserToLogout(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);
        session.login(achal.getLibraryNumber());
        LogoutController logoutVC =
                new LogoutController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        logoutVC.execute();
        assertNull(session.getLoggedInUser());
    }

    @Test
    public void shouldHideItselfIfNoUserIsLoggedIn(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LogoutController logoutVC =
                new LogoutController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertTrue(logoutVC.isHidden());
    }

    @Test
    public void shouldShowItselfIfUserIsLoggedInAlready(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LogoutController logoutVC =
                new LogoutController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        session.login(achal.getLibraryNumber());

        assertFalse(logoutVC.isHidden());
    }
}