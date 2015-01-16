package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.session.User;

public class ProfileView extends View {
    private User user;

    public ProfileView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void render() {
        io.printLine(io.buildLine('-', 65));
        io.printLine(io.formatLine("Profile.", 65));
        io.printLine(io.buildLine('-', 65));
        io.printLine(io.formatLine(new String[]{"Library Number", user.getLibraryNumber()}, 20, ""));
        io.printLine("");
        io.printLine(io.formatLine(new String[]{"Name", user.getName()}, 20, ""));
        io.printLine("");
        io.printLine(io.formatLine(new String[]{"Email Id", user.getEmailid()}, 20, ""));
        io.printLine("");
        io.printLine(io.formatLine(new String[]{"Phone Number", user.getPhoneNumber()}, 20, ""));
        io.printLine("");
        io.printLine(io.buildLine('-', 65));
    }
}
