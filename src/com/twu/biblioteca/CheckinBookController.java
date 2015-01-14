package com.twu.biblioteca;

public class CheckinBookController extends Controller {
    private CheckinBookView view;
    public CheckinBookController(Library library, InputOutputManger inputOutputManger) {
        super(library);
        view = new CheckinBookView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(library.checkin(view.getBookName())){
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
