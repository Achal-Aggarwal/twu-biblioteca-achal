package com.twu.biblioteca.controller;

public abstract class Controller {
    abstract public boolean execute();

    abstract public String getTitle();

    public boolean isHidden() {
        return false;
    }
}
