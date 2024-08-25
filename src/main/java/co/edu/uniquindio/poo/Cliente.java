package co.edu.uniquindio.poo;

public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String contrasena;

    // Constructor con paramentros
    public Cliente(String cedula, String nombre, String direccion, String telefono, String correoElectronico, String contrasena) {
        super(cedula, nombre);
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    // Constructor sin paramentros
    public Cliente(){}


    // Getters and setters ----------------------------------
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
