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
        LocalDateTime fechaInicio1 = crearFecha("2024","08","22","00","00");
        LocalDateTime fechaFin1 = crearFecha("2024","09","22","00","00");

        // Horarios de la clase 1
        ArrayList<LocalDateTime> horariosClase1 = new ArrayList<>();
        horariosClase1.add(crearFecha("2024","09","22","08","00"));
        horariosClase1.add(crearFecha("2024","09","23","08","00"));
        horariosClase1.add(crearFecha("2024","09","24","08","00"));

        // Horarios de la clase 2
        ArrayList<LocalDateTime> horariosClase2 = new ArrayList<>();
        horariosClase2.add(crearFecha("2024","11","05","18","00"));
        horariosClase2.add(crearFecha("2024","11","07","18","00"));
        horariosClase2.add(crearFecha("2024","11","09","18","00"));

        // Fechas de inicio y fin de la clase 3
        LocalDateTime fechaInicio2 = crearFecha("2024","12","01","18","00");
        LocalDateTime fechaFin2 = crearFecha("2024","02","15","18","00");

        // Horarios de la clase 3
        ArrayList<LocalDateTime> horariosClase3 = new ArrayList<>();
        horariosClase3.add(crearFecha("2024","01","05","08","00"));
        horariosClase3.add(crearFecha("2024","02","15","10","00"));
        horariosClase3.add( crearFecha("2024","03","25","12","00"));

        // Inicializacion de listas vacias
        this.listadoClientes = new ArrayList<Cliente>();
        this.listadoClases = new ArrayList<Clase>();
        this.listadoEntrenamientos = new ArrayList<Entrenamiento>();
        this.listadoReservas = new ArrayList<Reserva>();
        this.listadoEntrenadores = new ArrayList<Entrenador>();

        // Ingreso de datos de prueba ----------------------------------------

        // CLIENTES

        // ENTRENADORES
        listadoEntrenadores.add(registrarEntrenador("156465465", "Camilo", "Fuerza"));
        listadoEntrenadores.add(registrarEntrenador("15646513", "Daniela", "Gluteo"));
        listadoEntrenadores.add(registrarEntrenador("15121531", "Pepito", "Cardio"));

        // CLASES
        listadoClases.add(registrarClase("CLA_RUM-08", "Clase rumba 8AM", 30, fechaInicio1, fechaFin1, true,
                TipoClase.RUMBATERAPIA, horariosClase1, getListadoEntrenadores().get(0).getCedula(), null));

        listadoClases.add(registrarClase("CLA_RUM-10", "Clase rumba 6PM", 25, fechaInicio1, fechaFin1, true,
                TipoClase.RUMBATERAPIA, horariosClase2, getListadoEntrenadores().get(1).getCedula(), null));

        listadoClases.add(registrarClase("CLA_YOGA-12", "Clase yoga nuevo año", 20, fechaInicio2, fechaFin2, true,
                TipoClase.YOGA, horariosClase2, getListadoEntrenadores().get(2).getCedula(), null));

        //RESERVAS
        Reserva reservaGenerica = new Reserva(true, "CLA_RUM-08",  "1234", listadoClases.get(0).getHorarios().get(0));
        Reserva reservaGenerica2 = new Reserva(true, "CLA_RUM-10",  "4321", listadoClases.get(0).getHorarios().get(1));
        Reserva reservaGenerica3 = new Reserva(false, "CLA_YOGA-12",  "0000", listadoClases.get(0).getHorarios().get(1));
        listadoReservas.add(reservaGenerica);
        listadoReservas.add(reservaGenerica2);
        listadoReservas.add(reservaGenerica3);

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

    public Reserva registrarReserva(String idClase, String cedulaCliente){

        Boolean estado = true;
        LocalDateTime fechaReserva = null;
        for(int i = 0; i < listadoClases.size(); i++) {
            if(listadoClases.get(i).getId() ==  idClase){
                System.out.println(listadoClases.get(i).getHorarios().toString());
                System.out.println("Seleccione el horario que desea registrar");
                Integer horarioOpcion = scanner.nextInt();
                if(horarioOpcion > 0 && horarioOpcion < listadoClases.get(i).getHorarios().size()) {
                    fechaReserva = listadoClases.get(i).getHorarios().get(horarioOpcion);
                }else{
                    System.out.println("El horario solicitado no es valido");
                }
            }
        }
        return new Reserva(estado, idClase, cedulaCliente, fechaReserva);
    }

    public void mostrarReservasActivas(ArrayList<Reserva> listadoReservas) {
        for(Reserva reserva : listadoReservas) {
            if(reserva.getEstado()){
            System.out.println(reserva.toString());
            }
        }
    }

    public void cancelarReserva(String cedulaCliente){
        for(Reserva reserva: listadoReservas){
            if(reserva.getCedulaUsuario() ==  cedulaCliente){
                System.out.println("---- Listado de reservas ----");
                System.out.println(reserva.toString());
            }else{
                System.out.println("El usuario no tiene reservas");
            }
        }

    }

    public LocalDateTime crearFecha(String anio, String mes, String dia,String hora, String minuto){
        String fecha = anio+"-"+mes+"-"+dia+"T"+hora+":"+minuto;
        return LocalDateTime.parse(fecha);
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
