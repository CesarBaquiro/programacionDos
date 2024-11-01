package co.edu.uniquindio.reservasuq.modelo;


import co.edu.uniquindio.reservasuq.controlador.ControladorPrincipal;
import lombok.Getter;
import lombok.Setter;


public class Sesion {


    public static Sesion INSTANCIA;

    @Getter @Setter
    private Persona persona;
    private final ControladorPrincipal controladorPrincipal;

    private Sesion() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }


    public void cerrarSesion() {
        persona = null;
        controladorPrincipal.navegarVentana("/login.fxml", "Iniciar secion");
    }


}
