package co.edu.uniquindio.contacts.controllers;

import co.edu.uniquindio.contacts.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class ContactController {



    Contact contact; // Contact Instance
    private ArrayList<Contact> contactList; // Contact list
    LocalDateTime now = LocalDateTime.now(); // Current hour

    @FXML
    private Button btnNewContact;

    public ContactController() {
        this.contactList = new ArrayList<>();


        /**
         * DEBUG methods
         *
         */
        addContact("Cesar", "Baquiro", "1456464", "cdcdscsdf", now, "https//");
        addContact("Camilo", "Baquiro", "1456464", "cdcdscsdf", now, "https//");
        System.out.println(getContactList().toString());
        System.out.println(getContactList().get(1).getName());
        deleteContact(1);
        System.out.println(getContactList().toString());
    }

    public void addNewContact(ActionEvent e){
        try {
            System.out.println("Hola"); //Validar para crear nueva nota

        }catch (Exception ex){
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
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

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){

        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

}
