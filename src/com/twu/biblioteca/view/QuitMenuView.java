package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class QuitMenuView extends View {
    public QuitMenuView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        printMessage("Bye. Have an nice day!");
    }
}
