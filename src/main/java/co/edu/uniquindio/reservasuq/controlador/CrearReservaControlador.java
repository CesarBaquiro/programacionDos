package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearReservaControlador extends VentanaObservable implements Initializable {

    private final Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();

    @FXML
    private TableView<Horario> tablaHoraios;

    @FXML
    private TableColumn<Horario, String> colDia;

    @FXML
    private TableColumn<Horario, String> colHoraInicio;

    @FXML
    private TableColumn<Horario, String> colHoraFin;

    @FXML
    private TableColumn<Horario, String> colAforo;

    @FXML
    private ComboBox<String> comboBoxInstalacion;

    @FXML
    private DatePicker txtFecha;

    private ObservableList<Horario> horariosObservable;

    @FXML
    private TableColumn<Horario, Void> reservarBtn;

    private final ControladorPrincipal controladorPrincipal;

    public CrearReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    private Observador observador;

    @Override
    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    public void buscarHorarios(ActionEvent event) {
        String instalacionElegida = comboBoxInstalacion.getValue();
        LocalDate fechaElegida = txtFecha.getValue();
        horariosObservable.setAll(controladorPrincipal.buscarHorariosInstalaciones(instalacionElegida, fechaElegida));
        tablaHoraios.setItems(horariosObservable);
    }

    /**
     * Actualiza la lista observable de notas
     */
    public void actualizarHorarios() {
        String instalacionElegida = comboBoxInstalacion.getValue();
        LocalDate fechaElegida = txtFecha.getValue();
        horariosObservable.setAll(controladorPrincipal.buscarHorariosInstalaciones(instalacionElegida, fechaElegida));
        tablaHoraios.setItems(horariosObservable);
    }

    //crearReserva
    //    observador.notificar();

    private void addBtnReservar() {
        Callback<TableColumn<Horario, Void>, TableCell<Horario, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Horario, Void> call(final TableColumn<Horario, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Reservar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            String instalacionElegida = comboBoxInstalacion.getValue();
                            LocalDate fechaElegida = txtFecha.getValue();
                            Horario horario = getTableView().getItems().get(getIndex());
                            Boolean hayEspacio;

                            // Cambia el estado de la reserva
                            hayEspacio = controladorPrincipal.verificarAforoPorHora(instalacionElegida, horario.getHoraInicio());
                            if (!hayEspacio){
                                horario.setOcupado(true);
                            }

                            // Registrar la reserva
                            try {
                                if (hayEspacio){
                                    controladorPrincipal.crearReserva(instalacionElegida, sesion.getPersona().getCedula(), fechaElegida, horario.getHoraInicio());
                                    // Notificar a los observadores para actualizar la vista de reservas
                                    observador.notificar();
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            // Refresca la tabla actualizando el ObservableList
                            horariosObservable.set(getIndex(), horario);
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
                            Horario horario = getTableView().getItems().get(getIndex());
                            if (horario.getOcupado()) {
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
        comboBoxInstalacion.setItems( FXCollections.observableList(controladorPrincipal.listarInstalaciones()) );

        // Cargar tabla
        colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDia().toString()));
        colHoraInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio()));
        colHoraFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin()));
        // Mostrar el aforo de la instalación
        colAforo.setCellValueFactory(cellData -> {
            Instalacion instalacion = controladorPrincipal.obtenerInstalacionPorNombre(comboBoxInstalacion.getValue());
            return new SimpleStringProperty(controladorPrincipal.contarReservasPorInstalacionHora(comboBoxInstalacion.getValue(),cellData.getValue().getHoraInicio()) + "/" + String.valueOf(instalacion.getAforo()));
        });

        horariosObservable = FXCollections.observableArrayList();
    }

}
