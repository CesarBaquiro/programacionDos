package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.models.enums.Role;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private final MainController mainController;

    private final Session session = Session.getInstancia();

    @FXML
    private HBox boxAnonimo;

    @FXML
    private HBox boxUserButtons;

    @FXML
    private HBox boxHotelierButtons;

    @FXML
    private ComboBox<String> comboBoxHotel;

    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private ComboBox<String> comboBoxPrice;

    private ArrayList<Room> roomsFiltred = new ArrayList<>();

    @FXML
    private GridPane gridPane;

    public HomeController() {
        this.mainController = MainController.getInstancia();
    }

    public void goLogin(ActionEvent event) throws IOException {

        mainController.closeWindow(gridPane);
        mainController.navigateWindow("/login.fxml", "Iniciar sesión");
    }

    public void goRegister(ActionEvent event) throws IOException {
        mainController.closeWindow(gridPane);
        mainController.navigateWindow("/register.fxml", "Registrarse");
    }

    public void goProfile(ActionEvent event) throws IOException {
        mainController.closeWindow(gridPane);
        mainController.navigateWindow("/panelClient.fxml", "Perfil");
    }

    public void goHotelsManager(ActionEvent event) throws IOException {
        mainController.closeWindow(gridPane);
        mainController.navigateWindow("/panelHotelier.fxml", "Administrador de hoteles");
    }

    @FXML
    public void logout() {
        mainController.closeWindow(gridPane);
        session.cerrarSesion();
    }

    // Logic to sync property with filter list
    public void cleanFilters(ActionEvent event) {
        comboBoxHotel.setValue(null);
        comboBoxCity.setValue(null);
        comboBoxPrice.setValue(null);
    }

    // Call this method after any filter action

    public void search(ActionEvent event) throws IOException {
        ArrayList<Room> allRooms = mainController.getAllRooms();
        ArrayList<Room> roomsFilteredTemp = new ArrayList<>(); // Lista temporal para cada filtro

        String city = comboBoxCity.valueProperty().get();
        String hotel = comboBoxHotel.valueProperty().get();
        String price = comboBoxPrice.valueProperty().get();

        // Use roomsFiltred if you already have results, otherwise use allRooms
        ArrayList<Room> filtreByList = roomsFiltred.isEmpty() ? allRooms : roomsFiltred;

        // Filter by city
        if (city != null) {
            for (Room r : filtreByList) {
                if (r.getHotel().getLocation().equals(city)) {
                    if (!roomsFilteredTemp.contains(r)) {
                        roomsFilteredTemp.add(r);
                    }
                }
            }
            filtreByList = new ArrayList<>(roomsFilteredTemp);
            roomsFilteredTemp.clear();
        }

        // Filter by hotel name
        if (hotel != null) {
            for (Room r : filtreByList) {
                if (r.getHotel().getName().equals(hotel)) {
                    if (!roomsFilteredTemp.contains(r)) {
                        roomsFilteredTemp.add(r);
                    }
                }
            }
            filtreByList = new ArrayList<>(roomsFilteredTemp);
            roomsFilteredTemp.clear();
        }

        // Filter by price range
        if (price != null) {
            for (Room r : filtreByList) {
                boolean matches = switch (price) {
                    case "Menos de $80.0000" -> r.getPrice() < 80000;
                    case "$80000 - $100000" -> r.getPrice() >= 80000 && r.getPrice() < 100000;
                    case "$100000 - $150000" -> r.getPrice() >= 100000 && r.getPrice() <= 150000;
                    case "Más de $150000" -> r.getPrice() > 150000;
                    default -> false;
                };
                if (matches) {
                    if (!roomsFilteredTemp.contains(r)) {
                        roomsFilteredTemp.add(r);
                    }
                }
            }
            filtreByList = new ArrayList<>(roomsFilteredTemp);
            roomsFilteredTemp.clear();
        }

        // Update the global list of filtered rooms
        roomsFiltred = filtreByList;

        // Update the interface
        if (hotel == null && price == null && city == null) {
            llenarGridPaneConCards(allRooms);
        } else {
            llenarGridPaneConCards(roomsFiltred);
        }
    }


    // Method that fills the GridPane with dynamic cards based on a list of rooms
    private void llenarGridPaneConCards(List<Room> rooms) {
        gridPane.getChildren().clear();

        comboBoxCity.setItems(FXCollections.observableList(mainController.listCities()));
        comboBoxHotel.setItems(FXCollections.observableList(mainController.listHotels()));
        comboBoxPrice.setItems(FXCollections.observableList(mainController.listRangesPrices()));

        if (rooms.isEmpty()) {
            try {
                // Load empty card view from FXML file
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/emptyCard.fxml"));
                Node emptyCardNode = fxmlLoader.load();

                // Añadir la card vacía al GridPane
                gridPane.add(emptyCardNode, 0, 0);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return; // Exit method since there are no rooms
        }

        int elementosPorFila = 3; // Number of elements per row
        int row = 0, col = 0;

        for (Room room : rooms) {
            try {
                // Load card view from FXML file
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
        ArrayList<Room> rooms;

        // Bring user on load
        User user = session.getUser();
        // Check if the user is authenticated or not to display a button block
        if (user == null) {
        // If the user is not authenticated, show the "Login" and "Register" buttons
            boxAnonimo.setVisible(true);
            boxHotelierButtons.setVisible(false);
            boxUserButtons.setVisible(false);
        } else if (user.getRole() == Role.HOTELIER) {
            // If the user is authenticated, show the "Logout" button
            boxAnonimo.setVisible(false);
            boxHotelierButtons.setVisible(true);
            boxUserButtons.setVisible(false);
        } else {
            // If the user is authenticated, show the "Logout" button
            boxAnonimo.setVisible(false);
            boxHotelierButtons.setVisible(false);
            boxUserButtons.setVisible(true);
        }
        rooms = mainController.getAllRooms();
        llenarGridPaneConCards(rooms);
    }
}
