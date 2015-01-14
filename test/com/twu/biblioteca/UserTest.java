package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void testValidateUserCreation(){
        String libraryNumber = "000-0000";
        User user = new User(libraryNumber, "achal");
        assertEquals(libraryNumber, user.getLibraryNumber());
    }
}
