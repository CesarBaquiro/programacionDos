package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroControlador implements Initializable {

    @FXML
    public TextField txtCedula;

    @FXML
    public TextField txtNombre;

    @FXML
    private ComboBox<String> comboBoxTipoPersona;

    @FXML
    public TextField txtCorreo;

    @FXML
    public TextField txtPassword;

    private final ControladorPrincipal controladorPrincipal;

    public RegistroControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void registro(ActionEvent actionEvent) {

        try {

            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String tipoPersonaElegida = comboBoxTipoPersona.getValue();
            String contrasena = txtPassword.getText();

            TipoPersona tipoPersona = TipoPersona.valueOf(tipoPersonaElegida.toUpperCase());

            // Cambiar throws a try catch
            controladorPrincipal.registrarPersona(cedula,nombre,tipoPersona,correo,contrasena);

            controladorPrincipal.cerrarVentana(txtCorreo);
            controladorPrincipal.navegarVentana("/login.fxml", "Iniciar sesion");
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxTipoPersona.setItems( FXCollections.observableList(controladorPrincipal.listarTiposPesonas()) );
    }
}
