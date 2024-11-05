package co.edu.uniquindio.reservasuq.controlador;


import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorPrincipal implements ServiciosReservasUQ{


    private static ControladorPrincipal INSTANCIA;
    private final ReservasUQ reservasUQ;



    private ControladorPrincipal() {
        reservasUQ = new ReservasUQ();
        ArrayList<Horario>  horariosPrueba = new ArrayList<Horario>();

        // Datos de prueba
        try {
            // --- Personas de prueba
            reservasUQ.registrarPersona( "1234",  "Cesar Administrador", TipoPersona.ADMIN, "cesar@gmail.com", "1212");
            reservasUQ.registrarPersona("4321", "Cesar Usuario", TipoPersona.ESTUDIANTE, "cesar2@gmail.com", "1212");

            // --- Crear horarios de prueba
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 01), "10AM", "12PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 01), "12PM", "2PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 01), "4PM", "6PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 02), "10AM", "12PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 02), "12PM", "2PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 02), "4PM", "6PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 03), "10AM", "12PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 03), "12PM", "2PM", false));
            horariosPrueba.add(new Horario(LocalDate.of(2024, 12, 03), "4PM", "6PM", false));

            // --- Instalaciones
            reservasUQ.crearInstalacion("Piscina", 20, 2500, horariosPrueba);
            reservasUQ.crearInstalacion("Gimnasio", 16, 20000, horariosPrueba);
            reservasUQ.crearInstalacion("Cancha de fútbol", 22, 60000, horariosPrueba);
            reservasUQ.crearInstalacion("Auditorio Euclides Jaramillo", 16, 20000, horariosPrueba);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    public Boolean validarCorreo(String correo) {
        for(Persona p: reservasUQ.getPersonas()){
            System.out.println(p.getEmail());
            if(correo.equals(p.getEmail())){
                return true;
            }
        }
        System.out.println("No existe ese correo");
        return false;
    }

    public Boolean validarContrasena(String contrasena) {
        for (Persona p : reservasUQ.getPersonas()) {
            if (p.getPassword().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> listarInstalaciones(){
        ArrayList<String> tiposInstalaciones = new ArrayList<>();
        for(Instalacion i: reservasUQ.getInstalaciones()){
            tiposInstalaciones.add(i.getNombre());
            System.out.println(
                    i.getNombre()
            );
        }
        return tiposInstalaciones;
    }

    public ArrayList<String> listarTiposPesonas(){
        ArrayList<String> tiposPersonas = new ArrayList<>();
        tiposPersonas.add("Estudiante");
        tiposPersonas.add("Docente");
        tiposPersonas.add("Administrativo");
        tiposPersonas.add("Externo");

        return tiposPersonas;
    }

    public ArrayList<Horario> buscarHorariosInstalaciones(String instalacion, LocalDate fecha){
        ArrayList<Horario> horarios = new ArrayList<>();

        // Encontrar la instalacion buscada
        for (Instalacion i: reservasUQ.getInstalaciones()) {
            if(instalacion.equals(i.getNombre())){
                // Encontrar los horarios con la fecha buscada
                for(Horario h: i.getHorarios()){
                    if (h.getDia().equals(fecha)){
                        horarios.add(h);
                    }
                }
            }
        }
        return horarios;
    }

    public void navegarVentanaObservable(String nombreFxml, String titulo, Observador observador) {
        try {


            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreFxml));
            Parent root = loader.load();


            // Asignamos el observador al controlador de la nueva ventana
            VentanaObservable ventanaObservable = loader.getController();
            ventanaObservable.setObservador(observador);


            // Crear la escena
            Scene scene = new Scene(root);


            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titulo);


            // Mostrar la nueva ventana
            stage.show();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return reservasUQ.login(correo, contrasena);
    }


    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        reservasUQ.registrarPersona(cedula, nombre, tipoPersona, email, password);
    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        reservasUQ.crearInstalacion(nombre, aforo, costo, horarios);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }


    //TODO Completar con el resto de métodos necesarios para la aplicación
    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();


            // Crear la escena
            Scene scene = new Scene(root);


            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);


            // Mostrar la nueva ventana
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}