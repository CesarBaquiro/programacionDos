package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Gimnasio {

    // Parametros de la clase gimnasio.
    private ArrayList<Cliente> listadoClientes;
    private ArrayList<Clase> listadoClases;
    private ArrayList<Entrenamiento> listadoEntrenamientos;
    private ArrayList<Reserva> listadoReservas;
    private ArrayList<Entrenador> listadoEntrenadores;

    // Se instancian las clases usadas
    Clase clase = new Clase();

    // Se instancian recursos necesarios
    private final Scanner scanner = new Scanner(System.in);

    // Constructor sin parametros
    public Gimnasio() {

        // Inicializar datos

        // CLASES
        // Fechas de inicio y fin de la clase 1
        LocalDateTime fechaInicio1 = LocalDateTime.parse("2024-08-22T00:00");
        LocalDateTime fechaFin1 = LocalDateTime.parse("2024-09-22T00:00");

        // Horarios de la clase 1
        ArrayList<LocalDateTime> horariosClase1 = new ArrayList<>();
        LocalDateTime fechaHorario1 = LocalDateTime.parse("2024-09-22T08:00");
        LocalDateTime fechaHorario2 = LocalDateTime.parse("2024-09-23T08:00");
        LocalDateTime fechaHorario3 = LocalDateTime.parse("2024-09-24T08:00");
        horariosClase1.add(fechaHorario1);
        horariosClase1.add(fechaHorario2);
        horariosClase1.add(fechaHorario3);

        // Horarios de la clase 2
        ArrayList<LocalDateTime> horariosClase2 = new ArrayList<>();
        LocalDateTime fechaHorario4 = LocalDateTime.parse("2024-11-05T18:00");
        LocalDateTime fechaHorario5 = LocalDateTime.parse("2024-11-07T18:00");
        LocalDateTime fechaHorario6 = LocalDateTime.parse("2024-11-09T18:00");
        horariosClase2.add(fechaHorario4);
        horariosClase2.add(fechaHorario5);
        horariosClase2.add(fechaHorario6);

        // Fechas de inicio y fin de la clase 3
        LocalDateTime fechaInicio2 = LocalDateTime.parse("2024-12-01T00:00");
        LocalDateTime fechaFin2 = LocalDateTime.parse("2025-02-15T00:00");
        // Horarios de la clase 3
        ArrayList<LocalDateTime> horariosClase3 = new ArrayList<>();
        LocalDateTime fechaHorario7 = LocalDateTime.parse("2024-12-05T14:00");
        LocalDateTime fechaHorario8 = LocalDateTime.parse("2024-01-15T10:00");
        LocalDateTime fechaHorario9 = LocalDateTime.parse("2025-02-25T18:00");
        horariosClase3.add(fechaHorario7);
        horariosClase3.add(fechaHorario8);
        horariosClase3.add(fechaHorario9);

        // Inicializacion de listas vacias
        this.listadoClientes = new ArrayList<Cliente>();
        this.listadoClases = new ArrayList<Clase>();
        this.listadoEntrenamientos = new ArrayList<Entrenamiento>();
        this.listadoReservas = new ArrayList<Reserva>();
        this.listadoEntrenadores = new ArrayList<Entrenador>();

        // Ingreso de datos de prueba

        // CLIENTES


        // ENTRENADORES
        listadoEntrenadores.add(registrarEntrenador("156465465", "Camilo", "Fuerza"));
        listadoEntrenadores.add(registrarEntrenador("15646513", "Daniela", "Gluteo"));
        listadoEntrenadores.add(registrarEntrenador("15121531", "Pepito", "Cardio"));


        // CLASES
        listadoClases.add(registrarClase("CLA_RUM-08", "Clase rumba 8AM", 30, fechaInicio1, fechaFin1, true,
                TipoClase.RUMBATERAPIA, horariosClase1, getListadoEntrenadores().get(0).getCedula(), null));

        listadoClases.add(registrarClase("CLA_RUM-18", "Clase rumba 6PM", 25, fechaInicio1, fechaFin1, true,
                TipoClase.RUMBATERAPIA, horariosClase2, getListadoEntrenadores().get(1).getCedula(), null));

        listadoClases.add(registrarClase("CLA_YOGA-10", "Clase yoga nuevo año", 20, fechaInicio2, fechaFin2, true,
                TipoClase.YOGA, horariosClase2, getListadoEntrenadores().get(2).getCedula(), null));


        //RESERVAS
        listadoReservas.add(registrarReserva(true,  "CLA_RUM-08",  "156465465",  listadoClases.get(0).getHorarios().get(0)));
        listadoReservas.add(registrarReserva(true,  "CLA_RUM-18",  "156465465",listadoClases.get(0).getHorarios().get(0)));
        listadoReservas.add(registrarReserva(true,  "CLA_YOGA-10",  "15646513",  listadoClases.get(0).getHorarios().get(0)));


        // ENTRENAMIENTOS
        listadoEntrenamientos.add(registrarEntrenamiento(TipoEjercicio.BICICLETA, 30, 5));
        listadoEntrenamientos.add(registrarEntrenamiento(TipoEjercicio.ESPALDA, 15, 5));
        listadoEntrenamientos.add(registrarEntrenamiento(TipoEjercicio.GLUTEO, 15, 5));




    }

    // Metodos de reportes------------------------------------

    public ArrayList<Clase> obtenerClaseMasPopular(ArrayList<Clase> listadoClases) {

        return listadoClases;
    }

    public ArrayList<Cliente> obtenerUsuariosMasActivos(ArrayList<Cliente> listadoClientes) {
        return listadoClientes;
    }

    public Entrenamiento obtenerEjercicioMasPracticado(ArrayList<Entrenamiento> listadoEntrenamientos) {
        return listadoEntrenamientos.get(0);
    }

    // Metodo para buscar y analizar clases------------------------------------
    public ArrayList<Clase> obtenerClase(ArrayList<Clase> clases, Integer tipoBusqueda, String nombreEntrenador,
            Integer tipoClaseSeleccionada, String horario) {

        if (tipoBusqueda == 1) { // Busqueda por entrenador
            System.out.println("Buscando por nombre de entrenador");
            if (nombreEntrenador == null) {
                System.out.println("El nombre del entrenador no esta registrado");
            }
        } else if (tipoBusqueda == 2 && tipoClaseSeleccionada != null) {
            System.out.println("Buscando por el tipo de clase");
        } else if (tipoBusqueda == 3 && horario != null) {
            System.out.println("Buscando por horario");
        } else {
            System.out.println("Esta opcion no esta disponible");
        }

        return listadoClases;
    }

    public void mostrarClasesTodas(ArrayList<Clase> listadoClases) {
        for (Clase clase : listadoClases) {
            System.out.println(clase);
        }
        if(listadoClases.isEmpty()){
            System.out.println(" - No se encontraron clases de este tipo - ");
        }
    }

    public void mostrarClasesDisponibles(ArrayList<Clase> listadoClases) {
        for (Clase clase : listadoClases) {
            // Solo muestra las clases que esten disponibles
            if (clase.getDisponible() == true) {
                System.out.println(clase.toString());
            }
        }
        if(listadoClases.isEmpty()){
            System.out.println(" - No se encontraron clases disponibles - ");
        }
    }

    public ArrayList<Clase> buscarClasePorCedulaEntrenador(String cedulaEntrenador) {
        ArrayList<Clase> clasesEncontradas =  new ArrayList<>();
        ArrayList<Clase> listaClases =  getListadoClases();
        for(Clase clase : listaClases) {
            if(clase.getCedulaEntrenador() == cedulaEntrenador) {
                clasesEncontradas.add(clase);
            }
        }
        return clasesEncontradas;
    }

    public ArrayList<Clase> buscarClasePorTipo(Integer opcionTipo) {
        ArrayList<Clase> clasesEncontradas =  new ArrayList<>();
        ArrayList<Clase> listaClases =  getListadoClases();
        TipoClase tipo = null;

        switch(opcionTipo) {
            case 1:
                tipo = TipoClase.RUMBATERAPIA;
                break;
            case 2:
                tipo = TipoClase.AEROBICOS;
                break;
            case 3:
                tipo = TipoClase.RESISTENCIA;
                break;
            case 4:
                tipo = TipoClase.FUERZA;
                break;
            case 5:
                tipo = TipoClase.YOGA;
                break;
            default:
                System.out.println(" - Por favor ingrese una opcion valida - ");
                break;
        }

        for(Clase clase : listaClases) {
            if(clase.getTipoClase() == tipo) {
                clasesEncontradas.add(clase);
            }
        }

        return clasesEncontradas;
    }

    // METODO PARA REGISTRAR UN ENTRENADOR
    public Entrenador registrarEntrenador(String cedula, String nombre, String especialidad) {
        return new Entrenador(cedula, nombre, especialidad);
    }

    // METODO PARA REGISTRAR UNA NUEVA CLASE
    public Clase registrarClase(String id, String nombre, Integer capacidad, LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Boolean disponible, TipoClase tipoClase, ArrayList<LocalDateTime> horario, String cedulaEntrenador,
            ArrayList<Reserva> inscritos) {

        return new Clase(id, nombre, capacidad, fechaInicio, fechaFin, disponible, tipoClase, horario, cedulaEntrenador,
                inscritos);

    }

    // Metodo para registar clientes
    public Cliente registrarCliente() {

        System.out.println(" - Ingrese la cédula");
        String cedula = scanner.next();
        System.out.println(" - Ingrese el nombre");
        String nombre = scanner.next();
        System.out.println(" - Ingrese la dirección");
        String direccion = scanner.next();
        System.out.println(" - Ingrese el teléfono");
        String telefono = scanner.next();
        System.out.println("\n" + " - Ingrese el correo");
        String correoElectronico = scanner.next();
        System.out.println(" - Ingrese el contraseña");
        String contrasena = scanner.next();

        return new Cliente(cedula, nombre, direccion, telefono, correoElectronico, contrasena);

    }

    // Metodo para eliminar cliente
    public void eliminarCliente() {
        System.out.println(" - Ingrese la cédula");
        String cedula = scanner.next();

        for (int i = 0; i < listadoClientes.size(); i++) {
            if (listadoClientes.get(i).getCedula().equals(cedula)) {
                listadoClientes.remove(i);
                System.out.println("\n" + "Cliente eliminado");
            }
        }
    }

    public Entrenamiento registrarEntrenamiento(TipoEjercicio tipoEjercicio, Integer duracion, Integer caloriasQuemadas){
        return new Entrenamiento(tipoEjercicio, duracion, caloriasQuemadas);
    }

    public Reserva registrarReserva(Boolean estado, String idClase, String cedulaCliente, LocalDateTime fechaReserva){
        return new Reserva(estado, idClase, cedulaCliente, fechaReserva);

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

    public void setNuevoCliente(Cliente cliente) {
        listadoClientes.add(cliente);
    }

}
