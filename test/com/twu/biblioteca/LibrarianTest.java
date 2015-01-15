package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LibrarianTest {
    @Test
    public void testValidateLibrarianCreation(){
        String libraryNumber = "000-0000";
        User user = new User(libraryNumber, "achal", "", "", "", true);
        assertTrue(user instanceof User);
    }
}
