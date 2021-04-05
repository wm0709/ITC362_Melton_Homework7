package com.example.friendapp;

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend(int newId, String newFirstName, String newLastName, String newEmail){
        setID(newId);
        setFirstName(newFirstName);
        setLastName(newLastName);
        setEmail(newEmail);
    }

    private void setEmail(String newEmail) {
        email = newEmail;
    }

    private void setLastName(String newLastName) {
        lastName = newLastName;
    }

    private void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    private void setID(int newId) {
        id = newId;
    }

    public int getId(){return id;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}

    public String toString(){return id + "; " + firstName + " " + lastName + "; " + email;}
}
