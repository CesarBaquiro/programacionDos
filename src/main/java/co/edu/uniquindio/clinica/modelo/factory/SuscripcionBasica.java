package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.ArrayList;

public class SuscripcionBasica implements Suscripcion{
    @Override
    public ArrayList<Servicio> getServiciosDisponibles(){
        return null;
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio){
        return null;
    }
}
