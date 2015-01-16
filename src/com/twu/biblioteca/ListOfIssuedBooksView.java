package com.twu.biblioteca;

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

        String string = book.getTitle() + "|\t|";
        string += book.getAuthor() + "|\t|";
        string += book.getPublicationDate() + "|\t|";
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

    public void setItems(List<Issue> listOfItems) {
        this.listOfIssues = listOfItems;
    }
}
