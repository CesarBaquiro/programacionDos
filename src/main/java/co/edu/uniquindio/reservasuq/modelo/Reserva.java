package co.edu.uniquindio.reservasuq.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Reserva {
    String nombreInstalacion;
    String cedulaPersona;
    LocalDate diaReserva;
    String horaReserva;
}
