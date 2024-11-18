package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Hotel;
import co.edu.uniquindio.bookyourstay.models.User;
import co.edu.uniquindio.bookyourstay.models.Session;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelHotelierController implements Initializable {

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelTipoUsuario;

    @FXML
    private TableView<Hotel> hotelsTable;

    @FXML
    private TableColumn<Hotel, String> colIdHotel;

    @FXML
    private TableColumn<Hotel, String> colHotelName;

    @FXML
    private TableColumn<Hotel, String> colLocation;

    private ObservableList<Hotel> observableHotels;


    private MainController mainController;
    private final Session session = Session.getInstancia();
    User user = session.getUser();

    public PanelHotelierController(){
        this.mainController = MainController.getInstancia();
    }

    public void goHome(ActionEvent actionEvent) {
        //mainController.closeWindow();
        mainController.navigateWindow("/home.fxml", "Inicio");
    }

    public void goCreateHotel(ActionEvent actionEvent) {
        mainController.navigateWindow("/createHotel.fxml", "Crear nuevo hotel");

    }

    public void cerrarSesion(ActionEvent actionEvent) {
        mainController.closeWindow(hotelsTable);
        session.cerrarSesion();
    }

    private void loadHotels() {
        observableHotels = FXCollections.observableArrayList(mainController.listHotelsByIdHotel(session.getUser().getMyHotelsId()));
        hotelsTable.setItems(observableHotels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Assign name and user type to Labels
        labelNombre.setText(user.getFullname());
        labelTipoUsuario.setText(user.getRole().toString());

        // Load table
        colIdHotel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdHotel()));
        colHotelName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        //colHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReservationHour().toString()));

        // Cargar las reservas en la tabla
        loadHotels();
    }
}
