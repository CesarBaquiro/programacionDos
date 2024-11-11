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
    public TextField txtCorreo;


    @FXML
    public TextField txtPassword;

    @FXML
    public Label messageErrorCorreo;

    private Boolean esValido = false;


    private final MainController mainController;


    public LoginController() {
        this.mainController = MainController.getInstancia();
    }



    public void irARegistro(ActionEvent actionEvent) {
        mainController.navigateWindow("/register.fxml", "Crear una cuenta");
        mainController.cerrarVentana(txtCorreo);
    }

    public void login(ActionEvent actionEvent) {


        try {
            String email = txtCorreo.getText();
            String password = txtPassword.getText();

            // Cambiar throws a try catch

            if(email == null || email.isEmpty()) {
                messageErrorCorreo.setText("Por favor ingrese su correo");
                esValido = false;
            }else{

                esValido = mainController.validarCorreo(email);
                if(!esValido){
                    messageErrorCorreo.setText("El correo ingresado no existe");
                }
            }

            if (password == null || password.isEmpty()) {
                messageErrorCorreo.setText("Por favor ingrese su contraseña");
                esValido = false;
            }else{
                esValido = mainController.validarContrasena(password);
                if(!esValido){
                    messageErrorCorreo.setText("Contraseña incorrecta");
                }
            }

            if(esValido){
                User user = mainController.login(email, password);
                Session session = Session.getInstancia();
                session.setUser(user);

                if(user.getRole() == Role.ADMIN) {
                    mainController.navigateWindow("/panelAdmin.fxml", "Panel Administrador");
                }else{
                    mainController.navigateWindow("/panelClient.fxml", "Panel Usuario");
                }
                mainController.cerrarVentana(txtCorreo);
            }


        } catch (Exception e) {
            mainController.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }


}
