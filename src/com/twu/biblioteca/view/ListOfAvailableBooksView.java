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
        String string = book.getTitle() + "|\t|";
        string += book.getAuthor() + "|\t|";
        string += book.getPublicationDate() + "|";

        return string;
    }

    @Override
    public void render() {
        io.printLine(viewTitle);
        for (Item item : listOfItems) {
            io.printLine(formatBookInfo((Book) item));
        }
    }

    public void setItems(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
