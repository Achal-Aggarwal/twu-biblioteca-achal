package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/14/15.
 */
abstract public class View {
    protected InputOutputManger io;

    public View(InputOutputManger inputOutputManger) {
        this.io = inputOutputManger;
    }

    abstract public void render();
}
