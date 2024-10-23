package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.*;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class RegistroCitaControlador extends AbstractControlador implements Initializable {

    Clinica clinica = Clinica.getInstanciaClinica();

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private ComboBox<String> comboBoxServicio;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboBoxHora;

    @FXML
    private DatePicker txtFecha;



    @FXML
    private Button btnNewCita;

    @FXML
    private void addNewCita(ActionEvent event) {
        try {
           addCita();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    private void addCita() {
        //Lista de clientes.add
        String identificacion = txtIdentificacion.getText();
        String servicioSeleccionado = comboBoxServicio.getValue();
        LocalDate fechaIngresada = txtFecha.getValue();
        String hora = comboBoxHora.getValue();
        String idCita = UUID.randomUUID().toString();

        // Encontrar el paciente y sobreescribir
        Paciente paciente = null;
        for (Paciente p : clinica.getPacientes()) {
            if(identificacion == p.getCedula()){
                paciente = p;
            }
        }

        // Encontrar el servicio y sobreescribir
        Servicio servicio = null;
        for (Servicio s : clinica.getServicios()){
            if(servicioSeleccionado == s.getNombre()){
                servicio = s;
            }
        }

        // Fecha de prueba 2022-08-05 22:51:53
        clinica.registrarCita(new Cita(paciente, idCita, fechaIngresada, hora,  servicio,  new Factura()));

        //EnvioEmail.enviarNotificacion("cesarm.baquirom@uqvirtual.edu.co", "Prueba 1", "Hola " + nombre + " su registro fue exitoso!");
    }

    private ArrayList<String> listarHoras(){
        ArrayList<String> horas = new ArrayList<>();
        horas.add("9:00AM");
        horas.add("10:00AM");
        horas.add("11:00AM");
        horas.add("12:00AM");
        horas.add("2:00PM");
        horas.add("3:00PM");
        horas.add("4:00PM");
        horas.add("5:00PM");
        horas.add("6:00PM");
        return horas;
    }


    private void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxHora.setItems( FXCollections.observableList(listarHoras()));
        comboBoxServicio.setItems( FXCollections.observableList(clinica.listarNombreServicios()));
    }

}
