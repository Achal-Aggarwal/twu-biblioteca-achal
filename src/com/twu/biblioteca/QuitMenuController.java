package com.twu.biblioteca;

public class QuitMenuController extends Controller {
    private QuitMenuView view;
    public QuitMenuController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
        view = new QuitMenuView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        view.render();
        return false;
    }

    @Override
    public String getTitle() {
        return "Quit.";
    }
}
