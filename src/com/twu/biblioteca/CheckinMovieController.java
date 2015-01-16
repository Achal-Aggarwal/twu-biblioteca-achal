package com.twu.biblioteca;

public class CheckinMovieController extends Controller {
    private CheckinMovieView view;
    private LoginController loginController;
    private Library library;

    public CheckinMovieController(InputOutputManger inputOutputManger, Library libraryManger) {
        view = new CheckinMovieView(inputOutputManger);
        this.library = libraryManger;
        loginController = new LoginController(inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckinMovieView.Status.LOGIN_REQUIRED);
        } else if(library.checkinMovie(view.getMovieName())){
            view.setStatus(CheckinMovieView.Status.CHECKIN_SUCCESSFUL);
        } else {
            view.setStatus(CheckinMovieView.Status.CHECKIN_UNSUCCESSFUL);
        }

        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkin movie.";
    }
}
