package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Clase {
    private String id;
    private String nombre;
    private Integer capacidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean disponible;
    private TipoClase tipoClase;
    private LocalDateTime horario;
    private String cedulaEntrenador;
    private ArrayList<Reserva> inscritos;

    // Constructor con paramentros
    public Clase(String id, String nombre, Integer capacidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            Boolean disponible, TipoClase tipoClase, LocalDateTime horario, String cedulaEntrenador,
            ArrayList<Reserva> inscritos) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.disponible = disponible;
        this.tipoClase = tipoClase;
        this.horario = horario;
        this.cedulaEntrenador = cedulaEntrenador;
        this.inscritos = inscritos;
    }

    // Constructor sin parametros
    public Clase() {
    }

    // Getters and setters ----------------------------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getCedulaEntrenador() {
        return cedulaEntrenador;
    }

    public void setCedulaEntrenador(String cedulaEntrenador) {
        this.cedulaEntrenador = cedulaEntrenador;
    }

    public ArrayList<Reserva> getInscritos() {
        return inscritos;
    }

    public void setInscritos(ArrayList<Reserva> inscritos) {
        this.inscritos = inscritos;
    }

}
