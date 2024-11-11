package co.edu.uniquindio.bookyourstay.controllers;


import co.edu.uniquindio.bookyourstay.models.*;
import co.edu.uniquindio.bookyourstay.models.enums.Role;
import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import co.edu.uniquindio.bookyourstay.services.ServicesBookYourStay;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class MainController implements ServicesBookYourStay {


    private static MainController INSTANCIA;
    private final ReservationsBYS reservationsBYS;



    private MainController() {
        reservationsBYS = new ReservationsBYS();
        ArrayList<Schedule>  horariosPrueba = new ArrayList<Schedule>();

        ArrayList<Room> mocawaRooms = new ArrayList<>();


        // Datos de prueba
        try {
            String UUID = randomUUID().toString();
            // --- Personas de prueba
            reservationsBYS.registerUser("1234", "Roberto", "313213131", Role.USER,"est@gmail.com", "1234");
            reservationsBYS.registerUser( "1234",  "Alfonso Admin", "6465464", Role.ADMIN,"adm@gmail.com",  "1234");

            // --- Crear horarios de prueba
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 01), "10AM", "12PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 01), "12PM", "2PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 01), "4PM", "6PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 02), "10AM", "12PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 02), "12PM", "2PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 02), "4PM", "6PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 03), "10AM", "12PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 03), "12PM", "2PM", false));
            horariosPrueba.add(new Schedule(LocalDate.of(2024, 12, 03), "4PM", "6PM", false));

            ServicesIncluded servicesIncludedMocawa = new ServicesIncluded(true, true, true,true,false,true,true,true,true);
            mocawaRooms.add(new Room("Habitacion", 3, "Cama sencilla", 210000, "Habitacion con excelente vista", servicesIncludedMocawa, horariosPrueba));

            // --- Instalaciones
            reservationsBYS.createAccommodation(UUID, "Mocawa", "El Mocawa plaza, una increible opcion para conocer el Quindio", "Armenia, Quindio", mocawaRooms );


            // --- Reserva de prueba
            reservationsBYS.createReservation(UUID, reservationsBYS.getAccommodation().getFirst().getName(), reservationsBYS.getUsers().getFirst().getRole().toString(), reservationsBYS.getAccommodation().getFirst().getRooms().getFirst().getIdRoom(), reservationsBYS.getAccommodation().getFirst().getRooms().getFirst().getSchedules().getFirst().getDia(), reservationsBYS.getAccommodation().getFirst().getRooms().getFirst().getSchedules().getFirst().getHoraInicio());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static MainController getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new MainController();
        }
        return INSTANCIA;
    }

    public Boolean validarCorreo(String correo) {
        for(User p: reservationsBYS.getUsers()){
            if(correo.equals(p.getEmail())){
                return true;
            }
        }
        return false;
    }

    public Boolean validarContrasena(String contrasena) {
        for (User p : reservationsBYS.getUsers()) {
            if (p.getPassword().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> listarInstalaciones(){
        ArrayList<String> tiposInstalaciones = new ArrayList<>();
        for(Accommodation i: reservationsBYS.getAccommodation()){
            tiposInstalaciones.add(i.getName());
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

    public ArrayList<Schedule> searchRoomBySchedules(String instalacion, LocalDate fecha){
        ArrayList<Schedule> schedules = new ArrayList<>();

        // Encontrar la instalacion buscada
        for (Accommodation i: reservationsBYS.getAccommodation()) {
            if(instalacion.equals(i.getName())){
                // Encontrar los horarios con la fecha buscada
                for(Schedule h: i.getRooms().get(0).getSchedules()){
                    if (h.getDia().equals(fecha)){
                        schedules.add(h);
                    }
                }
            }
        }
        return schedules;
    }

    public Accommodation obtenerInstalacionPorNombre(String nombre) {
        Accommodation accommodation = null;
        for (Accommodation i: reservationsBYS.getAccommodation()) {
            if(nombre.equals(i.getName())){
                accommodation = i;
            }
        }
        return accommodation;
    }

    public void navegarVentanaObservable(String nombreFxml, String titulo, Observer observer) {
        try {


            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreFxml));
            Parent root = loader.load();


            // Asignamos el observador al controlador de la nueva ventana
            ObserverWindow observerWindow = loader.getController();
            observerWindow.setObservador(observer);


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
    public User login(String correo, String contrasena) throws Exception {
        return reservationsBYS.login(correo, contrasena);
    }


    @Override
    public void registerUser(String idDocumentation, String fullname, String phone, Role role, String email, String password) throws Exception {
        reservationsBYS.registerUser(idDocumentation, fullname, phone, role, email, password);
    }


    @Override
    public void createAccommodation(String idAccommodation, String name, String description, String location, ArrayList<Room> rooms) {
        reservationsBYS.createAccommodation(idAccommodation, name, description, location, rooms);
    }

    @Override
    public void createReservation(String idInstalacion, String idDocumentationUser, String idAccommodation, String idRoom, LocalDate reservationDate, String reservationHour) throws Exception {
        reservationsBYS.createReservation(idInstalacion, idDocumentationUser, idAccommodation, idRoom,reservationDate,reservationHour);
    }

    public Boolean verificarAforoPorHora(String nombreInstalacion, String horaReserva){
        return reservationsBYS.verificarAforoPorHora(nombreInstalacion, horaReserva);
    }

    public int contarReservasPorInstalacionHora(String nombreInstalacion, String horaReserva){
        return reservationsBYS.contarReservasPorInstalacionHora(nombreInstalacion, horaReserva);
    }

    @Override
    public List<Reservation> listAllReservations() {
        return List.of();
    }

    @Override
    public ArrayList<Reservation> listReservationsByUser(String cedulaPersona) {
        ArrayList<Reservation> listadoReservations = new ArrayList<>();
        for (Reservation reservation : reservationsBYS.getReservations()){
            if(reservation.getIdUser() == cedulaPersona){
                listadoReservations.add(reservation);
            }
        }
        return listadoReservations;
    }


    //TODO Completar con el resto de métodos necesarios para la aplicación
    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public void navigateWindow(String nombreArchivoFxml, String tituloVentana) {
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