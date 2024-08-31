package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Reserva {

    private Boolean estado; // Estado de la reserva
    private String idClase; // La clase a reservar
    private String cedulaUsuario;
    private LocalDateTime fechaReserva; // Formato:

    // Constructor default para la clase reserva
    public Reserva(Boolean estado, String idClase, String cedulaUsuario, LocalDateTime fechaReserva) {
        this.estado = estado;
        this.idClase = idClase;
        this.cedulaUsuario = cedulaUsuario;
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

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    // Metodo toString para las Reservas
    @Override
    public String toString() {
        String estado;
        if(getEstado() == true){
            estado = "Activo";
        }else{
            estado = "Inactivo";
        }
        return "Reserva{" +
                "Estado = "+estado+ " | "+
                "ID de la clase = "+getIdClase()+" | "+
                "Cedula del cliente = "+ getCedulaUsuario()+" | "+
                "Fecha de la reserva = "+getFechaReserva()+" | "+
                "}";
    }
}
