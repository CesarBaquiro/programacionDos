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

    /**
     * Comprobar si hay espacios disponibles y retornar True si los hay
     * */
    public Boolean verificarAforoPorHora(String nombreInstalacion, String hora) {
        Boolean hayEspacio = false;
        int cantidadReservas = 0;
        cantidadReservas = contarReservasPorInstalacionHora(nombreInstalacion, hora);
        /*
        for (Accommodation i: accommodations){
            if(nombreInstalacion.equals(i.getName())){
                for (Reservation r : reservations) {
                    if(nombreInstalacion.equals(r.getIdReservation()) && cantidadReservas < i.getAforo()){
                        hayEspacio = true;
                    }
                }
            }
        }
        */
        return hayEspacio;
    }

    public int contarReservasPorInstalacionHora(String nombreInstalacion, String hora) {
        int cantidadReservas = 0;
        // Logica de contar
        return cantidadReservas;
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
    public void createAccommodation(String idAccommodation, String name, String description, String location, ArrayList<Room> rooms) {
        hotels.add(new Hotel(idAccommodation, name, description, location, rooms));
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
