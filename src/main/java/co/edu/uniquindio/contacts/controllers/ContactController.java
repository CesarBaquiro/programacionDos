package co.edu.uniquindio.contacts.controllers;

import co.edu.uniquindio.contacts.model.Contact;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class ContactController {

    Contact contact; // Contact Instance
    private ArrayList<Contact> contactList; // Contact list
    LocalDateTime now = LocalDateTime.now(); // Current hour

    public ContactController() {
        this.contactList = new ArrayList<>();
    }

    // Method for add contacts
    public void addContact(String name, String lastname, String phone, String email, LocalDateTime birthday, String urlPhoto) {
        contactList.add(new Contact(name, lastname, phone, email, birthday, urlPhoto));
    }

    // Method for delete contacts
    public void deleteContact(int index) {
        contactList.remove(index);
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public Contact getContactById(int id) {
        return contactList.get(id);
    }

}
