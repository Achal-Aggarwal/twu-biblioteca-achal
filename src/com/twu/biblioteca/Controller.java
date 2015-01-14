package com.twu.biblioteca;

public abstract class Controller {
    protected Library library;
    protected InputOutputManger io;

    public Controller(Library library, InputOutputManger inputOutputManger) {
        this.library = library;
        this.io = inputOutputManger;
    }

    abstract public boolean execute();

    abstract public String getTitle();
}
