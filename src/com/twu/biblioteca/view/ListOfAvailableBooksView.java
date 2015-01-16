package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Item;

import java.util.Arrays;
import java.util.List;

public class ListOfAvailableBooksView extends View {
    private List<Item> listOfItems;
    private String viewTitle;

    public ListOfAvailableBooksView(InputOutputManger inputOutputManger, String viewTitle) {
        super(inputOutputManger);
        listOfItems =  Arrays.asList();
        this.viewTitle = viewTitle;
    }

    private String formatBookInfo(Book book){
        String strings[] = new String[]{book.getTitle(), book.getAuthor(), book.getPublicationDate()};
        return io.formatLine(strings, 25, "|");
    }

    @Override
    public void render() {
        io.printLine(io.buildLine('-', 79));
        io.printLine(io.formatLine(viewTitle, 78));
        io.printLine(io.buildLine('-', 79));
        String headers[] = new String[]{"Title", "Author", "Publication Date"};
        io.printLine(io.formatLine(headers, 25, "|"));
        String underline = io.buildLine('-',25);
        io.printLine(io.formatLine(new String[]{underline, underline, underline}, 25, "|"));

        if(listOfItems.size() == 0){
            io.printLine(io.formatLine("Empty", 104));
        }

        for (Item item : listOfItems) {
            io.printLine(formatBookInfo((Book) item));
        }
        io.printLine(io.buildLine('-', 79));
    }

    public void setItems(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
