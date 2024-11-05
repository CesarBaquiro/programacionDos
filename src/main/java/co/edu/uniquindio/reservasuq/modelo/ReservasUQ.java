package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservasUQ implements ServiciosReservasUQ {

    ArrayList<Reserva> reservas =   new ArrayList();
    ArrayList<Persona> personas =   new ArrayList();
    ArrayList<Instalacion> instalaciones =   new ArrayList();


    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        for (Persona persona : personas) {
            if(persona.getEmail().equals(correo) && persona.getPassword().equals(contrasena)){
                return persona;
            }
        }
        return null;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        personas.add(new Persona(cedula, nombre, tipoPersona, email, password));
    }

    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        instalaciones.add(new Instalacion(nombre, aforo, costo, horarios));
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        reservas.add(new Reserva(idInstalacion, cedulaPersona, diaReserva, horaReserva));
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }
}
