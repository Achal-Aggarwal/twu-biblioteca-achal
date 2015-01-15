package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class Library {

    protected HashMap<String, Item> availableItems = new HashMap();
    protected HashMap<String, Item> issuedItems = new HashMap();

    public boolean isItemAvailable(String itemTitle) {
        return availableItems.containsKey(itemTitle);
    }

    public boolean isItemIssued(String itemTitle) {
        return issuedItems.containsKey(itemTitle);
    }

    public Item addIntoAvailableItem(Item item) {
        if(isItemAvailable(item.getTitle())){
            return null;
        }

        availableItems.put(item.getTitle(), item);

        return item;
    }
    public Item addIntoIssuedItem(Item item) {
        if(isItemIssued(item.getTitle())){
            return null;
        }

        issuedItems.put(item.getTitle(), item);

        return item;
    }

    public List<String> getListOfAvailableItems() {

        List<String> availableItemsList = new ArrayList<String>();
        for (Item item : availableItems.values()) {
            availableItemsList.add(item.getFormattedString());
        }

        return availableItemsList;
    }

    public List<String> getListOfIssuedItems() {

        List<String> issuedItemsList = new ArrayList<String>();
        for (Item item : issuedItems.values()) {
            issuedItemsList.add(item.getFormattedString() + " issued by " + item.getBorrower().contactInformation());
        }

        return issuedItemsList;
    }

    public Item removeFromAvailableItem(String itemName) {
        if(!isItemAvailable(itemName)){
            return null;
        }

        return availableItems.remove(itemName);
    }
    public Item removeFromIssuedItem(String itemName) {
        if(!isItemIssued(itemName)){
            return null;
        }

        return issuedItems.remove(itemName);
    }
}
