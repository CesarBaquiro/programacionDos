package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.controllers.MainController;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class Room{
    private String idRoom;
    private String name;
    private int capacity;
    private String typeBed;
    private float price; // Price for night
    private String description;
    private ServicesIncluded servicesIncluded;
    private ArrayList<String> images;
    // Nueva referencia al alojamiento
    private String idHotel;

    public Room( String name, int capacity, String typeBed, float price, String description, ServicesIncluded servicesIncluded, ArrayList<String> images, String idHotel) {
        this.idRoom = UUID.randomUUID().toString();
        this.name=name;
        this.capacity = capacity;
        this.typeBed = typeBed;
        this.price = price;
        this.description = description;
        this.servicesIncluded = servicesIncluded;
        this.images = images;
        this.idHotel = idHotel;
    }

    public Hotel getHotel() {
        MainController mainController = MainController.getInstancia();
        Hotel hotel = null;
        for (Hotel ac : mainController.getAllHotels()){
            if (ac.getIdHotel().equals(idHotel)){
                hotel = ac;
            }
        }
        return hotel;
    }
}
