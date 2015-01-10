package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by achalaggarwal on 1/10/15.
 */
public class BookTest {
    @Test
    public void testStringRepresentationOfBook(){
        String title = "Let Us C";
        Book letusc = new Book(title);
        assertEquals(title, letusc.toString());
    }
}
