package co.edu.uniquindio.bookyourstay.controllers;


import co.edu.uniquindio.bookyourstay.models.User;
import co.edu.uniquindio.bookyourstay.models.Session;
import co.edu.uniquindio.bookyourstay.models.enums.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;




public class LoginController {



    @FXML
    public TextField txtEmail;


    @FXML
    public TextField txtPassword;

    @FXML
    public Label messageErrorCorreo;

    private Boolean isValid = false;


    private final MainController mainController;

    public LoginController() {
        this.mainController = MainController.getInstancia();
    }

    public void goRegister(ActionEvent actionEvent) {
        mainController.navigateWindow("/register.fxml", "Crear una cuenta");
        mainController.closeWindow(txtEmail);
    }

    public void login(ActionEvent actionEvent) {
        try {
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            // Cambiar throws a try catch

            if(email == null || email.isEmpty()) {
                messageErrorCorreo.setText("Por favor ingrese su correo");
                isValid = false;
            }else{

                isValid = mainController.validarCorreo(email);
                if(!isValid){
                    messageErrorCorreo.setText("El correo ingresado no existe");
                }
            }

            if (password == null || password.isEmpty()) {
                messageErrorCorreo.setText("Por favor ingrese su contraseña");
                isValid = false;
            }else{
                isValid = mainController.validarContrasena(password);
                if(!isValid){
                    messageErrorCorreo.setText("Contraseña incorrecta");
                }
            }

            if(isValid){
                User user = mainController.login(email, password);
                Session session = Session.getInstancia();
                session.setUser(user);

                if(user.getRole() == Role.HOTELIER) {
                    mainController.navigateWindow("/panelHotelier.fxml", "Panel hotelero");
                } else if (user.getRole() == Role.ADMIN) {
                    mainController.navigateWindow("/panelHotelier.fxml", "Panel Administrador");
                } else{
                    mainController.navigateWindow("/home.fxml", "Inicio");
                }
                mainController.closeWindow(txtEmail);
            }
        } catch (Exception e) {
            mainController.showAlert(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }


}
