package co.edu.uniquindio.bookyourstay.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Reservation {
    String idReservation;
    String idUser;
    String idAccommodation;
    String idRoom;
    LocalDate reservationDate;
    String reservationHour;
}
