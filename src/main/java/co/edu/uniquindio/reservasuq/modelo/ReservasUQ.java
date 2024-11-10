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

    /**
     * Comprobar si hay espacios disponibles y retornar True si los hay
     * */
    public Boolean verificarAforoPorHora(String nombreInstalacion, String hora) {
        Boolean hayEspacio = false;
        int cantidadReservas = 0;
        cantidadReservas = contarReservasPorInstalacionHora(nombreInstalacion, hora);
        for (Instalacion i: instalaciones){
            if(nombreInstalacion.equals(i.getNombre())){
                for (Reserva r : reservas) {
                    if(nombreInstalacion.equals(r.getNombreInstalacion()) && cantidadReservas < i.getAforo()){
                        hayEspacio = true;
                    }
                }
            }
        }
        return hayEspacio;
    }

    public int contarReservasPorInstalacionHora(String nombreInstalacion, String hora) {
        int cantidadReservas = 0;
        for (Reserva r : reservas) {
            if(r.getNombreInstalacion().equals(nombreInstalacion) && hora.equals(r.getHoraReserva())){
                cantidadReservas++;
            }
        }
        return cantidadReservas;
    }

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
    public void crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        reservas.add(new Reserva(idInstalacion, cedulaPersona, diaReserva, horaReserva));

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
