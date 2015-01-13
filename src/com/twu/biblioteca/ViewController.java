package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public abstract class ViewController {
    protected Library library;
    protected PrintStream output;
    protected Scanner input;

    public ViewController(Library library, PrintStream output, Scanner input) {
        this.library = library;
        this.output = output;
        this.input = input;
    }

    abstract public boolean execute();

    abstract public String getTitle();
}
