package co.edu.uniquindio.contacts.controllers;

import co.edu.uniquindio.contacts.model.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
public class ContactController implements Initializable {



    Contact contact; // Contact Instance
    private ArrayList<Contact> contactList; // Contact list
    LocalDateTime now = LocalDateTime.now(); // Current hour

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtBirthday;

    @FXML
    private TextField txtUrlPhoto;

    @FXML
    private Label messageErrorName;

    @FXML
    private Label messageErrorLastname;

    @FXML
    private Label messageErrorPhone;

    @FXML
    private Label messageErrorEmail;

    @FXML
    private Label messageErrorBirthday;

    @FXML
    private Label messageErrorUrlPhoto;

    @FXML
    private Button btnNewContact;

    @FXML
    private TableView<Contact> tableContacts;

    @FXML
    private TableColumn<Contact, String> colName;

    @FXML
    private TableColumn<Contact, String> colLastname;

    @FXML
    private TableColumn<Contact, String> colPhone;

    @FXML
    private TableColumn<Contact, String> colEmail;

    @FXML
    private TableColumn<Contact, String> colBirthday;

    @FXML
    private TableColumn<Contact, String> colUrlPhoto;

    @FXML
    private TableColumn<Contact, Void> editButton;

    @FXML
    private TableColumn<Contact, Void> deleteButton;


    public ContactController() {
        this.contactList = new ArrayList<>();
        this.tableContacts = new TableView<>();
    }

    public void addNewContact(ActionEvent e){
        try {
            validarFormulario();
        }catch (Exception ex){
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
            System.out.println(ex.getMessage() + Alert.AlertType.ERROR);
        }
    }

    // Method for add contacts
    public void addContact(String name, String lastname, String phone, String email, String birthday, String urlPhoto) {
        contactList.add(new Contact(name, lastname, phone, email, birthday, urlPhoto));
    }

    // Method for delete contacts
    public void deleteContact(int index) {
        contactList.remove(index);
    }

    public ArrayList<Contact> listContacts() {
        return this.contactList;
    }

    @FXML
    public void validarFormulario() {
        // Obtener el valor del campo de texto
        String name = txtName.getText();
        String lastName = txtLastname.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String birthday = txtBirthday.getText();
        String urlPhoto = txtUrlPhoto.getText();
        boolean esValido = true;

        // ------------ Validaciones -----------

        // Validar que no esté vacío
        if (name == null || name.trim().isEmpty()) {
            messageErrorName.setText("El nombre no puede estar vacío.");
            esValido= false;
        } else if (name.length() < 3) {
            // Validar longitud mínima
            messageErrorName.setText("El nombre debe tener al menos 3 caracteres.");
            esValido= false;
        }else{
            messageErrorName.setText("");
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            messageErrorLastname.setText("El apellido no puede estar vacío.");
            esValido= false;
        } else if (lastName.length() < 3) {
            messageErrorLastname.setText("El apellido debe tener al menos 3 caracteres.");
            esValido= false;
        }else{
            messageErrorLastname.setText("");
        }

        if (phone == null || phone.trim().isEmpty()) {
            messageErrorPhone.setText("El telefono no puede estar vacío.");
            esValido= false;
        } else {
            messageErrorPhone.setText("");
        }

        if(esValido){

//            notaPrincipal.agregarNota(indice, name, lastName, phone, email, birthday);
            addContact(name, lastName, phone, email, now.toString(), urlPhoto);
            mostrarAlerta("Contacto guardado correctamente", Alert.AlertType.INFORMATION);
            // Si la validación es exitosa, limpiar el mensaje de error
            messageErrorName.setText("");
            messageErrorLastname.setText("");
            messageErrorPhone.setText("");
            messageErrorEmail.setText("");
            messageErrorBirthday.setText("");
            messageErrorUrlPhoto.setText("");

            tableContacts.setItems( FXCollections.observableArrayList(listContacts()) );

            txtName.clear();
            txtLastname.clear();
            txtPhone.clear();
            txtEmail.clear();
            txtBirthday.clear();
            txtUrlPhoto.clear();
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //boxCategorias.getItems().addAll("Tarea", "Trabajo", "Examen", "Reunion");

        // Agregar el botón de Editar
        //agregarBotonEditar();

        // Agregar el botón de Eliminar
        //agregarBotonEliminar();

        //colId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdNota())));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colLastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        colPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colBirthday.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));
        colUrlPhoto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlPhoto()));

    }

}
