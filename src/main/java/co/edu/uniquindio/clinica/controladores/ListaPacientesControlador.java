package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaPacientesControlador extends AbstractControlador implements Initializable {
    Clinica clinica = Clinica.getInstanciaClinica();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
