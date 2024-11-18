package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.RoomSession;
import co.edu.uniquindio.bookyourstay.models.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

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
    private Label msgErrorLbl1;

    @FXML
    private Label msgErrorLbl2;

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
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private MainController mainController;

    private final Session session = Session.getInstancia();

    public RoomDetailsController() {
        this.mainController = MainController.getInstancia();
    }

    public void initialize() {
        loadRoomData();
    }

    public void goHome(ActionEvent actionEvent) {
        mainController.cerrarVentana(msgErrorLbl1);
        mainController.navigateWindow("/home.fxml", "Inicio");
    }

    // Cargar los datos de la habitación seleccionada desde la sesión
    private void loadRoomData() {
        Room room = RoomSession.getInstancia().getSelectedRoom(); // Obtenemos la habitación seleccionada desde la sesión

        if (room != null) {
            // Asignar los datos a los elementos de la interfaz
            hotelName.setText(room.getHotel().getName());
            roomTitle.setText(room.getName());
            hotelLocation.setText(room.getHotel().getLocation());
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
        Boolean save = true;

        LocalDate initDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (session.getUser() == null) {
            save = false;
            mainController.showAlert("Por favor primero inicie sesión", "Queremos saber quien eres!", Alert.AlertType.WARNING);
        }

        if(initDate == null) {
            save = false;
            msgErrorLbl1.setText("Por favor ingrese la fecha de inicio");
        }else {
            msgErrorLbl1.setText("");
        }

        if(endDate == null) {
            save = false;
            msgErrorLbl2.setText("Por favor ingrese la fecha de fin");
        }else {
            msgErrorLbl2.setText("");
        }

        // Conditional to check if endDate is a day after initDate
        if (endDate != null && initDate != null) {
            if (!endDate.isAfter(initDate)) {
                save = false;
                msgErrorLbl2.setText("La fecha de fin debe ser mayor");
            }
        }
        if(save && session.getUser() != null) {
            // Logic to handle room reservation
            try {
                mainController.createReservation(room.getHotel().getIdHotel(), room.getIdRoom(),session.getUser().getIDdocumentation(), initDate,endDate);
                mainController.showAlert("La reserva se creo exitosamente", "Reserva guardada", Alert.AlertType.CONFIRMATION);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        mainController.cerrarVentana(image1);
        mainController.navigateWindow("/home.fxml", "Inicio");
    }
}
