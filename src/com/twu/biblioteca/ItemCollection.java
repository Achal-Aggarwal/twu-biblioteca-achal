package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCollection {

    protected HashMap<String, Item> availableItems = new HashMap<String, Item>();
    protected HashMap<String, Issue> issues = new HashMap<String, Issue>();

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

        Issue issuedItem = new Issue(item, user);
        issues.put(itemTitle, issuedItem);

        return true;
    }

    public boolean returnItem(String itemTitle, User user){
        if(!issues.containsKey(itemTitle)){
            return false;
        }

        Issue issuedItem = issues.remove(itemTitle);

        if(issuedItem.getIssuer() != user){
            return false;
        }

        availableItems.put(itemTitle, issuedItem.getIssuedItem());

        return true;
    }


    public List<Item> getListOfAvailableItems() {
        List<Item> items = new ArrayList<Item>();
        for (Item item : availableItems.values()) {
            items.add(item);
        }

        return items;
    }

    public List<Issue> getListOfIssuedItems() {

        List<Issue> issuedItemsList = new ArrayList<Issue>();
        for (Issue issue : issues.values()) {
            issuedItemsList.add(issue);
        }

        return issuedItemsList;
    }
}
