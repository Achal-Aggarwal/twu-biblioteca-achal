package com.twu.biblioteca;

public abstract class Controller {
    protected Library library;

    public Controller(Library library) {
        this.library = library;
    }

    abstract public boolean execute();

    abstract public String getTitle();
}
