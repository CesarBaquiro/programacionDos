package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Hotel;
import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.Session;
import co.edu.uniquindio.bookyourstay.models.User;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateRoomController extends ObserverWindow implements Initializable {

    private final MainController mainController;
    private final Session session = Session.getInstancia();
    User user = session.getUser();

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> comboBoxHotels;

    @FXML
    private TextField roomCapacity;

    @FXML
    private TextField roomPrice;

    @FXML
    private TextArea txtDescription;

    private ObservableList<Hotel> observableHotels;

    public CreateRoomController(){
        this.mainController = MainController.getInstancia();
    }

    @Override
    public void setObservador(Observer observer) {

    }

    private void loadHotels() {
        observableHotels = FXCollections.observableArrayList(mainController.listHotelsByIdHotel(session.getUser().getMyHotelsId()));
    }

    public void createRoom(ActionEvent actionEvent) {
        String name = txtName.getText();
        //String description = txtDescription.getText();
        //String city = comboBoxHotels.getValue();
        //Integer capacity = Integer.parseInt(roomCapacity.getText());
        //Float price = Float.parseFloat(roomPrice.getText());

        //mainController.createRoom(name, description, city);
        //session.getUser().getMyHotelsId().add(mainController.getAllHotels().getLast().getIdHotel());
        mainController.showAlert("Se creo la habitación " + name,"Habitación creada", Alert.AlertType.INFORMATION);
        //observer.notificar();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableHotels = FXCollections.observableArrayList(mainController.listHotelsByIdHotel(session.getUser().getMyHotelsId()));
        ArrayList<String> hotels = new ArrayList<>();
        for (Hotel hotel : observableHotels) {
            hotels.add(hotel.getName());
        }
        comboBoxHotels.setItems(FXCollections.observableList(hotels));
    }
}
