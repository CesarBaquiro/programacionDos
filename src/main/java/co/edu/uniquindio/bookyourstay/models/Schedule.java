package co.edu.uniquindio.bookyourstay.models;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Schedule {
    LocalDate dia;
    String horaInicio;
    String horaFin;
    Boolean ocupado;
}
