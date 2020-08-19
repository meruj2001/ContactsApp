package com.contacts;

public class Contact implements Comparable<Contact> {
    private int id;
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;

    public Contact(int id, String name, String surname, int phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "PhoneNumber: " + phoneNumber + "\n" +
                "Email: " + email;
    }

    //CompareTo for sorting
    @Override
    public int compareTo(Contact other) {
        return this.getName().compareTo(other.getName());
    }
}
