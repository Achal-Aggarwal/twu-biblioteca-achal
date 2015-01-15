package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ProfileControllerTest {
    LibraryManager libraryManager = new LibraryManager(new BookLibrary(), new MovieLibrary());
    ProfileController profileController;
    User achal = new User("000-0000", "achal", "", "", "");
    ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        String input = "\n";
        libraryManager.registerUser(achal);
        profileController =
                new ProfileController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
    }

    @Test
    public void shouldDisplayCurrentUserProfile(){
        libraryManager.setCurrentUser(achal.getLibraryNumber());
        profileController.execute();

        String title = "Profile.\n";
        String profile = "Library Number \t: \t" + achal.getLibraryNumber() + "\n";
        profile += "Name \t: \t" + achal.getName() + "\n";
        profile += "Email Id \t: \t" + achal.getEmailid() + "\n";
        profile += "Phone Number \t: \t" + achal.getPhoneNumber() + "\n";
        assertEquals(title + profile, output.toString());
    }

    @Test
    public void shouldHideItselfIfNoUserIsLoggedIn(){
        assertTrue(profileController.isHidden());
    }

    @Test
    public void shouldShowItselfIfUserIsLoggedInAlready(){
        libraryManager.setCurrentUser(achal.getLibraryNumber());

        assertFalse(profileController.isHidden());
    }
}
