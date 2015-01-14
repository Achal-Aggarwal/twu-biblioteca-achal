package com.twu.biblioteca;

public abstract class ViewController {
    protected Library library;
    protected InputOutputManger io;

    public ViewController(Library library, InputOutputManger inputOutputManger) {
        this.library = library;
        this.io = inputOutputManger;
    }

    abstract public boolean execute();

    abstract public String getTitle();
}
