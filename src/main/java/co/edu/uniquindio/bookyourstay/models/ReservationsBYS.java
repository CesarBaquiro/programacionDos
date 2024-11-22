package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import co.edu.uniquindio.bookyourstay.services.ServicesBookYourStay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservationsBYS implements ServicesBookYourStay {

    ArrayList<Reservation> reservations =   new ArrayList();
    ArrayList<User> users =   new ArrayList();
    ArrayList<Hotel> hotels =   new ArrayList();

    public int countReservationsByHotel(String hotelName) {
        int reservesQuantity = 0;
        // Logica de contar
        return reservesQuantity;
    }

    @Override
    public User login(String correo, String contrasena) throws Exception {
        for (User user : users) {
            if(user.getEmail().equals(correo) && user.getPassword().equals(contrasena)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void registerUser(String iDdocumentation, String fullname, String phone, Role role, String email, String password, String activationCode) throws Exception {
        users.add(new User(iDdocumentation, fullname, phone, role, email, password, activationCode));
    }

    @Override
    public void createHotel(String name, String description, String location, ArrayList<Room> rooms) {
        hotels.add(new Hotel(name, description, location, rooms));
    }

    public void createRoom(String name, int capacity, String typeBed, float price, String description, ServicesIncluded servicesIncluded, String idHotel) {
        ArrayList<String> images = new ArrayList<>();
        images.add("/fotoHotel1.png");
        for (Hotel h: hotels){
            if(h.getIdHotel().equals(idHotel)){
                h.addRoom(new Room(name, capacity, typeBed, price,description, servicesIncluded, images, idHotel));
            }
        }
    }

    @Override
    public void createReservation(String idAccommodation, String idRoom, String idDocumentationUser, LocalDate startDate, LocalDate endDate) throws Exception {
        reservations.add(new Reservation(idAccommodation,  idRoom, idDocumentationUser, startDate, endDate));
    }

    @Override
    public List<Reservation> listAllReservations() {
        return List.of();
    }

    @Override
    public List<Reservation> listReservationsByUser(String cedulaPersona) {
        return List.of();
    }
}
