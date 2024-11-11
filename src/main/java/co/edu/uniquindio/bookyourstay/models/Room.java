package co.edu.uniquindio.bookyourstay.models;

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

    public Room( String name, int capacity, String typeBed, float price, String description, ServicesIncluded servicesIncluded, ArrayList<Schedule> schedules){
        this.idRoom = UUID.randomUUID().toString();
        this.name=name;
        this.capacity = capacity;
        this.typeBed = typeBed;
        this.price = price;
        this.description = description;
        this.servicesIncluded = servicesIncluded;
        this.schedules = schedules;
    }


}
