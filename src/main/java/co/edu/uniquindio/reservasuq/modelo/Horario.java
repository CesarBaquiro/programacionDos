package co.edu.uniquindio.reservasuq.modelo;

import javafx.scene.control.Dialog;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Horario {
    LocalDate dia;
    String horaInicio;
    String horaFin;
    Boolean ocupado;
}
