package co.edu.uniquindio.clinica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MostrarRegistroCitaControlador extends AbstractControlador{

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
}
