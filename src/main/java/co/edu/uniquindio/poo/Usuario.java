package co.edu.uniquindio.poo;

public class Usuario {
    private String cedula;
    private String nombre;

    // Cosntructor con parametros
    public Usuario(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    // Cosntructor sin parametros
    public Usuario() {}

    // Metodos de la clase -----------------------------

    public void registrarUsuario(){
        System.out.println("El usuario se ha registrado");
    }




    // Getters and setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

