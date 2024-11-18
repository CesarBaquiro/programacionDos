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

@Getter
public class MainController implements ServicesBookYourStay {
    private static MainController INSTANCIA;
    private final ReservationsBYS reservationsBYS;

    private MainController() {
        reservationsBYS = new ReservationsBYS();

        // Test data
        try {
            // --- Test people
            reservationsBYS.registerUser("1234567", "Usuario de prueba", "311213131", Role.USER,"usuario@gmail.com", "1234", "");
            reservationsBYS.registerUser("12345678", "Hotelero de prueba", "313213731", Role.HOTELIER,"hotelero@gmail.com", "1234", "");
            reservationsBYS.registerUser( "123456789",  "Alfonso Admin", "3145267942", Role.ADMIN,"admin@gmail.com",  "1234", "");

            ServicesIncluded servicesIncludedMocawa = new ServicesIncluded(true, true, true,true,false,true,true,true,true);

            // --- Rooms test
            ArrayList<String> imagesMocawi1 = new ArrayList<>();
            imagesMocawi1.add(getClass().getResource("/img/recepcion1.jpg").toExternalForm());
            imagesMocawi1.add(getClass().getResource("/img/dormitorio1Vista.jpg").toExternalForm());
            imagesMocawi1.add(getClass().getResource("/img/dormitorio1Basico2.jpg").toExternalForm());
            imagesMocawi1.add(getClass().getResource("/img/almohada1Basico.jpg").toExternalForm());

            ArrayList<String> imagesMocawi2 = new ArrayList<>();
            imagesMocawi2.add(getClass().getResource("/img/dormitorio1Vista.jpg").toExternalForm());
            imagesMocawi2.add(getClass().getResource("/img/almohada1Basico.jpg").toExternalForm());
            imagesMocawi2.add(getClass().getResource("/img/dormitorio1Basico2.jpg").toExternalForm());
            imagesMocawi2.add(getClass().getResource("/img/recepcion1.jpg").toExternalForm());

            ArrayList<Room> roomsMocawi = new ArrayList<>();

            // --- Test hotels
            reservationsBYS.createHotel( "Mocawi", "Ubicado en la vibrante ciudad de Armenia, Hotel Mocawi redefine la experiencia hotelera con un concepto que fusiona lujo, comodidad y conexión con la naturaleza. Inspirado en la rica cultura cafetera y los paisajes únicos del Quindío, Mocawi ofrece a sus huéspedes una experiencia inolvidable en el centro de la ciudad, rodeado de modernas comodidades y un ambiente acogedor.", "Armenia, Quindío", roomsMocawi);

            // --- Add mocawa hotels to test hotelier 1
            ArrayList<String> idMocawisHotels = new ArrayList<>();
            idMocawisHotels.add(reservationsBYS.getHotels().get(0).getIdHotel());
            reservationsBYS.getUsers().get(1).setMyHotelsId(idMocawisHotels);

            // --- Test rooms
            roomsMocawi.add(new Room("Habitación 1", 3,"Cama simple", 180000,"Descripción de la Habitación 1", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), imagesMocawi1, reservationsBYS.getHotels().get(0).getIdHotel()));
            roomsMocawi.add(new Room("Habitacion presidencial", 6,"Cama doble", 490000,"Descripción de la Habitación 2", new ServicesIncluded(true, true, true, true, false, true, true, true, true ), imagesMocawi2, reservationsBYS.getHotels().get(0).getIdHotel()));

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

    public ArrayList<String> listCities(){
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Arauca, Arauca");
        cities.add("Armenia, Quindío");
        cities.add("Barranquilla, Atlántico");
        cities.add("Bogotá, Cundinamarca");
        cities.add("Bucaramanga, Santander");
        cities.add("Cali, Valle del Cauca");
        cities.add("Cartagena, Bolívar");
        cities.add("Cúcuta, Norte de Santander");
        cities.add("Florencia, Caquetá");
        cities.add("Ibagué, Tolima");
        cities.add("Inírida, Guainía");
        cities.add("Leticia, Amazonas");
        cities.add("Manizales, Caldas");
        cities.add("Medellín, Antioquia");
        cities.add("Mitú, Vaupés");
        cities.add("Mocoa, Putumayo");
        cities.add("Montería, Córdoba");
        cities.add("Neiva, Huila");
        cities.add("Pasto, Nariño");
        cities.add("Pereira, Risaralda");
        cities.add("Popayán, Cauca");
        cities.add("Puerto Carreño, Vichada");
        cities.add("Quibdó, Chocó");
        cities.add("Riohacha, La Guajira");
        cities.add("San Andrés, Archipiélago de San Andrés, Providencia y Santa Catalina");
        cities.add("San José del Guaviare, Guaviare");
        cities.add("Santa Marta, Magdalena");
        cities.add("Sincelejo, Sucre");
        cities.add("Tunja, Boyacá");
        cities.add("Valledupar, Cesar");
        cities.add("Villavicencio, Meta");
        cities.add("Yopal, Casanare");
        return cities;
    }

    public ArrayList<String> listHotels(){
        ArrayList<String> hotels = new ArrayList<>();
        for(Hotel i: reservationsBYS.getHotels()){
            hotels.add(i.getName());
        }
        return hotels;
    }

    public ArrayList<String> listRangesPrices(){
        ArrayList<String> prices = new ArrayList<>();
        prices.add("Menos de $80.0000");
        prices.add("$80000 - $100000");
        prices.add("$100000 - $150000");
        prices.equals("$100000 - $150000");
        prices.add("Más de $150000");
        return prices;
    }

    public void printUsers(){
        ArrayList<User> usersList;
        usersList = reservationsBYS.getUsers();
        for (User u: usersList) {
            System.out.println( u.toString());
        }

    }

    public ArrayList<Room> getAllRooms(){
        ArrayList<Room> rooms = new ArrayList<>();

        for(Hotel ac: reservationsBYS.getHotels()){
            for (int i = 0; i< ac.getRooms().size(); i++) {
                rooms.add(ac.getRooms().get(i));
            }
        }
        return rooms;
    }

    public ArrayList<Hotel> getAllHotels(){
        return reservationsBYS.getHotels();
    }

    public Hotel getHotelByName(String hotelName) {
        Hotel hotel = null;
        for (Hotel i: reservationsBYS.getHotels()) {
            if(hotelName.equals(i.getName())){
                hotel = i;
            }
        }
        return hotel;
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
    public void createHotel(String name, String description, String location, ArrayList<Room> rooms) {
        reservationsBYS.createHotel(name, description, location, rooms);
    }

    @Override
    public void createReservation(String idAccommodation, String idRoom, String idDocumentationUser, LocalDate startDate, LocalDate endDate) throws Exception {
        reservationsBYS.createReservation(idAccommodation, idRoom, idDocumentationUser,startDate,endDate);
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

    //@Override
    public ArrayList<Hotel> listHotelsByIdHotel(ArrayList<String> listIdsHotels) {
        ArrayList<Hotel> listHotels = new ArrayList<>();
        for (Hotel h : reservationsBYS.getHotels()){
            for(int i = 0; i < listIdsHotels.size(); i++){
                if(h.getIdHotel().equals(listIdsHotels.get(i))){
                    listHotels.add(h);
                }
            }
        }
        return listHotels;
    }

    // Show alerts messages
    public void showAlert(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void navigateWindow(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Load the view
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Create the scene
            Scene scene = new Scene(root);

            // Create a new scenario (window)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Show the new window
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeWindow(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}