package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import co.edu.uniquindio.poo.enums.*;
import co.edu.uniquindio.poo.ReservaBuilder;

public class Reserva {
    private String destino;
    private Integer duracion;
    private LocalDateTime fechaSalida;
    private Transporte transporte;
    private TipoAlojamiento tipoAlojamiento;
    private Actividades actividades;
    private DietasEspeciales dietasEspeciales;


    public Reserva(String destino, Integer duracion, LocalDateTime fechaSalida, Transporte transporte, TipoAlojamiento tipoAlojamiento, Actividades actividades, DietasEspeciales dietasEspeciales){

        this.duracion = duracion;
        this.fechaSalida = fechaSalida;
        this.transporte = transporte;
        this.tipoAlojamiento = tipoAlojamiento;
        this.actividades = actividades;
        this.dietasEspeciales = dietasEspeciales;
    }

    public static ReservaBuilder builder(){
        return new ReservaBuilder();
    }


}
