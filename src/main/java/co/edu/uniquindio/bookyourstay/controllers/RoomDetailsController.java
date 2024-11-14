package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.RoomSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RoomDetailsController {

    @FXML
    private Label hotelName;

    @FXML
    private Label roomTitle;

    @FXML
    private Label hotelLocation;

    @FXML
    private Label roomPrice;

    @FXML
    private Label roomDescription;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private Button reserveButton;

    @FXML
    private VBox rootVBox;

    public void initialize() {
        loadRoomData();
    }

    // Cargar los datos de la habitación seleccionada desde la sesión
    private void loadRoomData() {
        Room room = RoomSession.getInstancia().getSelectedRoom(); // Obtenemos la habitación seleccionada desde la sesión

        if (room != null) {
            // Asignar los datos a los elementos de la interfaz
            hotelName.setText(room.getAccommodationByIdAccommodation().getName());
            roomTitle.setText(room.getName());
            hotelLocation.setText(room.getAccommodationByIdAccommodation().getLocation());
            //roomLocation.setText(room.getLocation());
            roomPrice.setText(String.valueOf(room.getPrice()));
            roomDescription.setText(room.getDescription());

            // Cargar las imágenes
            if (room.getImages() != null && room.getImages().size() >= 4) {
                image1.setImage(new Image(room.getImages().get(0)));
                image2.setImage(new Image(room.getImages().get(1)));
                image3.setImage(new Image(room.getImages().get(2)));
                image4.setImage(new Image(room.getImages().get(3)));
            }

            // Asignar acción al botón de reserva
            reserveButton.setOnAction(e -> handleReservation(room));
        }
    }

    private void handleReservation(Room room) {
        // Lógica para manejar la reserva de la habitación
        System.out.println("Reservando habitación: " + room.getName());

        // Aquí podrías navegar a una pantalla de confirmación de reserva
    }
}
