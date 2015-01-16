package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class ListOfAvailableBooksView extends View {
    private List<String> listOfItems;
    private String viewTitle;

    public ListOfAvailableBooksView(InputOutputManger inputOutputManger, String viewTitle) {
        super(inputOutputManger);
        listOfItems =  Arrays.asList();
        this.viewTitle = viewTitle;
    }

    @Override
    public void render() {
        io.printLine(viewTitle);
        for (int i = 0; i < listOfItems.size(); i++) {
            io.printLine((i + 1) + ". \t" + listOfItems.get(i));
        }
    }

    public void setItems(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
