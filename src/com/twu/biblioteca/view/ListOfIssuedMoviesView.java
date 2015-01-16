package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.session.User;
import com.twu.biblioteca.library.Issue;
import com.twu.biblioteca.library.Movie;

import java.util.Arrays;
import java.util.List;

public class ListOfIssuedMoviesView extends View {
    private List<Issue> listOfIssues;
    private String viewTitle;

    public ListOfIssuedMoviesView(InputOutputManger inputOutputManger, String viewTitle) {
        super(inputOutputManger);
        listOfIssues =  Arrays.asList();
        this.viewTitle = viewTitle;
    }

    private String formatBookInfo(Issue issue){
        Movie movie = (Movie) issue.getIssuedItem();
        User issuer = issue.getIssuer();

        String string = movie.getTitle() + "|\t|";
        string += movie.getYear() + "|\t|";
        string += movie.getDirector() + "|\t|";
        string += movie.getRating() + "|\t|";
        string += "issued by " + issuer.getLibraryNumber() + "|";

        return string;
    }

    @Override
    public void render() {
        io.printLine(viewTitle);
        for (Issue issue : listOfIssues) {
            io.printLine(formatBookInfo(issue));
        }
    }

    public void setItems(List<Issue> listOfIssues) {
        this.listOfIssues = listOfIssues;
    }
}
