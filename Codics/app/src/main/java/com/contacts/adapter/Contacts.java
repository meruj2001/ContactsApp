package com.contacts.adapter;

import com.contacts.Contact;

import java.util.List;

public class Contacts {

    List<Contact> contactList;

    public Contacts(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
