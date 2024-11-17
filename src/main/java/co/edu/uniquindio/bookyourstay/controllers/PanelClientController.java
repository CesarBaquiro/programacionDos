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
    private TableView<Reservation> reservationsTable;

    @FXML
    private TableColumn<Reservation, String> colIdHotel;

    @FXML
    private TableColumn<Reservation, String> colRoomName;

    @FXML
    private TableColumn<Reservation, String> colInitDate;

    @FXML
    private TableColumn<Reservation, String> colEndDate;

    @FXML
    private TableColumn<Reservation, String> colNightsReserved;

    private ObservableList<Reservation> observableReserves;

    public void cerrarSesion(ActionEvent actionEvent) {
        mainController.cerrarVentana(reservationsTable);
        session.cerrarSesion();
    }

    public void goHome(ActionEvent actionEvent) {
        mainController.cerrarVentana(reservationsTable);
        mainController.navigateWindow("/home.fxml", "Inicio");
    }

    private void loadReservations() {
        observableReserves = FXCollections.observableArrayList(mainController.listReservationsByUser(session.getUser().getIDdocumentation()));
        reservationsTable.setItems(observableReserves);  // Vincula las reservas con la tabla
    }

    @Override
    public void notificar() {
        observableReserves.setAll(mainController.listReservationsByUser(user.getIDdocumentation()));
        reservationsTable.refresh();  // Refrescar la tabla para mostrar cambios
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignar name y tipo de usuario a los Label
        labelNombre.setText(user.getFullname());
        labelTipoUsuario.setText(user.getRole().toString());

        // Cargar tabla
        colIdHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHotel().getName()));
        colRoomName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoom().getName()));
        colInitDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartDate().toString()));
        colEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndDate().toString()));
        colNightsReserved.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumberNightsReserved())));

        //colHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReservationHour().toString()));

        // Cargar las reservas en la tabla
        loadReservations();
    }
}
