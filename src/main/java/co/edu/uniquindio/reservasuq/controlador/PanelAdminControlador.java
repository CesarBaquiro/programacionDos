package co.edu.uniquindio.reservasuq.controlador;

import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.Sesion;

public class PanelAdminControlador {

    private final Sesion sesion = Sesion.getInstancia();
    Persona persona = sesion.getPersona();

}
