package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
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
    private Label roomDescription;

    // Método que recibe los datos de la habitación y los asigna a la "card"
    public void setRoomData(Room room) {
        roomName.setText(room.getName());
        roomDescription.setText(room.getDescription());
        //roomImage.setImage(new Image(room.getImage()));
    }
}
