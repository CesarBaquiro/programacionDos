package co.edu.uniquindio.poo;


import java.util.ArrayList;

public class Gimnasio {
    private ArrayList<Cliente> listadoClientes;
    private ArrayList<Clase> listadoClases;
    private ArrayList<Entrenamiento> listadoEntrenamientos;
    private ArrayList<Reserva> listadoReservas;
    private ArrayList<Entrenador> listadoEntrenadores;

    // Constructor con parametros
    public Gimnasio(ArrayList<Cliente> listadoClientes, ArrayList<Clase> listadoClases, ArrayList<Entrenamiento> listadoEntrenamientos, ArrayList<Reserva> listadoReservas, ArrayList<Entrenador> listadoEntrenadores) {
        this.listadoClientes = listadoClientes;
        this.listadoClases = listadoClases;
        this.listadoEntrenamientos = listadoEntrenamientos;
        this.listadoReservas = listadoReservas;
        this.listadoEntrenadores = listadoEntrenadores;
    }

    // Constructor sin parametros
    public Gimnasio() {}


    // Metodos de la clase ------------------------------------

    public ArrayList<Clase> obtenerClaseMasPopular(ArrayList<Clase> listadoClases){

        return listadoClases;
    }

    public ArrayList<Cliente> obtenerUsuariosMasActivos(ArrayList<Cliente> listadoClientes){
        return listadoClientes;
    }

    public Entrenamiento obtenerEjercicioMasPracticado(ArrayList<Entrenamiento> listadoEntrenamientos){
        return listadoEntrenamientos.get(0);
    }

    public ArrayList<Clase> obtenerClase(ArrayList<Clase> clases, Integer tipoBusqueda, String nombreEntrenador, Integer tipoClaseSeleccionada, String horario) {

        if(tipoBusqueda == 1){ // Busqueda por entrenador
            System.out.println("Buscando por nombre de entrenador");
            if(nombreEntrenador == null){
                System.out.println("El nombre del entrenador no esta registrado");
            }
        } else if (tipoBusqueda == 2 && tipoClaseSeleccionada != null) {
            System.out.println("Buscando por el tipo de clase");
        } else if (tipoBusqueda == 3 && horario != null) {
            System.out.println("Buscando por horario");
        }else{
            System.out.println("Esta opcion no esta disponible");
        }

        return listadoClases;
    }

    // Getters and setters -----------------------

    public ArrayList<Entrenador> getListadoEntrenadores() {
        return listadoEntrenadores;
    }

    public void setListadoEntrenadores(ArrayList<Entrenador> listadoEntrenadores) {
        this.listadoEntrenadores = listadoEntrenadores;
    }

    public ArrayList<Reserva> getListadoReservas() {
        return listadoReservas;
    }

    public void setListadoReservas(ArrayList<Reserva> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }

    public ArrayList<Entrenamiento> getListadoEntrenamientos() {
        return listadoEntrenamientos;
    }

    public void setListadoEntrenamientos(ArrayList<Entrenamiento> listadoEntrenamientos) {
        this.listadoEntrenamientos = listadoEntrenamientos;
    }

    public ArrayList<Clase> getListadoClases() {
        return listadoClases;
    }

    public void setListadoClases(ArrayList<Clase> listadoClases) {
        this.listadoClases = listadoClases;
    }

    public ArrayList<Cliente> getListadoClientes() {
        return listadoClientes;
    }

    public void setListadoClientes(ArrayList<Cliente> listadoClientes) {
        this.listadoClientes = listadoClientes;
    }



}
