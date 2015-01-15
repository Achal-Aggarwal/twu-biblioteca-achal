package com.twu.biblioteca;

import java.util.HashMap;

public class SessionManager {
    private HashMap<String, User> users = new HashMap<String, User>();
    private User currentUser = null;
    private static SessionManager sessionObject = null;
    private SessionManager(){}

    public static SessionManager getSession(){
        if(sessionObject == null){
            sessionObject = new SessionManager();
        }

        return sessionObject;
    }

    public void registerUser(User user) {
        users.put(user.getLibraryNumber(), user);
    }

    public boolean isUserPresent(String libraryNumber) {
        return users.containsKey(libraryNumber);
    }

    public boolean validateUser(String libraryNumber, String password) {
        User user = users.get(libraryNumber);

        if (user == null){
            return false;
        }

        return user.getPassword().equals(password);
    }

    public boolean login(String userLibraryNumber) {
        if(!isUserPresent(userLibraryNumber)){
            return false;
        }

        currentUser = users.get(userLibraryNumber);
        return true;
    }

    public void logout() {
        currentUser = null;
    }

    public User getLoggedInUser() {
        return currentUser;
    }

    public static void clearSession(){
        sessionObject = null;
    }
}
