package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.RoomSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RoomDetailsController {

    @FXML
    private Label roomName;

    @FXML
    private Label roomDescription;



    @FXML
    private Label roomPrice;

    // Método que se llama para mostrar los detalles de la habitación
    public void initialize() {
        // Obtener la habitación seleccionada desde RoomSession
        Room selectedRoom = RoomSession.getInstancia().getSelectedRoom();

        if (selectedRoom != null) {
            roomName.setText(selectedRoom.getName());
            roomDescription.setText(selectedRoom.getDescription());
            roomPrice.setText(String.valueOf(selectedRoom.getPrice()));
        }
    }
}
