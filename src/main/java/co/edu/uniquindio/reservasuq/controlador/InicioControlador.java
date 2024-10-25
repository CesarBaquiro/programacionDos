package co.edu.uniquindio.reservasuq.controlador;


import javafx.event.ActionEvent;


public class InicioControlador {


    private final ControladorPrincipal controladorPrincipal;


    public InicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/login.fxml","Iniciar Sesión");
    }


    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/registro.fxml", "Registro Persona");
    }
}
