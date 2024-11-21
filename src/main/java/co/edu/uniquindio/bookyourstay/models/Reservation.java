package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.controllers.MainController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Reservation {
    String idReservation;
    String idHotel;
    String idUser; // ID documentation
    String idRoom;
    LocalDate startDate; // Date of entry to the reserve
    LocalDate endDate; // Departure date of reservation
    int numberNightsReserved;

    private MainController mainController;

    public Reservation(String idHotel, String idRoom, String idUser, LocalDate startDate, LocalDate endDate) {
        this.mainController = MainController.getInstancia();

        this.idReservation = UUID.randomUUID().toString();
        this.idHotel = idHotel;
        this.idRoom = idRoom;
        this.idUser = idUser;
        this.startDate = startDate;
        this.endDate = endDate;
        // Calculate reserved nights
        this.numberNightsReserved = (int) ChronoUnit.DAYS.between(startDate, endDate);;
    }

    public Hotel getHotel() {
        for (Hotel h: mainController.getAllHotels()){
            if (h.getIdHotel().equals(idHotel)){
                return h;
            }
        }
        return null;
    }

    public Room getRoom() {
        Hotel h = getHotel();
        for (Room r: h.getRooms()){
            if (r.getIdRoom().equals(idRoom)){
                return r;
            }
        }
        return null;
    }

}
