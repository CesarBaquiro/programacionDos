package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.RoomSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomCardController {

    @FXML
    private ImageView roomImage;

    @FXML
    private Label roomName;

    @FXML
    private Label roomAccommodationName;

    @FXML
    private Label roomPrice;

    private Room room;

    private final MainController mainController;

    public RoomCardController() {
        this.mainController = MainController.getInstancia();
    }

    // Método que recibe los datos de la habitación y los asigna a la "card"
    public void setRoomData(Room room) {
        this.room = room;
        roomName.setText(room.getName());
        roomAccommodationName.setText(room.getHotel().getName());
        roomPrice.setText(String.valueOf(room.getPrice()));
        roomImage.setImage(new Image(room.getImages().get(0))); // Trae siempre la primer imagen en la lista
    }

    // Método que se llama cuando el usuario hace clic en "Ver más"
    @FXML
    public void handleSeeMore(ActionEvent actionEvent) {
        // Guardamos la habitación seleccionada en RoomSession
        RoomSession.getInstancia().setSelectedRoom(room);

        mainController.cerrarVentana(roomImage);
        mainController.navigateWindow("/roomDetails.fxml", "Detalles de la Habitación");
    }
}
