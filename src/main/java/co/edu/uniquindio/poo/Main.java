package co.edu.uniquindio.poo;


import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        LocalDateTime tiempoActual = LocalDateTime.now().withSecond(0).withNano(0);

        // Se intancian las clases usadas
        Menu menu = new Menu();

        System.out.println(tiempoActual); // Imprime la hora actual
        menu.seleccionarMenu();
    }
}