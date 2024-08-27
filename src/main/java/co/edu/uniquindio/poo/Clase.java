package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Clase {
    private String id;
    private String nombre;
    private Integer capacidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean disponible;
    private TipoClase tipoClase;
    private ArrayList<LocalDateTime> horario; // Ejemplo: ["2024-01-22T00:00", "2024-02-22T00:00", "2024-05-22T00:00"]
    private String cedulaEntrenador;
    private ArrayList<Reserva> inscritos;



    //Formato de la fecha de inicio y fin de las clases
    private DateTimeFormatter formatoFechaInicioFin = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");


    //private DateTimeFormatter formatoHorarioClase = DateTimeFormatter.ofPattern("dd HH:mm");



    //System.out.println(horarioClase);
    //System.out.println("Horarios disponibles: "+horarioClasePresentacion);
    //System.out.println("Clase disponible del "+fechaInicioPresentacion+" al "+fechaFinPresentacion);


    // Constructor con paramentros
    public Clase(String id, String nombre, Integer capacidad, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            Boolean disponible, TipoClase tipoClase, ArrayList<LocalDateTime> horario, String cedulaEntrenador,
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
        ArrayList <Reserva> inscritos = new ArrayList<>();
    }

    // Metodos de la clase

    public void mostrarHorarios(ArrayList<LocalDateTime> horarios) {
        // Horario en formato numero dia y hora
        for(LocalDateTime horario : horarios) {
        String diaDeSemanaClase = horario.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        Integer diaDeSemanaClaseNumero = horario.getDayOfMonth();
        String horaClase = horario.getHour() + ":" + horario.getMinute()+horario.getSecond();
        String horarioClase = diaDeSemanaClase + " " +diaDeSemanaClaseNumero+ " a las " + horaClase;
        String horarioClasePresentacion = horarioClase;
        System.out.println(horarioClase);
        System.out.println("Horarios disponibles: "+horarioClasePresentacion);
        }
    }

    public void mostrarFechaInicioFin(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        //Formato de hora para visualizacion de la hora de la clase
        String fechaInicioPresentacion = fechaInicio.format(formatoFechaInicioFin);
        String fechaFinPresentacion = fechaFin.format(formatoFechaInicioFin);
        System.out.println("Clase disponible del "+fechaInicioPresentacion+" al "+fechaFinPresentacion);
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

    public ArrayList<LocalDateTime> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<LocalDateTime> horario) {
        this.horario = horario;
    }



    @Override
    public String toString() {
        return "Clase{" +
                " ID: " + getId()+
                " Nombre: " + getNombre()+
                " Capacidad: " + getCapacidad()+
                " Fecha de inicio: " + getFechaInicio()+
                " Fecha de fin: " + getFechaFin()+
                " Disponible: " + getDisponible()+
                " Tipo de clase: " + getTipoClase()+
                " Horario: " + getHorario()+
                " Cédula del entrenador: " + getCedulaEntrenador()+
                " Inscritos: " + getInscritos()+
                "}";
    }
}
