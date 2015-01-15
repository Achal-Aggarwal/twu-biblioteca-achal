package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class LogoutControllerTest {
    User achal = new User("000-0000", "achal", "", "", "");
    LibraryManager libraryManager;
    SessionManager session;

    @Before
    public void setUp() {
        libraryManager = new LibraryManager(new BookLibrary(), new MovieLibrary());
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
                new LogoutController(libraryManager,
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
                new LogoutController(libraryManager,
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
                new LogoutController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        session.login(achal.getLibraryNumber());

        assertFalse(logoutVC.isHidden());
    }
}
