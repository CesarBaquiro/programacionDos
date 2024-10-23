package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaCitasControlador extends AbstractControlador implements Initializable {
    Clinica clinica = Clinica.getInstanciaClinica();

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colServicio;

    @FXML
    private TableColumn<Cita, String> colNombre;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colHora;

    private ObservableList<Cita> citasObservable;

    private void cargarCitas() {
        citasObservable.setAll(clinica.getCitas());
        tablaCitas.setItems(citasObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Asignar las propiedades de la nota a las columnas de la tabla
        colServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getIdServicio()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHora()));
        //colSuscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getSuscripcion().toString()));

        citasObservable = FXCollections.observableArrayList();
        cargarCitas();

    }
}
