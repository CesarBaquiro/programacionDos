package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.observador.Observador;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PanelClienteControlador implements Observador {
    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();
    public PanelClienteControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colInstalacion;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colHora;

    private ObservableList<Reserva> observableList;



    public void cerrarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana(tablaReservas);
        sesion.cerrarSesion();
    }

    public void crearReserva(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentanaObservable("/crearReserva.fxml", "Crear Reserva", this);
    }


    @Override
    public void notificar() {
        observableList.setAll(controladorPrincipal.listarReservasPorPersona(persona.getCedula()));
    }
}
