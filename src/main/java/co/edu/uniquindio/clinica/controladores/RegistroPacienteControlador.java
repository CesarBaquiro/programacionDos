package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.ClinicaApplication;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.utils.EnvioEmail;
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

public class RegistroPacienteControlador extends AbstractControlador implements Initializable {


    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> comboBoxSuscripcion;

    @FXML
    private Button btnNewPaciente;



    //Cargar categorias en el ComboBox
    //comboBoxSuscripcion.setItems( FXCollections.observableList(getClinica().listarSuscripciones()) );

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
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String suscripcionElegida = comboBoxSuscripcion.getValue();

        Suscripcion suscripcion = null;

        if(suscripcionElegida.equals("Basica")) {
            suscripcion = new SuscripcionBasica();
        } else if (suscripcionElegida.equals("Premium")) {
            suscripcion = new SuscripcionPremium();
        }

        getClinica().registrarPaciente(new Paciente(identificacion, nombre, telefono, email, suscripcion));
        //

        //EnvioEmail.enviarNotificacion("cesarm.baquirom@uqvirtual.edu.co", "Prueba 1", "Hola " + nombre + " su registro fue exitoso!");
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
        if(getClinica() == null) {
            inicializarClinica(new Clinica());
        }
        comboBoxSuscripcion.setItems( FXCollections.observableList(getClinica().listarSuscripciones()) );
    }


}
