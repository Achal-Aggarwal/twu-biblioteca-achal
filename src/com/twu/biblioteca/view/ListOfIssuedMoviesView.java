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

    private String formatMovieInfo(Issue issue){
        Movie movie = (Movie) issue.getIssuedItem();
        User issuer = issue.getIssuer();

        String strings[] = new String[]{
                movie.getTitle(),
                movie.getYear(),
                movie.getDirector(),
                movie.getRating(),
                issuer.getLibraryNumber()
        };
        return io.formatLine(strings, 25, "|");
    }

    @Override
    public void render() {
        io.printLine(io.buildLine('-', 131));
        io.printLine(io.formatLine(viewTitle, 130));
        io.printLine(io.buildLine('-', 131));
        String headers[] = new String[]{"Title", "Year", "Director", "Rating", "Issuer"};
        io.printLine(io.formatLine(headers, 25, "|"));
        String underline = io.buildLine('-',25);
        io.printLine(io.formatLine(new String[]{underline, underline, underline, underline, underline}, 25, "|"));

        if(listOfIssues.size() == 0){
            io.printLine(io.formatLine("Empty", 131));
        }

        for (Issue issue : listOfIssues) {
            io.printLine(formatMovieInfo(issue));
        }
        io.printLine(io.buildLine('-', 131));
    }

    public void setItems(List<Issue> listOfIssues) {
        this.listOfIssues = listOfIssues;
    }
}
