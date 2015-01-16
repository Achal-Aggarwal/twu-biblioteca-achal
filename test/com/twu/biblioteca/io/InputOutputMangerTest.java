package com.twu.biblioteca.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class InputOutputMangerTest {
    ByteArrayOutputStream output;
    InputOutputManger io;

    public void runTestCaseWithInput(String input){
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
            );
    }

    @Test
    public void testPrintLine(){
        runTestCaseWithInput("");
        io.printLine("Hello World");
        assertEquals("Hello World\n", output.toString());
    }

    @Test
    public void testPrintString(){
        runTestCaseWithInput("");
        io.printString("Hello World");
        assertEquals("Hello World", output.toString());
    }

    @Test
    public void testBuildLine(){
        runTestCaseWithInput("");
        assertEquals("*****", io.buildLine('*', 5));
    }

    @Test
    public void testFormatLine(){
        runTestCaseWithInput("");
        assertEquals("|  *  |", io.formatLine("*", 5, "|"));
        assertEquals("|*    |-    |", io.formatLine(new String[]{"*", "-"}, 5, "|"));
        assertEquals("|  *  |  -  |", io.formatLine(new String[]{"*", "-"}, 5, "|", true));
    }

    @Test
    public void testReadLine(){
        runTestCaseWithInput("Hello\nWorld");
        assertEquals("Hello", io.readLine());
    }
}
