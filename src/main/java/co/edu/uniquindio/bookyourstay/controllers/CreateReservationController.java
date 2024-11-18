package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateReservationController extends ObserverWindow implements Initializable {

    private final Session session = Session.getInstancia();
    User user = session.getUser();

    @FXML
    private TableView<Schedule> tablaHoraios;

    @FXML
    private TableColumn<Schedule, String> colDia;

    @FXML
    private TableColumn<Schedule, String> colHoraInicio;

    @FXML
    private TableColumn<Schedule, String> colHoraFin;

    @FXML
    private ComboBox<String> comboBoxInstalacion;



    private ObservableList<Schedule> horariosObservable;

    @FXML
    private TableColumn<Schedule, Void> reservarBtn;

    private final MainController mainController;

    public CreateReservationController() {
        this.mainController = MainController.getInstancia();
    }

    private Observer observer;

    @Override
    public void setObservador(Observer observer) {
        this.observer = observer;
    }

    /**
    public void buscarHorarios(ActionEvent event) {
        String instalacionElegida = comboBoxInstalacion.getValue();
        LocalDate fechaElegida = startDatePicker.getValue();
        horariosObservable.setAll(mainController.searchRoomBySchedules(instalacionElegida, fechaElegida));
        tablaHoraios.setItems(horariosObservable);
    }

     * Actualiza la lista observable de notas
    public void actualizarHorarios() {
        String instalacionElegida = comboBoxInstalacion.getValue();
        LocalDate fechaElegida = startDatePicker.getValue();
        horariosObservable.setAll(mainController.searchRoomBySchedules(instalacionElegida, fechaElegida));
        tablaHoraios.setItems(horariosObservable);
    }
     */

    private void addBtnReservar() {
        Callback<TableColumn<Schedule, Void>, TableCell<Schedule, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Schedule, Void> call(final TableColumn<Schedule, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Reservar");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            String instalacionElegida = comboBoxInstalacion.getValue();
                           // LocalDate startDate = startDatePicker.getValue();
                            //LocalDate endDate = endDatePicker.getValue();
                            Schedule schedule = getTableView().getItems().get(getIndex());
                            Boolean hayEspacio;
                            String idAccomodation = "";
                            String idRoom = "";

                            // Cambia el estado de la reserva
                            hayEspacio = mainController.verificarAforoPorHora(instalacionElegida, schedule.getHoraInicio());
                            if (!hayEspacio){
                                schedule.setOcupado(true);
                            }

                            // Registrar la reserva
                            try {
                                if (hayEspacio){
                                   // mainController.createReservation(instalacionElegida, session.getUser().getIDdocumentation(), idAccomodation, idRoom, startDate, endDate);
                                    // Notificar a los observadores para actualizar la vista de reservas
                                    observer.notificar();
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            // Refresca la tabla actualizando el ObservableList
                            horariosObservable.set(getIndex(), schedule);
                            tablaHoraios.refresh(); // Refrescar la tabla para mostrar cambios

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
                            Schedule schedule = getTableView().getItems().get(getIndex());
                            if (schedule.getOcupado()) {
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtnReservar();
        // Cargar combo box
        comboBoxInstalacion.setItems( FXCollections.observableList(mainController.listHotels()) );

        // Cargar tabla
        colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDia().toString()));
        colHoraInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio()));
        colHoraFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin()));
        // Mostrar el aforo de la instalación
//        colAforo.setCellValueFactory(cellData -> {
//            Accommodation accommodations = mainController.obtenerInstalacionPorNombre(comboBoxInstalacion.getValue());
//            return new SimpleStringProperty(mainController.contarReservasPorInstalacionHora(comboBoxInstalacion.getValue(),cellData.getValue().getHoraInicio()) + "/" + String.valueOf(accommodations.getAforo()));
//        });

        horariosObservable = FXCollections.observableArrayList();
    }

}
