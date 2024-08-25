package co.edu.uniquindio.poo;

public class Entrenador extends Usuario {
    private String especialidad;

    public Entrenador(String cedula, String nombre, String especialidad) {
        super(cedula, nombre);
        this.especialidad = especialidad;
    }

    public Entrenador(){}
}
