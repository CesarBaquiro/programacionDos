package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroCitaControlador extends AbstractControlador implements Initializable {

    Clinica clinica = Clinica.getInstanciaClinica();

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private ComboBox<String> comboBoxServicio;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtHora;


    @FXML
    private Button btnNewCita;



    @FXML
    private void addNewCita(ActionEvent event) {
        try {
            // Completar addNewCita
        System.out.println("Se añadio una cita");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    private void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxServicio.setItems( FXCollections.observableList(clinica.listarNombreServicios()));
    }

}
