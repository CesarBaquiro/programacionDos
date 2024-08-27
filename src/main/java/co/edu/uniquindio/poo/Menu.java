package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Clase Menu para el control de la aplicacion y sus procesos
 */

public class Menu {

    // Se instancian las clases usadas
    Gimnasio gimnasio = new Gimnasio();
    Cliente cliente = new Cliente();



    // Se instancian recursos necesarios
    private static final Scanner scanner = new Scanner(System.in);
    LocalDateTime tiempoActual = LocalDateTime.now().withSecond(0).withNano(0);

    public void seleccionarMenu() {
        Integer opcion = 0;

        do {
            System.out.println("Hora: " + tiempoActual); // Imprime la hora actual
            // Mostrar el menú
            System.out.println("----------------Menú:--------------");
            System.out.println("1. Usuarios"); // Dentro lleva las opciones registrar, actualizar, eliminar
            System.out.println("2. Clases"); // Dentro lleva Busqueda, reservar
            System.out.println("3. Entrenamientos"); // Ver
            System.out.println("4. Reservas"); //  Dentro tiene ver, cancelar
            System.out.println("5. Reportes"); // Ver
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("----------------Opciones:--------------");
                    System.out.println("1. Registrar usuario");
                    System.out.println("2. Actualizar datos de usuario");
                    System.out.println("3. Eliminar usuario");
                    System.out.println("4. Ver listado de entrenadores");
                    Integer opcionUsuarios = scanner.nextInt();
                    if (opcionUsuarios == 1) { // Busqueda por entrenador
                        System.out.println("Registrando usuario");
                        cliente.registrarCliente();
                    } else if (opcionUsuarios == 2) {
                        System.out.println("Actualizando los datos de usuario");
                    } else if (opcionUsuarios == 3) {
                        System.out.println("Eliminando el usuario");

                    }else if (opcionUsuarios == 4) {
                        System.out.println("----Listado de entrenadores-----");
                        System.out.println(gimnasio.getListadoEntrenadores().toString());
                    }else {
                        System.out.println("Esta opcion no esta disponible");
                    }
                    break;
                case 2:
                    System.out.println("----------------Opciones de busqueda de clases:--------------");
                    System.out.println("1. Ver clases disponibles");
                    System.out.println("2. Buscar por nombre del entrenador");
                    System.out.println("3. Buscar por tipo de clase");
                    System.out.println("4. Buscar por horario");
                    System.out.print("Seleccione una opción: ");
                    Integer opcionClases = scanner.nextInt();
                    if(opcionClases == 1){
                        gimnasio.mostrarClasesDisponibles(gimnasio.getListadoClases());
                    } else if (opcionClases == 2) {
                        System.out.println("Buscando por nombre...");
                        //gimnasio.obtenerClase(null, 1, null, null, null);
                    }else if (opcionClases == 3) {
                        System.out.println("Buscando por tipo...");
                    }else if (opcionClases == 4) {
                        System.out.println("Buscando por horario...");
                    }
                    else {
                        System.out.println("Esta opcion no esta disponible");
                    }
                    break;
                case 3:
                    System.out.println("------------Entrenamientos disponibles:----------");
                    System.out.println(gimnasio.getListadoEntrenamientos().toString());
                    break;
                case 4:

                    break;
                case 5:

                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

        } while (opcion != 6);
        System.out.println("---Gracias por usar Gym System---");
        scanner.close();
    }
}
