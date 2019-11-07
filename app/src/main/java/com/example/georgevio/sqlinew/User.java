package com.example.georgevio.sqlinew;

public class User {

    private String Name;
    private String Phone;
    private String Email;
    private String Street;
    private String Place;

    public User(String dName,String dPhone,String dEmail,String dStreet,String dPlace){

        Name = dName;
        Phone = dPhone;
        Email = dEmail;
        Street = dStreet;
        Place = dPlace;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        Email = Email;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        Street = Street;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
        Place = Place;
    }
}

