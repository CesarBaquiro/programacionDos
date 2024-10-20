package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.ArrayList;

public interface Suscripcion {
    ArrayList<Servicio> getServiciosDisponibles();
    Factura generarFacturaCobro(Servicio servicio);
}
