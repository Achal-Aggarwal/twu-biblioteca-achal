package com.twu.biblioteca;

public abstract class Controller {
    abstract public boolean execute();

    abstract public String getTitle();

    public boolean isHidden() {
        return false;
    }
}
