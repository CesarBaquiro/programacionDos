package co.edu.uniquindio.reservasuq.modelo;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class Reserva {
    String idInstalacion;
    String cedulaPersona;
    LocalDate diaReserva;
    String horaReserva;
}
