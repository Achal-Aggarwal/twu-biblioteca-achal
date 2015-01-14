package com.twu.biblioteca;

public class QuitMenuViewController extends ViewController {
    public QuitMenuViewController(Library library, InputOutputManger inputOutputManger) {
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
