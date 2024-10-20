package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter

public class Clinica {
    ArrayList<Paciente> pacientes;
    ArrayList<Cita> citas;
    ArrayList<Servicio> servicios;

    public Clinica(){
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        servicios = new ArrayList<>();
    }

    private void registrarPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    private ArrayList<Servicio> getServiciosDisponibles(){
        return null;
    }

    private void registrarServicio(Servicio servicio){
        servicios.add(servicio);
    }

    private void registrarCita(Cita cita){
        citas.add(cita);
    }

    private Factura generarFactura(Paciente paciente, Servicio servicio){
        return null;
    }

    private void getServiciosDisponibles(Suscripcion suscripcion){
        suscripcion.getServiciosDisponibles();
    }


    /**
     * Lista las categorías disponibles
     * @return Lista de categorías
     */
    public ArrayList<String> listarSuscripciones() {
        ArrayList<String> suscripciones = new ArrayList<>();
        suscripciones.add("Basica");
        suscripciones.add("Premium");

        return suscripciones;
    }

}
