package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCollection {

    protected HashMap<String, Item> availableItems = new HashMap();
    protected HashMap<String, Item> issuedItems = new HashMap();

    public ItemCollection(){}

    public ItemCollection(Item[] items){
        for (Item item : items) {
            availableItems.put(item.getTitle(), item);
        }

    }

    public void addItem(Item item){
        availableItems.put(item.getTitle(), item);
    }

    public boolean issueItem(String itemTitle, User user){
        if(!availableItems.containsKey(itemTitle)){
            return false;
        }

        Item item = availableItems.remove(itemTitle);
        item.setBorrower(user);
        issuedItems.put(itemTitle, item);

        return true;
    }

    public boolean returnItem(String itemTitle, User user){
        if(!issuedItems.containsKey(itemTitle)){
            return false;
        }


        if(issuedItems.get(itemTitle).getBorrower() != user){
            return false;
        }

        Item item = issuedItems.remove(itemTitle);
        item.setBorrower(null);

        availableItems.put(itemTitle, item);

        return true;
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
}
