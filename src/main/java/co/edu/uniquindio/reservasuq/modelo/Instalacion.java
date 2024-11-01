package co.edu.uniquindio.reservasuq.modelo;

import lombok.Builder;

import java.util.List;

@Builder
public class Instalacion {
    String nombre;
    int aforo;
    float costo;
    List<Horario> horarios;
}
