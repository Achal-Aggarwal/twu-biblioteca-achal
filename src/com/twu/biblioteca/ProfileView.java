package com.twu.biblioteca;

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
        if(user != null){
            io.printLine("Profile.");
            io.printLine("Library Number \t: \t" + user.getLibraryNumber());
            io.printLine("Name \t: \t" + user.getName());
            io.printLine("Email Id \t: \t" + user.getEmailid());
            io.printLine("Phone Number \t: \t" + user.getPhoneNumber());
        }
    }
}
