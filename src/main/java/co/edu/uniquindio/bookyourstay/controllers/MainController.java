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
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@Getter
public class MainController implements ServicesBookYourStay {
    private static MainController INSTANCIA;
    private final ReservationsBYS reservationsBYS;
    // Variable para almacenar el controlador de la ventana actual
    private Object currentController;

    private MainController() {
        reservationsBYS = new ReservationsBYS();
        ArrayList<Schedule>  horariosPrueba = new ArrayList<Schedule>();

        ArrayList<Room> mocawaRooms = new ArrayList<>();


        // Datos de prueba
        try {
            String UUID = randomUUID().toString();
            // --- Personas de prueba
            reservationsBYS.registerUser("1234", "Roberto", "313213131", Role.USER,"est@gmail.com", "1234", "");
            reservationsBYS.registerUser( "1234",  "Alfonso Admin", "6465464", Role.ADMIN,"adm@gmail.com",  "1234", "");

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
            //ArrayList<String> imagesMocawa1 = new ArrayList<String>();

            // Datos de prueba
            ArrayList<Schedule> schedulesMocawa = new ArrayList<Schedule>();
            schedulesMocawa.add(new Schedule(LocalDate.parse("2025-02-28"), "10PM", "9AM", false));
            // Crear una lista de habitaciones de ejemplo
            ArrayList<String> imagesMocawa1 = new ArrayList<>();
            imagesMocawa1.add(getClass().getResource("/img/recepcion1.jpg").toExternalForm());
            imagesMocawa1.add(getClass().getResource("/img/dormitorio1Vista.jpg").toExternalForm());
            imagesMocawa1.add(getClass().getResource("/img/dormitorio1Basico2.jpg").toExternalForm());
            imagesMocawa1.add(getClass().getResource("/img/almohada1Basico.jpg").toExternalForm());

            ArrayList<String> imagesMocawa2 = new ArrayList<>();
            imagesMocawa2.add(getClass().getResource("/img/dormitorio1Vista.jpg").toExternalForm());
            imagesMocawa2.add(getClass().getResource("/img/almohada1Basico.jpg").toExternalForm());
            imagesMocawa2.add(getClass().getResource("/img/dormitorio1Basico2.jpg").toExternalForm());
            imagesMocawa2.add(getClass().getResource("/img/recepcion1.jpg").toExternalForm());

            ArrayList<Room> roomsMocawa = new ArrayList<>();

            // --- Crear acomodacion de prueba
            reservationsBYS.createAccommodation("1", "Hotel Mocawa", "Descripción del alojamiento", "Armenia, Colombia", roomsMocawa);

            roomsMocawa.add(new Room("Habitación 1", 3,"Cama simple", 180000,"Descripción de la Habitación 1", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), schedulesMocawa, imagesMocawa1, reservationsBYS.getAccommodations().get(0).getIdAccommodation()));
            roomsMocawa.add(new Room("Habitacion presidencial", 6,"Cama doble", 490000,"Descripción de la Habitación 2", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), schedulesMocawa, imagesMocawa2, reservationsBYS.getAccommodations().get(0).getIdAccommodation()));



            //mocawaRooms.add(new Room("Habitacion 3", 3, "Cama sencilla", 210000, "Habitacion con excelente vista", servicesIncludedMocawa, horariosPrueba, imagesMocawa1));

            // --- Instalaciones
            //reservationsBYS.createAccommodation(UUID, "Mocawa", "El Mocawa plaza, una increible opcion para conocer el Quindio", "Armenia, Quindio", mocawaRooms );


            // --- Reserva de prueba
            //reservationsBYS.createReservation(UUID, reservationsBYS.getAccommodations().getFirst().getName(), reservationsBYS.getUsers().getFirst().getRole().toString(), reservationsBYS.getAccommodations().getFirst().getRooms().getFirst().getIdRoom(), reservationsBYS.getAccommodations().getFirst().getRooms().getFirst().getSchedules().getFirst().getDia(), reservationsBYS.getAccommodations().getFirst().getRooms().getFirst().getSchedules().getFirst().getHoraInicio());

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
        for(Accommodation i: reservationsBYS.getAccommodations()){
            tiposInstalaciones.add(i.getName());
        }
        return tiposInstalaciones;
    }

    public void printUsers(){
        ArrayList<User> usersList;
        usersList = reservationsBYS.getUsers();
        for (User u: usersList) {
            System.out.println( u.toString());
        }

    }

    public ArrayList<Schedule> searchRoomBySchedules(String instalacion, LocalDate fecha){
        ArrayList<Schedule> schedules = new ArrayList<>();

        // Encontrar la instalacion buscada
        for (Accommodation i: reservationsBYS.getAccommodations()) {
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

    public ArrayList<Room> getAllRooms(){
        ArrayList<Room> rooms = new ArrayList<>();

        for(Accommodation ac: reservationsBYS.getAccommodations()){
            for (int i = 0; i< ac.getRooms().size(); i++) {
                rooms.add(ac.getRooms().get(i));
            }
        }
        return rooms;
    }

    public ArrayList<Accommodation> getAllAccommodations(){
        return reservationsBYS.getAccommodations();
    }

    public Accommodation obtenerInstalacionPorNombre(String nombre) {
        Accommodation accommodation = null;
        for (Accommodation i: reservationsBYS.getAccommodations()) {
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
    public void registerUser(String idDocumentation, String fullname, String phone, Role role, String email, String password, String activationCode) throws Exception {
        reservationsBYS.registerUser(idDocumentation, fullname, phone, role, email, password, activationCode);
    }




    @Override
    public void createAccommodation(String idAccommodation, String name, String description, String location, ArrayList<Room> rooms) {
        reservationsBYS.createAccommodation(idAccommodation, name, description, location, rooms);
    }

    @Override
    public void createReservation(String idInstalacion, String idDocumentationUser, String idAccommodation, String idRoom, LocalDate startDate, LocalDate endDate) throws Exception {
        reservationsBYS.createReservation(idInstalacion, idDocumentationUser, idAccommodation, idRoom,startDate,endDate);
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

    public Object getController() {
        return currentController;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}