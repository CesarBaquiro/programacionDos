package co.edu.uniquindio.contacts.controllers;

import co.edu.uniquindio.contacts.model.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
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
            showAlert(ex.getMessage(), Alert.AlertType.ERROR);
            System.out.println(ex.getMessage() + Alert.AlertType.ERROR);
        }
    }

    // Method for add contacts
    public void addContact(String name, String lastname, String phone, String email, String birthday, String urlPhoto) {
        contactList.add(new Contact(name, lastname, phone, email, birthday, urlPhoto));
    }

    // Method for delete contacts
    public void deleteContact(Contact contact) {
        contactList.remove(contact);
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

        // Validar nombre ----
        if (name == null || name.trim().isEmpty()) {
            messageErrorName.setText("El nombre no puede estar vacío");
            esValido= false;
        } else if (name.length() < 3) {
            // Validar longitud mínima
            messageErrorName.setText("El nombre debe tener al menos 3 caracteres");
            esValido= false;
        }else{
            messageErrorName.setText("");
        }

        // Validar apellido ----
        if (lastName == null || lastName.trim().isEmpty()) {
            messageErrorLastname.setText("El apellido no puede estar vacío.");
            esValido= false;
        } else if (lastName.length() < 3) {
            messageErrorLastname.setText("El apellido debe tener al menos 3 caracteres");
            esValido= false;
        }else{
            messageErrorLastname.setText("");
        }

        // Validar telefono ----
        if (phone == null || phone.trim().isEmpty()) {
            messageErrorPhone.setText("El telefono no puede estar vacío");
            esValido= false;
        }else if (!phone.matches("\\d+")) {  // Verifica si contiene solo números
            messageErrorPhone.setText("El teléfono solo debe contener números");
            esValido = false;
        }else if (phone.length() != 10) { // Verifica tiene 10 caracteres
            messageErrorPhone.setText("El telefono debe tener 10 caracteres");
            esValido = false;
        } else {
            messageErrorPhone.setText("");
        }

        // Validar correo electronico
        if (email == null || email.trim().isEmpty()) {
            messageErrorEmail.setText("El correo electrónico no puede estar vacío");
            esValido = false;
        } else if (!email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$")) {  // Verifica el formato de correo
            messageErrorEmail.setText("El correo electrónico no tiene un formato válido");
            esValido = false;
        } else {
            messageErrorEmail.setText("");
        }


        // Validar fecha de cumpleaños ----
        if (birthday == null || birthday.trim().isEmpty()) {
            messageErrorBirthday.setText("La fecha de cumpleaños no puede estar vacia");
            esValido = false;
        }

        // Validar URl de foto ----
        if (urlPhoto == null || urlPhoto.trim().isEmpty()) {
            messageErrorUrlPhoto.setText("La URL no puede estar vacia");
            esValido = false;
        }

        // Verificar que no se repita ----
        for (Contact c : this.contactList) {
            System.out.println(c);
            if (c.getEmail().equals(email) || c.getPhone().equals(phone)) {
                showAlert("El contacto ya existe", Alert.AlertType.ERROR);
                esValido = false;
            }
        }

        if(esValido){
            addContact(name, lastName, phone, email, birthday, urlPhoto);
            showAlert("Contacto guardado correctamente", Alert.AlertType.INFORMATION);
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

    private void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void addBtnEdit() {
        // Usar un CellFactory para crear el botón en cada fila
        Callback<TableColumn<Contact, Void>, TableCell<Contact, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Contact, Void> call(final TableColumn<Contact, Void> param) {
                final TableCell<Contact, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Editar");

                    {
                        // Acción del botón Editar
                        btn.setOnAction((ActionEvent event) -> {
                            btnNewContact.setText("Editar");
                            Contact contact = getTableView().getItems().get(getIndex());
                            txtName.setText(contact.getName());
                            txtLastname.setText(contact.getLastname());
                            txtPhone.setText(contact.getPhone());
                            txtEmail.setText(contact.getEmail());
                            txtBirthday.setText(contact.getBirthday());
                            txtUrlPhoto.setText(contact.getUrlPhoto());
                            btnNewContact.setOnAction((ActionEvent e) -> {
                                System.out.println("Editando a " + contact.getName());
                                btnNewContact.setText("Guardar nuevo contacto");
                            });

                            // Aquí puedes añadir lógica para editar la nota
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        editButton.setCellFactory(cellFactory);
    }

    private void addBtnDelete() {
        // Usar un CellFactory para crear el botón en cada fila
        Callback<TableColumn<Contact, Void>, TableCell<Contact, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Contact, Void> call(final TableColumn<Contact, Void> param) {
                final TableCell<Contact, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Eliminar");

                    {
                        // Acción del botón Eliminar
                        btn.setOnAction((ActionEvent event) -> {
                            Contact contact = getTableView().getItems().get(getIndex());
                            deleteContact(contact);
                            tableContacts.getItems().remove(contact);
                            // Aquí puedes añadir lógica para eliminar la nota
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        deleteButton.setCellFactory(cellFactory);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //boxCategorias.getItems().addAll("Tarea", "Trabajo", "Examen", "Reunion");

        // Agregar el botón de Editar
        addBtnEdit();

        // Agregar el botón de Eliminar
        addBtnDelete();

        //colId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdNota())));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colLastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        colPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colBirthday.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));
        colUrlPhoto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUrlPhoto()));

    }

}
