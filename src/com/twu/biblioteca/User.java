package com.twu.biblioteca;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String emailid;
    private String phoneNumber;

    public User(String libraryNumber, String password, String name, String emailid, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.emailid = emailid;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String contactInformation() {
        return name + " | " + emailid + " | " + phoneNumber;
    }
}
