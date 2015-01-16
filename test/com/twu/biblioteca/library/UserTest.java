package com.twu.biblioteca.library;

import com.twu.biblioteca.session.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    String libraryNumber = "000-0000";
    String password = "achal";
    String name = "Achal";
    String emailid = "achal@gmail.com";
    String phoneNumber = "1234567890";
    User user = new User(libraryNumber, password, name, emailid, phoneNumber);

    @Test
    public void testUserCreation(){
        assertEquals(libraryNumber, user.getLibraryNumber());
        assertEquals(password, user.getPassword());
        assertEquals(name, user.getName());
        assertEquals(emailid, user.getEmailid());
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    public void testValidateLibrarianCreation(){
        String libraryNumber = "000-0000";
        User user = new User(libraryNumber, "achal", "", "", "", true);
        assertTrue(user instanceof User);
    }
}
