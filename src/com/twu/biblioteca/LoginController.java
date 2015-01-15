package com.twu.biblioteca;

public class LoginController extends Controller {
    LoginView view;
    Controller controller;

    public LoginController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
        view = new LoginView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(libraryManager.getCurrentUser() != null){
            return true;
        }

        String libraryNumber = view.getLibraryNumber();
        String password = view.getUserPassword();
        boolean isUserValid = libraryManager.isUserValid(libraryNumber, password);
        if (isUserValid) {
            view.setStatus(LoginView.Status.LOGIN_SUCCESSFUL);
            libraryManager.setCurrentUser(libraryNumber);
            view.render();
            if (controller != null && isUserValid) {
                return controller.execute();
            }
        } else {
            view.setStatus(LoginView.Status.LOGIN_UNSUCCESSFUL);
            view.render();
            return false;
        }

        return true;
    }

    @Override
    public String getTitle() {
        return "Login.";
    }

    public void setAction(Controller controller){
        this.controller = controller;
    }

    @Override
    public boolean isHidden() {
        return libraryManager.getCurrentUser() != null;
    }
}
