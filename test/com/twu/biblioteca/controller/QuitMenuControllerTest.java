package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.QuitMenuController;
import com.twu.biblioteca.io.InputOutputManger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;

public class QuitMenuControllerTest {
    @Test
    public void shouldDisplayQuitMessage(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        QuitMenuController quitVC =
                new QuitMenuController(
                        new InputOutputManger(
                            new ByteArrayInputStream(input.getBytes()),
                            new PrintStream(output)
                        )
                );

        assertFalse(quitVC.execute());
    }
}