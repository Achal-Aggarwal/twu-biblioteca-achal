package com.twu.biblioteca;

public class LoginController extends Controller {
    LoginView view;
    Controller nextActionController;

    public LoginController(InputOutputManger inputOutputManger) {
        view = new LoginView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(SessionManager.getSession().getLoggedInUser() != null){
            return true;
        }

        String libraryNumber = view.getLibraryNumber();
        String password = view.getUserPassword();
        boolean isUserValid = SessionManager.getSession().validateUser(libraryNumber, password);
        if (isUserValid) {
            view.setStatus(LoginView.Status.LOGIN_SUCCESSFUL);
            SessionManager.getSession().login(libraryNumber);
            view.render();
            if (nextActionController != null && isUserValid) {
                return nextActionController.execute();
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
        this.nextActionController = controller;
    }

    @Override
    public boolean isHidden() {
        return SessionManager.getSession().getLoggedInUser() != null;
    }
}
