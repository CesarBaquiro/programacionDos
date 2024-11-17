package co.edu.uniquindio.bookyourstay.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Hotel {
    String idHotel;
    String name;
    String description;
    String location;
    ArrayList<Room> rooms;

    // Method to bring all rooms
    public ArrayList<Room> getRooms(){
        return new ArrayList<>(rooms);
    }
}
