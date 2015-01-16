package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class LogoutView extends View {
    public LogoutView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        printMessage("Logout successful");
    }
}
