package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PanelClienteControlador {
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



    public void cerrarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana(tablaReservas);
        sesion.cerrarSesion();
    }
}
