package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Item;
import com.twu.biblioteca.session.User;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Issue;

import java.util.Arrays;
import java.util.List;

public class ListOfIssuedBooksView extends View {
    private List<Issue> listOfIssues;
    private String viewTitle;

    public ListOfIssuedBooksView(InputOutputManger inputOutputManger, String viewTitle) {
        super(inputOutputManger);
        listOfIssues =  Arrays.asList();
        this.viewTitle = viewTitle;
    }

    private String formatBookInfo(Issue issue){
        Book book = (Book) issue.getIssuedItem();
        User issuer = issue.getIssuer();

        String strings[] = new String[]{
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationDate(),
                issuer.getLibraryNumber()
        };
        return io.formatLine(strings, 25, "|");
    }

    @Override
    public void render() {
        io.printLine(io.buildLine('-', 104));
        io.printLine(io.formatLine(viewTitle, 103));
        io.printLine(io.buildLine('-', 104));
        String headers[] = new String[]{"Title", "Author", "Publication Date", "Issuer"};
        io.printLine(io.formatLine(headers, 25, "|"));
        String underline = io.buildLine('-',25);
        io.printLine(io.formatLine(new String[]{underline, underline, underline, underline}, 25, "|"));

        if(listOfIssues.size() == 0){
            io.printLine(io.formatLine("Empty", 104));
        }

        for (Issue issue : listOfIssues) {
            io.printLine(formatBookInfo(issue));
        }
        io.printLine(io.buildLine('-', 104));
    }

    public void setItems(List<Issue> listOfItems) {
        this.listOfIssues = listOfItems;
    }
}
