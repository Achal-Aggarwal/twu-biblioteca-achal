package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class QuitMenuViewController extends ViewController {
    public QuitMenuViewController(Library library, PrintStream output, Scanner input) {
        super(library, output, input);
    }

    @Override
    public boolean execute() {
        //output.println("Do not forget to return issues books.");
        return false;
    }

    @Override
    public String getTitle() {
        return "Quit.";
    }
}
