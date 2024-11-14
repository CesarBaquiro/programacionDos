package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.controllers.MainController;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<Schedule> schedules;
    private ArrayList<String> images;
    // Nueva referencia al alojamiento
    private String idAccommodation;

    public Room( String name, int capacity, String typeBed, float price, String description, ServicesIncluded servicesIncluded, ArrayList<Schedule> schedules, ArrayList<String> images, String idAccommodation) {
        this.idRoom = UUID.randomUUID().toString();
        this.name=name;
        this.capacity = capacity;
        this.typeBed = typeBed;
        this.price = price;
        this.description = description;
        this.servicesIncluded = servicesIncluded;
        this.schedules = schedules;
        this.images = images;
        this.idAccommodation = idAccommodation;
    }

    public Accommodation getAccommodationByIdAccommodation() {
        MainController mainController = MainController.getInstancia();
        Accommodation accommodation = null;
        for (Accommodation ac : mainController.getAllAccommodations()){
            if (ac.getIdAccommodation().equals(idAccommodation)){
                accommodation = ac;
            }
        }
        return accommodation;
    }
}
