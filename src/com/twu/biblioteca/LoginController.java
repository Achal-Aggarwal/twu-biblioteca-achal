package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/14/15.
 */
public class LoginController extends Controller {
    LoginView view;
    Controller controller;

    public LoginController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
        view = new LoginView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        String libraryNumber = view.getLibraryNumber();
        String password = view.getUserPassword();
        if(libraryManager.isUserValid(libraryNumber, password)){
            view.setStatus(LoginView.Status.LOGIN_SUCCESSFUL);
        } else {
            view.setStatus(LoginView.Status.LOGIN_UNSUCCESSFUL);
        }

        view.render();

        if (controller != null){
            return controller.execute();
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
}
