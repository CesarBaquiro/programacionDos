package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.RoomSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RoomCardController {

    @FXML
    private ImageView roomImage;

    @FXML
    private Label roomName;

    @FXML
    private Label roomDescription;

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
        roomDescription.setText(room.getDescription());
        roomPrice.setText(String.valueOf(room.getPrice()));
        // roomImage.setImage(new Image(room.getImage()));
    }

    // Método que se llama cuando el usuario hace clic en "Ver más"
    @FXML
    public void handleSeeMore(ActionEvent actionEvent) {
        // Guardamos la habitación seleccionada en RoomSession
        RoomSession.getInstancia().setSelectedRoom(room);

        // Navegar a la vista de detalles de la habitación
        // Esto puede ser similar a la lógica de navegación en tu código
        mainController.navigateWindow("/roomDetails.fxml", "Detalles de la Habitación");
    }
}
