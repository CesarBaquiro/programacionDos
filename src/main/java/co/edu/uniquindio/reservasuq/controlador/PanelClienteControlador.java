package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.observador.Observador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelClienteControlador implements Observador, Initializable {
    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();
    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    private TableView<Reserva>  tablaReservas;

    @FXML
    private TableColumn<Reserva, String> colInstalacion;

    @FXML
    private TableColumn<Reserva, String> colFecha;

    @FXML
    private TableColumn<Reserva, String> colHora;

    private ObservableList<Reserva> reservasObservable;

    public void cerrarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana(tablaReservas);
        sesion.cerrarSesion();
    }

    public void crearReserva(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentanaObservable("/crearReserva.fxml", "Crear Reserva", this);
    }

    private void cargarReservas() {
        reservasObservable = FXCollections.observableArrayList(controladorPrincipal.listarReservasPorPersona(sesion.getPersona().getCedula()));
        tablaReservas.setItems(reservasObservable);  // Vincula las reservas con la tabla
    }

    @Override
    public void notificar() {
        reservasObservable.setAll(controladorPrincipal.listarReservasPorPersona(persona.getCedula()));
        tablaReservas.refresh();  // Refrescar la tabla para mostrar cambios
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Cargar tabla
        colInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreInstalacion()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaReserva().toString()));
        colHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraReserva().toString()));

        // Cargar las reservas en la tabla
        cargarReservas();
    }
}
