package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.LoginController;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.session.SessionManager;
import com.twu.biblioteca.session.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LoginControllerTest {
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
    public void shouldLetValidUserToLogin(){
        String input = achal.getLibraryNumber() + "\n" + achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        SessionManager.getSession().registerUser(achal);

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertTrue(loginVC.execute());
    }

    @Test
    public void shouldNotLetValidUserToLoginIfPasswordIsWrong(){
        String input = achal.getLibraryNumber() + "\nasd\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertFalse(loginVC.execute());

        assertEquals(output.toString(), "Wrong library number or password.\n");
    }

    @Test
    public void shouldNotLetInvalidUserToLogin(){
        String input = achal.getLibraryNumber() + "\nasd\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertFalse(loginVC.execute());

        assertTrue(output.toString().contains("Wrong library number or password.\n"));
    }

    @Test
    public void shouldPassValidUserInformationToLibraryManager(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertTrue(loginVC.execute());

        assertSame(achal, session.getLoggedInUser());
    }

    @Test
    public void shouldShowItselfIfNoUserIsLoggedIn(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        assertFalse(loginVC.isHidden());
    }

    @Test
    public void shouldHideItselfIfUserIsLoggedInAlready(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.registerUser(achal);

        LoginController loginVC =
                new LoginController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        session.login(achal.getLibraryNumber());

        assertTrue(loginVC.isHidden());
    }
}
