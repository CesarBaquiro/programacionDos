package co.edu.uniquindio.poo;
import java.time.LocalDateTime;
import co.edu.uniquindio.poo.enums.*;

public class Reserva {
    private String destino;
    private Integer duracion;
    private LocalDateTime fechaSalida;
    private Transporte transporte;
    private TipoAlojamiento tipoAlojamiento;
    private Actividades actividades;
    private DietasEspeciales dietasEspeciales;


    public Reserva(String destino, Integer duracion, LocalDateTime fechaSalida, Transporte transporte, TipoAlojamiento tipoAlojamiento, Actividades actividades, DietasEspeciales dietasEspeciales) {
        this.destino = destino;
        this.duracion = duracion;
        this.fechaSalida = fechaSalida;
        this.transporte = transporte;
        this.tipoAlojamiento = tipoAlojamiento;
        this.actividades = actividades;
        this.dietasEspeciales = dietasEspeciales;
    }

    public static ReservaBuilder builder() {
        return new ReservaBuilder();
    }

    @Override
    public String toString() {
        return "Destino: " + destino +
                " - Duracion: " + duracion +
                " - Fecha de salida: " + fechaSalida +
                " - Transporte: " + transporte +
                " - Tipo alojamiento: " + tipoAlojamiento +
                " - Actividades: " + actividades +
                " - Dietas especiales: " + dietasEspeciales;
    }

}
