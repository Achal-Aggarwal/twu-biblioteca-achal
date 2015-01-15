package com.twu.biblioteca;

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

    @Before
    public void setUp() {
        libraryManager = new LibraryManager(new BookLibrary(), new MovieLibrary());
    }

    @Test
    public void shouldLetUserToLogout(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        libraryManager.registerUser(achal);
        libraryManager.setCurrentUser(achal.getLibraryNumber());
        LogoutController logoutVC =
                new LogoutController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        logoutVC.execute();
        assertNull(libraryManager.getCurrentUser());
    }

    @Test
    public void shouldHideItselfIfNoUserIsLoggedIn(){
        String input = achal.getLibraryNumber() + "\n"+ achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        libraryManager.registerUser(achal);

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
        libraryManager.registerUser(achal);

        LogoutController logoutVC =
                new LogoutController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        libraryManager.setCurrentUser(achal.getLibraryNumber());

        assertFalse(logoutVC.isHidden());
    }
}
