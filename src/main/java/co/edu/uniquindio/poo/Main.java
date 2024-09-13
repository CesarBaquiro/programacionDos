package co.edu.uniquindio.poo;


import javax.annotation.processing.SupportedSourceVersion;
import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo!");

        LocalDateTime now = LocalDateTime.now();

        Reserva reserva1 = Reserva.builder()
                .destino("Cartagena")
                .build();

        Reserva reserva2 = Reserva.builder()
                .destino("Tabogo")
                .fechaSalida(LocalDateTime.parse("2024-09-20T18:50"))
                .build();

        System.out.println(now);
        System.out.println(reserva1);
        System.out.println(reserva2);

    }
}