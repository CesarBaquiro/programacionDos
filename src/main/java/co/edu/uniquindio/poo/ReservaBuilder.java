package co.edu.uniquindio.poo;
import co.edu.uniquindio.poo.enums.*;

import java.time.LocalDateTime;

public class ReservaBuilder {

        private String destino;
        private Integer duracion;
        private LocalDateTime fechaSalida;
        private Transporte transporte;
        private TipoAlojamiento tipoAlojamiento;
        private Actividades actividades;
        private DietasEspeciales dietasEspeciales;

        public ReservaBuilder(){}

    public ReservaBuilder destino(String destino) {
        this.destino = destino;
        return this;
    }

    public ReservaBuilder duracion(Integer duracion){
            this.duracion = duracion;
            return this;
    }

    public ReservaBuilder fechaSalida(LocalDateTime fechaSalida){
            this.fechaSalida = fechaSalida;
            return this;
    }

    public ReservaBuilder transporte(Transporte transporte){
            this.transporte = transporte;
            return this;
    }

    public ReservaBuilder tipoAlojamiento(TipoAlojamiento tipoAlojamiento){
            this.tipoAlojamiento = tipoAlojamiento;
            return this;
    }

    public ReservaBuilder actividades(Actividades actividades){
            this.actividades = actividades;
            return this;
    }

    public ReservaBuilder dietasEspeciales(DietasEspeciales dietasEspeciales){
            this.dietasEspeciales = dietasEspeciales;
            return this;
    }

    public Reserva build(){
            return new Reserva(destino, duracion, fechaSalida, transporte, tipoAlojamiento, actividades, dietasEspeciales);
    }

}
