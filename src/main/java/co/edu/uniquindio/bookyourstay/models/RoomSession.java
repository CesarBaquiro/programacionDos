package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.controllers.MainController;

public class RoomSession {

    private final MainController mainController;

    public static RoomSession INSTANCIA;
    private Room selectedRoom;

    // Constructor privado
    private RoomSession() {
        this.mainController = MainController.getInstancia();
    }

    // Método para obtener la instancia única de RoomSession
    public static RoomSession getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new RoomSession();
        }
        return INSTANCIA;
    }

    // Obtener la habitación seleccionada
    public Room getSelectedRoom() {
        return selectedRoom;
    }

    // Establecer la habitación seleccionada
    public void setSelectedRoom(Room room) {
        this.selectedRoom = room;
    }
}
