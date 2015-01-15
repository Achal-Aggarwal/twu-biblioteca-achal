package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class ListOfItemsView extends View {
    private List<String> listOfItems;
    private String itemType;

    public ListOfItemsView(InputOutputManger inputOutputManger, String itemType) {
        super(inputOutputManger);
        listOfItems =  Arrays.asList();
        this.itemType = itemType;
    }

    @Override
    public void render() {
        io.printLine("List of " + itemType + "s available.");
        for (int i = 0; i < listOfItems.size(); i++) {
            io.printLine((i + 1) + ". \t" + listOfItems.get(i));
        }
    }

    public void setItems(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
