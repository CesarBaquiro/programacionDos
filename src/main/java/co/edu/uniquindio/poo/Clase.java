package co.edu.uniquindio.poo;

import java.time.LocalDateTime;

public class Clase {
    String id;
    String nombre;
    Integer capacidad;
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;
    Boolean estado;
    TipoClase tipo;
    LocalDateTime horario;
    Entrenador entrenador;

    // Constructor con paramentros
    public Clase(String id, String nombre, Integer capacidad, LocalDateTime fechaInicio, LocalDateTime fechaFin, Boolean estado, TipoClase tipo, LocalDateTime horario, Entrenador entrenador){}

    // Constructor sin paramentros
    public Clase() {}

}
