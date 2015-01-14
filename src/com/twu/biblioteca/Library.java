package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class Library {

    protected HashMap items = new HashMap();

    public boolean isItemPresent(String itemTitle) {
        return items.containsKey(itemTitle);
    }

    public Item addItem(Item item) {
        if(isItemPresent(item.getTitle())){
            return null;
        }

        items.put(item.getTitle(), item);

        return item;
    }

    public List<String> getListOfAvailableItems() {

        List<String> availableItems = new ArrayList<String>();
        for (Object item : items.values()) {
            availableItems.add(((Item) item).getFormattedString());
        }

        return availableItems;
    }

    public Item removeItem(String itemName) {
        if(!isItemPresent(itemName)){
            return null;
        }

        return (Item) items.remove(itemName);
    }
}
