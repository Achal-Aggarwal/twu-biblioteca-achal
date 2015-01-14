package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LoginControllerTest {

    User achal = new User("000-0000", "achal");
    User abhishek = new User("000-0001", "abhishek");
    LibraryManager libraryManager;

    @Before
    public void setUp() {
        libraryManager = new LibraryManager(new BookLibrary(), new MovieLibrary());
    }

    @Test
    public void shouldLetValidUserToLogin(){
        String input = achal.getLibraryNumber() + "\n" + achal.getPassword() +"\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        libraryManager.registerUser(achal);

        LoginController loginVC =
                new LoginController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        loginVC.execute();

        assertEquals(output.toString(), "Welcome and thank you for taking time to visit Biblioteca.\n");
    }

    @Test
    public void shouldNotLetValidUserToLoginIfPasswordIsWrong(){
        String input = achal.getLibraryNumber() + "\nasd\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        libraryManager.registerUser(achal);

        LoginController loginVC =
                new LoginController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        loginVC.execute();

        assertEquals(output.toString(), "Wrong library number or password.\n");
    }

    @Test
    public void shouldNotLetInvalidUserToLogin(){
        String input = achal.getLibraryNumber() + "\nasd\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        LoginController loginVC =
                new LoginController(libraryManager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );

        loginVC.execute();

        assertTrue(output.toString().contains("Wrong library number or password.\n"));
    }
}
