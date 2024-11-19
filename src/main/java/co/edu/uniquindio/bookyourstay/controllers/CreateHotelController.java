package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateHotelController extends ObserverWindow implements Initializable {

    private final Session session = Session.getInstancia();
    User user = session.getUser();

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ComboBox<String> comboBoxCities;
    
    private final MainController mainController;

    public CreateHotelController() {
        this.mainController = MainController.getInstancia();
    }

    private Observer observer;

    @Override
    public void setObservador(Observer observer) {
        this.observer = observer;
    }

    public void createHotel(ActionEvent actionEvent) {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String city = comboBoxCities.getValue();
        ArrayList<Room> rooms = new ArrayList<>();

        mainController.createHotel(name, description, city, rooms);
        mainController.showAlert("Se guardo el hotel " + name,"Hotel creado", Alert.AlertType.INFORMATION);



    }

/**
    private void addBtnReservar() {
        Callback<TableColumn<Hotel, Void>, TableCell<Hotel, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Hotel, Void> call(final TableColumn<Hotel, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Reservar");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            String instalacionElegida = comboBoxInstalacion.getValue();
                           // LocalDate startDate = startDatePicker.getValue();
                            //LocalDate endDate = endDatePicker.getValue();
                            Hotel hotel = getTableView().getItems().get(getIndex());
                            Boolean hayEspacio;
                            String idAccomodation = "";
                            String idRoom = "";




                            // Refresca la tabla actualizando el ObservableList
                            hotelObservable.set(getIndex(), hotel);
                            tableHotel.refresh(); // Refrescar la tabla para mostrar cambios

                            // Cambia el estado del botón
                            btn.setDisable(true);
                            btn.setText("Ocupado");
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Hotel hotel = getTableView().getItems().get(getIndex());
                            if (hotel.ge) {
                                btn.setDisable(true);
                                btn.setText("Ocupado");
                            } else {
                                btn.setDisable(false);
                                btn.setText("Reservar");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        reservarBtn.setCellFactory(cellFactory);
    }



 */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//       // addBtnReservar();
        // Cargar combo box
        comboBoxCities.setItems( FXCollections.observableList(mainController.listCities()) );
//
//        // Cargar tabla
//        colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDia().toString()));
//        colHoraInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio()));
//        colHoraFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin()));
//        // Mostrar el aforo de la instalación
////        colAforo.setCellValueFactory(cellData -> {
////            Accommodation accommodations = mainController.obtenerInstalacionPorNombre(comboBoxInstalacion.getValue());
////            return new SimpleStringProperty(mainController.contarReservasPorInstalacionHora(comboBoxInstalacion.getValue(),cellData.getValue().getHoraInicio()) + "/" + String.valueOf(accommodations.getAforo()));
////        });
//
//        horariosObservable = FXCollections.observableArrayList();
    }

}
