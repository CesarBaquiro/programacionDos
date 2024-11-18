package co.edu.uniquindio.bookyourstay.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class Hotel {
    String idHotel;
    String name;
    String description;
    String location;
    ArrayList<Room> rooms;

    public Hotel(String name, String description, String location, ArrayList<Room> rooms) {
        this.idHotel = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.location = location;
        this.rooms = rooms;
    }

    // Method to bring all rooms
    public ArrayList<Room> getRooms(){
        return new ArrayList<>(rooms);
    }
}
