package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.ReservasUQ;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearReservaControlador extends VentanaObservable implements Initializable {

    @FXML
    private TableView<Horario> tablaHoraios;

    @FXML
    private TableColumn<Horario, String> colDia;

    @FXML
    private TableColumn<Horario, String> colHoraInicio;

    @FXML
    private TableColumn<Horario, String> colHoraFin;

    @FXML
    private TableColumn<Horario, String> colEstado;

    @FXML
    private ComboBox<String> comboBoxInstalacion;

    @FXML
    private DatePicker txtFecha;

    private ObservableList<Horario> horariosObservable;


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
        ArrayList<Horario> horariosPorDia = new ArrayList<>();

        String instalacionElegida = comboBoxInstalacion.getValue();
        LocalDate fechaElegida = txtFecha.getValue();

        horariosPorDia = controladorPrincipal.buscarHorariosInstalaciones(instalacionElegida, fechaElegida);
        horariosObservable.setAll(controladorPrincipal.buscarHorariosInstalaciones(instalacionElegida, fechaElegida));
        tablaHoraios.setItems(horariosObservable);
        System.out.println(horariosPorDia);


    }


    public void guardarReserva(ActionEvent actionEvent){
        try {

            //String cedula = txtCedula.getText();

            String instalacionElegida = comboBoxInstalacion.getValue();

            //controladorPrincipal.crearReserva();
        } catch (Exception e){
            controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }

    }
    //crearReserva
    //    observador.notificar();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cargar combo box
        comboBoxInstalacion.setItems( FXCollections.observableList(controladorPrincipal.listarInstalaciones()) );

        // Cargar tabla
        colDia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDia().toString()));
        colHoraInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraInicio()));
        colHoraFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoraFin()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOcupado().toString()));

        horariosObservable = FXCollections.observableArrayList();



    }

}
