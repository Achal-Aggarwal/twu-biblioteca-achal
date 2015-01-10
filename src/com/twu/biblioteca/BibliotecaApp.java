package com.twu.biblioteca;

public class BibliotecaApp {
    public String welcomeMessage(){
        return "Welcome and thank you for taking time to visit Biblioteca.";
    }
    public static void main(String[] args) {
        BibliotecaApp application = new BibliotecaApp();
        System.out.println(application.welcomeMessage());
    }
}
