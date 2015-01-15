package com.twu.biblioteca;

public class CheckinBookController extends Controller {
    private CheckinBookView view;
    private LoginController loginController;
    private LibraryManager libraryManager;

    public CheckinBookController(InputOutputManger inputOutputManger, LibraryManager libraryManger) {
        view = new CheckinBookView(inputOutputManger);
        this.libraryManager = libraryManger;
        loginController = new LoginController(inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckinBookView.Status.LOGIN_REQUIRED);
        } else if(libraryManager.checkinBook(view.getBookName())){
            view.setStatus(CheckinBookView.Status.CHECKIN_SUCCESSFUL);
        } else {
            view.setStatus(CheckinBookView.Status.CHECKIN_UNSUCCESSFUL);
        }

        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkin book.";
    }
}
