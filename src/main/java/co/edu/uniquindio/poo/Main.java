package co.edu.uniquindio.poo;
import co.edu.uniquindio.poo.Reserva;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo!");

        Reserva reserva = Reserva
                .builder()
                .destino("Cartagena")
                .build();

        System.out.println(reserva);

    }
}