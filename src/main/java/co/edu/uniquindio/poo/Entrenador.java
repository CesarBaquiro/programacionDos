package co.edu.uniquindio.poo;

public class Entrenador extends Usuario {
    private String especialidad; // atributo propio del Usuario-Entrenador

    public Entrenador(String cedula, String nombre, String especialidad) {
        super(cedula, nombre);
        this.especialidad = especialidad;
    } // constructor para la clase clase entrenador

    public Entrenador() {
    } // contructor sin parametros de la clase entrenador


    //Getters and setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Metodo toString para los entrenadores
    @Override
    public String toString() {
        return "Entrenador{" +
                " cedula="+getCedula()+
                " nombre="+getNombre()+
                " especialidad="+getEspecialidad()+
                "}";
    }
}
