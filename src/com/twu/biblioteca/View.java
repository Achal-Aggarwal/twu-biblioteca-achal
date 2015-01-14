package com.twu.biblioteca;

abstract public class View {
    protected InputOutputManger io;

    public View(InputOutputManger inputOutputManger) {
        this.io = inputOutputManger;
    }

    abstract public void render();
}
