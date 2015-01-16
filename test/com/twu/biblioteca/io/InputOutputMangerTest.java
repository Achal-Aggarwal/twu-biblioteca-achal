package com.twu.biblioteca.io;

import com.twu.biblioteca.io.InputOutputManger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class InputOutputMangerTest {
    @Test
    public void testPrintLine(){
        String input = "";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputOutputManger io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
            );
        io.printLine("Hello World");
        assertEquals("Hello World\n", output.toString());
    }

    @Test
    public void testReadLine(){
        String input = "Hello\nWorld";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputOutputManger io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );

        assertEquals("Hello", io.readLine());
    }
}
