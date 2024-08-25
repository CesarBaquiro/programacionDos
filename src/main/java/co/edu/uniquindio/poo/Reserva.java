package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Reserva {

    private Boolean estado; // Estado de la reserva
    private String idClase; // La clase a reservar
    private String usuario;
    private LocalDateTime fechaReserva; // Formato:

    // Constructor default para la clase reserva
    public Reserva(Boolean estado, String idClase, String usuario, LocalDateTime fechaReserva) {
        this.estado = estado;
        this.idClase = idClase;
        this.usuario = usuario;
        this.fechaReserva = fechaReserva;
    }

    // Constructor sin parametros
    public Reserva() {
    }

    // Getters and setters

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getIdClase() {
        return idClase;
    }

    public void setIdClase(String idClase) {
        this.idClase = idClase;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

}
