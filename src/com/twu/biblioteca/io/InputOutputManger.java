package com.twu.biblioteca.io;

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

    public static String buildLine(char character, int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(character);
        }
        return sb.toString();
    }

    public static String formatLine(String string, int columnSize, String separator){
        return formatLine(new String[]{string}, columnSize, separator, true);
    }

    public static String formatLine(String strings[], int columnSize, String separator){
        return formatLine(strings, columnSize, separator, false);
    }

    public static String formatLine(String string, int columnSize){
        return formatLine(new String[]{string}, columnSize, "", true);
    }

    public static String formatLine(String strings[], int columnSize, String separator, Boolean centered){
        StringBuilder sb = new StringBuilder(strings.length * columnSize + separator.length()*2);

        for (String string : strings) {
            int length = string.length();
            length = length <= columnSize ? columnSize - length : 0;
            sb.append(separator);
            if(centered){
                length /= 2;
                sb.append(buildLine(' ', length));

            }
            sb.append(string);
            sb.append(buildLine(' ', length));
        }
        sb.append(separator);

        return sb.toString();
    }

    public void printLine(String line) {
        outputStream.println(line);
    }

    public String readLine() {
        return inputStream.nextLine();
    }

    public void printString(String string) {
        outputStream.print(string);
    }
}
