package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.utils.EnvioEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
            // Guardar los datos del formulario en atributos de la clase
            idDocumentation = txtCc.getText();
            fullname = txtName.getText();
            phone = txtPhone.getText(); // Placeholder para el campo de teléfono
            // phone = txtPhone.getText();
            email = txtEmail.getText();
            role = Role.USER;
            password = txtPassword.getText();
            passwordConfirm = txtPasswordConfirm.getText();

            Boolean isValid = validateFormRegister(idDocumentation, fullname, phone, email, password, passwordConfirm);

            if(isValid){
                // Generar el código de activación
                generatedCode = EnvioEmail.createCodeActivation();

                // Evniar el codigo al correo registrado
                EnvioEmail.enviarNotificacion(email, "Codigo de verificacion Book Your Stay", "Hola! Te enviamos tu codigo de verificación "+generatedCode);
                System.out.println("Código generado: " + generatedCode);

                // Abrir la ventana verifyCode.fxml para que el usuario ingrese el código
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
        } else if (!email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$")) {  // Verifica el formato de correo
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

        // Si la validación es exitosa, limpiar el mensaje de error
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

        // Verificar el código ingresado con el generado
        boolean codigoVerificado = verifyActivationCode(codeInput, generatedCode);
        System.out.println("Resultado comprobación: " + codigoVerificado);

        // Verificar si el código fue correcto y continuar el registro
        if (codigoVerificado) {
            continuarRegistro(); // Continuar con el registro
        } else {
            System.out.println("CODIGO INCORRECTO");
            // Aquí podrías manejar un mensaje de error o reintento
        }
    }

    // Método para continuar el registro después de verificar el código
    public void continuarRegistro() {
        System.out.println("Código verificado correctamente. Continuando con el registro...");

        // Registrar el usuario con los datos almacenados
        try {
            mainController.registerUser(idDocumentation, fullname, phone, role, email, password, generatedCode);
            // Cerrar la ventana actual (asumiendo que txtCorreo pertenece a la ventana que debe cerrarse)
            mainController.cerrarVentana(txtEmail);
            // Navegar a la ventana de inicio de sesión
            mainController.navigateWindow("/login.fxml", "Iniciar sesión");
            mainController.printUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
