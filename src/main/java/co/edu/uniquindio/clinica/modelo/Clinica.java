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
@AllArgsConstructor
@NoArgsConstructor
public class Clinica {
    ArrayList<Paciente> pacientes;
    ArrayList<Cita> citas;
    ArrayList<Servicio> servicios;

    private void registrarPaciente(Paciente paciente){}

    private ArrayList<Servicio> getServiciosDisponibles(){
        return null;
    }

    private void registrarServicio(Servicio servicio){}

    private void registrarCita(Cita cita){}

    private Factura generarFactura(Paciente paciente, Servicio servicio){
        return null;
    }

    private void getServiciosDisponibles(Suscripcion suscripcion){}

}
