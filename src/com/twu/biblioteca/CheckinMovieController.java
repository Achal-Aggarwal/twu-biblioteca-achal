package com.twu.biblioteca;

public class CheckinMovieController extends Controller {
    private CheckinMovieView view;
    public CheckinMovieController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
        view = new CheckinMovieView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(libraryManager.checkinMovie(view.getMovieName())){
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
