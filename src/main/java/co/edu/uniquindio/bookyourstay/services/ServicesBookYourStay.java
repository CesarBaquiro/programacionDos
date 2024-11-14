package co.edu.uniquindio.bookyourstay.services;


import co.edu.uniquindio.bookyourstay.models.Room;
import co.edu.uniquindio.bookyourstay.models.Schedule;
import co.edu.uniquindio.bookyourstay.models.User;
import co.edu.uniquindio.bookyourstay.models.Reservation;
import co.edu.uniquindio.bookyourstay.models.enums.Role;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface ServicesBookYourStay {

    User login(String correo, String contrasena) throws Exception;

    void registerUser(String iDdocumentation, String fullname, String phone, Role role, String email, String password, String activationCode) throws Exception;

    void createAccommodation(String idAccommodation, String name, String description, String location, ArrayList<Room> rooms);

    void createReservation(String idInstalacion, String idDocumentationUser, String idAccommodation, String idRoom, LocalDate startDate, LocalDate endDate) throws Exception;

    List<Reservation> listAllReservations();


    List<Reservation> listReservationsByUser(String cedulaPersona);


}
