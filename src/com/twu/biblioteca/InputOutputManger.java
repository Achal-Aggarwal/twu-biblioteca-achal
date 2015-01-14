package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutputManger {
    private PrintStream outputStream;
    private Scanner inputStream;
    public InputOutputManger(InputStream byteArrayInputStream, PrintStream outputStream) {
        this.outputStream = outputStream;
        inputStream = new Scanner(byteArrayInputStream);
    }

    public void printLine(String line) {
        outputStream.println(line);
    }

    public String readLine() {
        return inputStream.nextLine();
    }
}
