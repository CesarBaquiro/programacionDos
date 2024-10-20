package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    private Paciente paciente;
    private String idCita;
    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;
}
