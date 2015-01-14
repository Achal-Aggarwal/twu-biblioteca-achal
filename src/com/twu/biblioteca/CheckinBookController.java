package com.twu.biblioteca;

public class CheckinBookController extends Controller {
    public CheckinBookController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(library.checkin(io.readLine())){
            io.printLine("Thank you for returning the book.");
        } else {
            io.printLine("That is not a valid book to return.");
        }

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkin book.";
    }
}
