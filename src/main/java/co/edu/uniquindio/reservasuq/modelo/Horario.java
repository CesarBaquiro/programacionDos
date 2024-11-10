package co.edu.uniquindio.reservasuq.modelo;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Horario {
    LocalDate dia;
    String horaInicio;
    String horaFin;
    Boolean ocupado;
}
