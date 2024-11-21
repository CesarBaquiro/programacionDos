package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.utils.EnvioEmail;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Observer, Initializable {

    @FXML
    public TextField txtCc;

    @FXML
    public Label msgErrorCc;

    @FXML
    public TextField txtName;

    @FXML
    public Label msgErrorName;

    @FXML
    public TextField txtPhone;

    @FXML
    public Label msgErrorPhone;

    @FXML
    public TextField txtEmail;

    @FXML
    public Label msgErrorEmail;

    @FXML
    public TextField txtPassword;

    @FXML
    public Label msgErrorPassword1;

    @FXML
    public TextField txtPasswordConfirm;

    @FXML
    public Label msgErrorPassword2;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    public Label msgErrorComboBox;

    private final MainController mainController;

    private Observer observer;

    private String generatedCode;


    // Atributos para almacenar los datos del usuario
    private String idDocumentation;
    private String fullname;
    private String phone;
    private Role role;
    private String email;
    private String password;
    private String passwordConfirm;

    public RegisterController() {
        this.mainController = MainController.getInstancia();
    }

    public void registro(ActionEvent actionEvent) {

        try {
            // Save the form data in class attributes
            idDocumentation = txtCc.getText();
            fullname = txtName.getText();
            phone = txtPhone.getText(); // Placeholder para el campo de teléfono
            // phone = txtPhone.getText();
            email = txtEmail.getText();
            //ring role = cmbRole.valueProperty().get();
            String roleSelected = cmbRole.getValue();

            if(roleSelected.equals("Usuario")){
                role = Role.USER;
            }else if(roleSelected.equals("Hotelero")){
                role = Role.HOTELIER;
            }

            password = txtPassword.getText();
            passwordConfirm = txtPasswordConfirm.getText();

            Boolean isValid = validateFormRegister(idDocumentation, fullname, phone, email, password, passwordConfirm);

            if(isValid){
                // Generate the activation code
                generatedCode = EnvioEmail.createCodeActivation();

                // Send the code to the registered email
                //EnvioEmail.enviarNotificacion(email, "Codigo de verificacion Book Your Stay", "Hola! Te enviamos tu codigo de verificación "+generatedCode);
                System.out.println("Código generado: " + generatedCode);

                // Open the verifyCode.fxml window for the user to enter the code
                mainController.navegarVentanaObservable("/verifyCode.fxml", "Verificar código de autenticación", this);
            }
        } catch (Exception e) {
            mainController.showAlert(e.getMessage(), "Error", Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private boolean validateFormRegister(String idDocumentation, String fullname, String phone, String email, String password, String passwordConfirm){
        Boolean isValidForm = true;

        // ------------ Validations -----------

        // Validator ID identification
        if (idDocumentation == null || idDocumentation.trim().isEmpty()) {
            msgErrorCc.setText("La cedula no puede estar vacia");
            isValidForm = false;
        } else if (idDocumentation.length() < 6) {
            // Validar longitud mínima
            msgErrorCc.setText("La cedula debe tener al menos 6 caracteres");
            isValidForm= false;
        }else{
            msgErrorCc.setText("");
        }

        // Validator fullname ----
        if (fullname == null || fullname.trim().isEmpty()) {
            msgErrorName.setText("El nombre no puede estar vacío");
            isValidForm = false;
        } else if (fullname.length() < 3) {
            // Validar longitud mínima
            msgErrorName.setText("El nombre debe tener al menos 3 caracteres");
            isValidForm= false;
        }else{
            msgErrorName.setText("");
        }

        // Validator phone ----
        if (phone == null || phone.trim().isEmpty()) {
            msgErrorPhone.setText("El telefono no puede estar vacío");
            isValidForm= false;
        }else if (!phone.matches("\\d+")) {  // Verifica si contiene solo números
            msgErrorPhone.setText("El teléfono solo debe contener números");
            isValidForm = false;
        }else if (phone.length() != 10) { // Verifica tiene 10 caracteres
            msgErrorPhone.setText("El telefono debe tener 10 caracteres");
            isValidForm = false;
        } else {
            msgErrorPhone.setText("");
        }

        // Validator email
        if (email == null || email.trim().isEmpty()) {
            msgErrorEmail.setText("El correo electrónico no puede estar vacío");
            isValidForm = false;
        } else if (!email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$")) {  // Check the mail format
            msgErrorEmail.setText("El correo electrónico no tiene un formato válido");
            isValidForm = false;
        } else {
            msgErrorEmail.setText("");
        }

        // Validator password
        if (!password.equals(passwordConfirm)) {
            msgErrorPassword1.setText("Las contraseñas no coinciden");
            msgErrorPassword2.setText("Las contraseñas no coinciden");
            isValidForm = false;
        }else{
            msgErrorPassword1.setText("");
            msgErrorPassword2.setText("");
        }

        if (password == null || password.trim().isEmpty()) {
            msgErrorPassword1.setText("La contraseña no puede estar vacia");
            isValidForm = false;
        }

        if (passwordConfirm == null || passwordConfirm.trim().isEmpty()) {
            msgErrorPassword2.setText("La contraseña no puede estar vacia");
            isValidForm = false;
        }

        // If validation is successful, clear the error message
        if(isValidForm){
            msgErrorCc.setText("");
            msgErrorName.setText("");
            msgErrorEmail.setText("");
            msgErrorPhone.setText("");
            msgErrorPassword1.setText("");
            msgErrorPassword2.setText("");
        }

        return isValidForm;
    }

    @Override
    public void notificar() {
        // Método vacío, implementar si es necesario
    }

    @Override
    public void storeReceivedCode(String codeInput) {
        System.out.println("Código ingresado: " + codeInput);
        System.out.println("Código generado: " + generatedCode);

        // Verify the entered code with the generated one
        boolean codigoVerificado = verifyActivationCode(codeInput, generatedCode);
        System.out.println("Resultado comprobación: " + codigoVerificado);

        // Check if the code was correct and continue registration
        if (codigoVerificado) {
            continuarRegistro(); // Continue with registration
        } else {
            System.out.println("CODIGO INCORRECTO");
           // Here you could handle an error or retry message
        }
    }

    //Method to continue registration after verifying the code
    public void continuarRegistro() {
        System.out.println("Código verificado correctamente. Continuando con el registro...");

      // Register the user with the stored data
        try {
            mainController.registerUser(idDocumentation, fullname, phone, role, email, password, generatedCode);
            // Close the current window (assuming that txtMail belongs to the window that should be closed)
            mainController.closeWindow(txtEmail);
            // Navigate to the login window
            mainController.navigateWindow("/login.fxml", "Iniciar sesión");
            mainController.printUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRole.setItems( FXCollections.observableList(mainController.listRoles()));
    }
}
