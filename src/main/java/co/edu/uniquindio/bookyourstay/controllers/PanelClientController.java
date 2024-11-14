package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelClientController implements Observer, Initializable {
    private final MainController mainController;
    private final Session session = Session.getInstancia();
    User user = session.getUser();
    public PanelClientController() {
        this.mainController = MainController.getInstancia();
    }

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelTipoUsuario;

    @FXML
    private TableView<Reservation>  tablaReservas;

    @FXML
    private TableColumn<Reservation, String> colInstalacion;

    @FXML
    private TableColumn<Reservation, String> colFecha;

    @FXML
    private TableColumn<Reservation, String> colHora;

    private ObservableList<Reservation> reservasObservable;

    public void cerrarSesion(ActionEvent actionEvent) {
        mainController.cerrarVentana(tablaReservas);
        session.cerrarSesion();
    }

    public void goHome(ActionEvent actionEvent) {
        mainController.navigateWindow("/home.fxml", "Inicio");
    }

    private void cargarReservas() {
        reservasObservable = FXCollections.observableArrayList(mainController.listReservationsByUser(session.getUser().getIDdocumentation()));
        tablaReservas.setItems(reservasObservable);  // Vincula las reservas con la tabla
    }

    @Override
    public void notificar() {
        reservasObservable.setAll(mainController.listReservationsByUser(user.getIDdocumentation()));
        tablaReservas.refresh();  // Refrescar la tabla para mostrar cambios
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignar name y tipo de usuario a los Label
        labelNombre.setText(user.getFullname());
        labelTipoUsuario.setText(user.getRole().toString());

        // Cargar tabla
        colInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdReservation()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdRoom()));
        //colHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReservationHour().toString()));

        // Cargar las reservas en la tabla
        cargarReservas();
    }
}
