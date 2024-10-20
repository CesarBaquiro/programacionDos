package co.edu.uniquindio.clinica.modelo;

import java.time.LocalDateTime;

public class Cita {
    private Paciente paciente;
    private String idCita;
    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;
}
