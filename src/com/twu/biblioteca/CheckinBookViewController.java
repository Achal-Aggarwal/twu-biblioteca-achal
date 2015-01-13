package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

public class CheckinBookViewController extends ViewController {
    public CheckinBookViewController(Library library, PrintStream output, Scanner input) {
        super(library, output, input);
    }

    @Override
    public boolean execute() {
        if(library.checkin(input.nextLine())){
            output.println("Thank you for returning the book.");
        } else {
            output.println("That is not a valid book to return.");
        }

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkin book.";
    }
}
