package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateHotelController extends ObserverWindow implements Initializable {

    private final Session session = Session.getInstancia();
    User user = session.getUser();

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ComboBox<String> comboBoxCities;
    
    private final MainController mainController;

    public CreateHotelController() {
        this.mainController = MainController.getInstancia();
    }

    private Observer observer;

    @Override
    public void setObservador(Observer observer) {
        this.observer = observer;
    }

    public void createHotel(ActionEvent actionEvent) {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String city = comboBoxCities.getValue();
        ArrayList<Room> rooms = new ArrayList<>();

        mainController.createHotel(name, description, city, rooms);
        session.getUser().getMyHotelsId().add(mainController.getAllHotels().getLast().getIdHotel());
        mainController.showAlert("Se guardo el hotel " + name,"Hotel creado", Alert.AlertType.INFORMATION);
        observer.notificar();
    }

    public void createRoom(ActionEvent actionEvent) {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String city = comboBoxCities.getValue();
        ArrayList<Room> rooms = new ArrayList<>();

        mainController.createHotel(name, description, city, rooms);
        session.getUser().getMyHotelsId().add(mainController.getAllHotels().getLast().getIdHotel());
        mainController.showAlert("Se guardo el hotel " + name,"Hotel creado", Alert.AlertType.INFORMATION);
        observer.notificar();
        // Terminar la creacion de habitaciones
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load combo box
        comboBoxCities.setItems( FXCollections.observableList(mainController.listCities()));
    }

}
