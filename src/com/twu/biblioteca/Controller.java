package com.twu.biblioteca;

public abstract class Controller {
    protected LibraryManager libraryManager;

    public Controller(LibraryManager libraryManger) {
        this.libraryManager = libraryManger;
    }

    abstract public boolean execute();

    abstract public String getTitle();
}
