package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private HBox boxAutenticado;

    @FXML
    private ComboBox<String> comboBoxHotel;

    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private ComboBox<String> comboBoxPrice;

    private ArrayList<Room> roomsFiltred = new ArrayList<>();

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

    public void goProfile(ActionEvent event) throws IOException {
        mainController.cerrarVentana(gridPane);
        mainController.navigateWindow("/panelClient.fxml", "Perfil");
    }

    @FXML
    public void logout() {
        mainController.cerrarVentana(gridPane);
        session.cerrarSesion();
    }

    // Lógica para sincronizar la propiedad con la lista de filtros
    public void cleanFilters(ActionEvent event) {
        comboBoxHotel.setValue(null);
        comboBoxCity.setValue(null);
        comboBoxPrice.setValue(null);
    }

// Llama a este método después de cualquier acción de filtrado


    public void search(ActionEvent event) throws IOException {
        ArrayList<Room> allRooms = mainController.getAllRooms();
        ArrayList<Room> roomsFilteredTemp = new ArrayList<>(); // Lista temporal para cada filtro

        String city = comboBoxCity.valueProperty().get();
        String hotel = comboBoxHotel.valueProperty().get();
        String price = comboBoxPrice.valueProperty().get();

        // Usar roomsFiltred si ya tiene resultados, de lo contrario, usar allRooms
        ArrayList<Room> filtreByList = roomsFiltred.isEmpty() ? allRooms : roomsFiltred;

        // Filtrar por ciudad
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

        // Filtrar por nombre del hotel
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

        // Filtrar por rango de precios
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

        // Actualizar la lista global de habitaciones filtradas
        roomsFiltred = filtreByList;

        // Actualizar la interfaz
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
        ArrayList<Room> rooms = new ArrayList<>();

        // Traer el usuario al cargar
        User user = session.getUser();
        // Verificar si el usuario está autenticado o no para mostrar un bloque de botones
        if (user == null) {
            // Si el usuario no está autenticado, mostrar los botones "Iniciar sesión" y "Registrarse"
            boxAnonimo.setVisible(true);
            boxAutenticado.setVisible(false);
        } else {
            // Si el usuario está autenticado, mostrar el botón "Cerrar sesión"
            boxAnonimo.setVisible(false);
            boxAutenticado.setVisible(true);
        }

        rooms = mainController.getAllRooms();

        llenarGridPaneConCards(rooms);
    }
}
