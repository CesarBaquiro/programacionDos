package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private final MainController mainController;

    private final Session session = Session.getInstancia();

    @FXML
    private HBox boxAnonimo;

    @FXML
    private HBox boxAutenticado;

    @FXML
    private GridPane gridPane; // Asegúrate de tener el GridPane en tu archivo FXML con fx:id="gridPane"

    public HomeController() {
        this.mainController = MainController.getInstancia();
    }

    public void goLogin(ActionEvent event) throws IOException {

        mainController.cerrarVentana(gridPane);
        mainController.navigateWindow("/login.fxml", "Iniciar sesión");
    }

    public void goRegister(ActionEvent event) throws IOException {
        mainController.cerrarVentana(gridPane);
        mainController.navigateWindow("/register.fxml", "Registrarse");
    }

    @FXML
    public void logout() {
        mainController.cerrarVentana(gridPane);
        session.cerrarSesion();
    }

    // Método que llena el GridPane con "cards" dinámicas basadas en una lista de habitaciones
    private void llenarGridPaneConCards(List<Room> rooms) {

        gridPane.getChildren().clear();

        if (rooms.isEmpty()) {
            try {
                // Cargar la vista de la card vacía desde el archivo FXML
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/emptyCard.fxml"));
                Node emptyCardNode = fxmlLoader.load();

                // Añadir la card vacía al GridPane
                gridPane.add(emptyCardNode, 0, 0);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return; // Salir del método ya que no hay habitaciones
        }

        int elementosPorFila = 2; // Número de elementos por fila
        int row = 0, col = 0;

        for (Room room : rooms) {
            try {
                // Cargar la vista de la card desde el archivo FXML
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/roomCard.fxml"));
                Node cardNode = fxmlLoader.load();  // Carga el archivo FXML

// Obtén el controlador para poder configurarlo
                RoomCardController cardController = fxmlLoader.getController();
                if (cardController != null) {
                    cardController.setRoomData(room);  // Asegúrate de que no sea nulo
                }

                // Añadir la card al GridPane
                gridPane.add(cardNode, col, row);

                col++;
                if (col == elementosPorFila) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Traer el usuario al cargar
        User user = session.getUser();
        // Verificar si el usuario está autenticado o no
        if (user == null) {
            // Si el usuario no está autenticado, mostrar los botones "Iniciar sesión" y "Registrarse"
            boxAnonimo.setVisible(true);
            boxAutenticado.setVisible(false);
        } else {
            // Si el usuario está autenticado, mostrar el botón "Cerrar sesión"
            boxAnonimo.setVisible(false);
            boxAutenticado.setVisible(true);
        }

        // Datos de prueba
        ArrayList<Schedule> schedulesMocawa = new ArrayList<Schedule>();
        schedulesMocawa.add(new Schedule(LocalDate.parse("2025-02-28"), "10PM", "9AM", false));
        // Crear una lista de habitaciones de ejemplo
        List<Room> rooms = List.of(
                new Room("Habitación 1", 3,"Cama simple", 180000,"Descripción de la Habitación 1", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), schedulesMocawa),
                new Room("Habitacion presidencial", 6,"Cama doble", 490000,"Descripción de la Habitación 2", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), schedulesMocawa)
        );

        llenarGridPaneConCards(rooms);
    }
}
