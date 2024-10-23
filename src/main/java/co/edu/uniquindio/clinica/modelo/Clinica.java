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

    // Constructor privado para evitar la creación de nuevas instancias
    private Clinica(){
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        servicios = new ArrayList<>();

        servicios.add(new Servicio(10000.0, "SER-001", "Revision general"));
        servicios.add(new Servicio(1900000.0, "EST-001", "Rinoplastia"));
        servicios.add(new Servicio(1900000.0, "SER-002", "Excusas medicas"));
    }

    private static Clinica instance;

    // Método para obtener la única instancia de Clinica
    public static Clinica getInstanciaClinica() {
        if (instance == null) {
            instance = new Clinica();
            System.out.println("Clinica creada como: "+ instance.hashCode());
        }
        return instance;
    }

    public void registrarPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    private ArrayList<Servicio> getServiciosDisponibles(){
        return null;
    }

    private void registrarServicio(Servicio servicio){
        servicios.add(servicio);
    }

    public void registrarCita(Cita cita){
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

    /**
     * Lista las categorías disponibles
     * @return Lista de nombres de servivios
     */

    public ArrayList<String> listarNombreServicios(){
        ArrayList<String> nombres = new ArrayList<>();
        for (Servicio servicio : servicios) {
            nombres.add(servicio.getNombre());
        }
        return nombres;
    }
}
