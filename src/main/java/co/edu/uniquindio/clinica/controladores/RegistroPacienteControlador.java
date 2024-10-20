package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.utils.EnvioEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroPacienteControlador extends AbstractControlador{


    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;


    @FXML
    private Button btnNewPaciente;

    @FXML
    private void addNewPaciente(ActionEvent event) {
        try {
            addPaciente();
        }catch(Exception ex) {
            showAlert(ex.getMessage(), Alert.AlertType.ERROR);
            System.out.println(ex.getMessage() + Alert.AlertType.ERROR);
        }
    }

    private void addPaciente() {
        //Lista de clientes.add
        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();

        System.out.println(identificacion);
        //EnvioEmail.enviarNotificacion("cesarm.baquirom@uqvirtual.edu.co", "Prueba 1", "Hola " + nombre + " su registro fue exitoso!");
    }

    private void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
