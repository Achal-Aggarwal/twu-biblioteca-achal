package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

abstract public class View {
    protected InputOutputManger io;

    public View(InputOutputManger inputOutputManger) {
        this.io = inputOutputManger;
    }

    abstract public void render();

    protected void printMessage(String message){
        io.printLine(io.buildLine('=', 50));
        io.printLine(io.formatLine(message, 46, "||"));
        io.printLine(io.buildLine('=', 50));
    }
}
