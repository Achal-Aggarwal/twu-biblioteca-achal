package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.*;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class MenuViewController extends ViewController {
    private LinkedHashMap menuItems = new LinkedHashMap();

    public MenuViewController(Library library, PrintStream output, Scanner input) {
        super(library, output, input);
    }

    public boolean execute() {
        output.println(getTitle());

        Iterator menuItemSet = menuItems.entrySet().iterator();
        while (menuItemSet.hasNext()) {
            Map.Entry menuItem = (Map.Entry)menuItemSet.next();
            output.println(menuItem.getKey() + ". \t" + ((ViewController) menuItem.getValue()).getTitle());
        }

        String selectedAction = input.nextLine();

        if(!menuItems.containsKey(selectedAction) && selectedAction.length() > 0){
            output.println("Select a valid option!");
        } else if(menuItems.containsKey(selectedAction)){
            return ((ViewController) menuItems.get(selectedAction)).execute();
        }
        return true;
    }

    public void setAction(String onUserInput, ViewController viewController) {
        menuItems.put(onUserInput, viewController);
    }


    public String getTitle() {
        return "Main Menu.";
    }
}
