package com.twu.biblioteca;

public class QuitMenuController extends Controller {
    public QuitMenuController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
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
