package co.edu.uniquindio.reservasuq.controlador;


import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;




public class LoginControlador {



    @FXML
    public TextField txtCorreo;


    @FXML
    public TextField txtPassword;

    @FXML
    public Label messageErrorCorreo;

    private Boolean esValido = false;


    private final ControladorPrincipal controladorPrincipal;


    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }



    public void irARegistro(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/registro.fxml", "Crear una cuenta");
        controladorPrincipal.cerrarVentana(txtCorreo);
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

                esValido = controladorPrincipal.validarCorreo(email);
                if(!esValido){
                    messageErrorCorreo.setText("El correo ingresado no existe");
                }
            }

            if (password == null || password.isEmpty()) {
                messageErrorCorreo.setText("Por favor ingrese su contraseña");
                esValido = false;
            }else{
                esValido = controladorPrincipal.validarContrasena(password);
                if(!esValido){
                    messageErrorCorreo.setText("Contraseña incorrecta");
                }
            }

            if(esValido){
                Persona persona = controladorPrincipal.login(email, password);
                Sesion sesion = Sesion.getInstancia();
                sesion.setPersona(persona);

                if(persona.getTipoPersona() == TipoPersona.ADMIN) {
                    controladorPrincipal.navegarVentana("/panelAdmin.fxml", "Panel Administrador");
                }else{
                    controladorPrincipal.navegarVentana("/panelCliente.fxml", "Panel Usuario");
                }
                controladorPrincipal.cerrarVentana(txtCorreo);
            }


        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }


}
