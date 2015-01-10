package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp app = new BibliotecaApp();
        assertEquals("Welcome and thank you for taking time to visit Biblioteca.", app.welcomeMessage());
    }
}
