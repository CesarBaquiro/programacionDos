package co.edu.uniquindio.bookyourstay.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Reservation {
    String idReservation;
    String idUser; // ID documentation
    String idAccommodation;
    String idRoom;
    LocalDate startDate; // Date of entry to the reserve
    LocalDate endDate; // Departure date of reservation
    int numberNightsReserved;

    public Reservation(String idReservation, String idUser, String idAccommodation, String idRoom, LocalDate startDate, LocalDate endDate) {
        this.idReservation = idReservation;
        this.idUser = idUser;
        this.idAccommodation = idAccommodation;
        this.idRoom = idRoom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberNightsReserved = (int) ChronoUnit.DAYS.between(startDate, endDate);;
    }
}
