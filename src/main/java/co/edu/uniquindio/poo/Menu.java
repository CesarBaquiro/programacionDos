package co.edu.uniquindio.poo;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Clase Menu para el control de la aplicacion y sus procesos
 */

public class Menu {

    // Se intancian las clases usadas
    Gimnasio gimnasio = new Gimnasio();
    Usuario usuario = new Usuario();

    private static final Logger LOG = Logger.getLogger(Menu.class.getName());
    private static final Scanner scanner = new Scanner(System.in);


    public void seleccionarMenu() {
        Integer opcion = 0;

        do {
            // Mostrar el menú
            System.out.println("----------------Menú:--------------");
            System.out.println("1. Usuarios"); //Dentro lleva las opciones registrar, actualizar, eliminar
            System.out.println("2. Clases"); // Dentro lleva Busqueda, reservar
            System.out.println("3. Reservas"); // Dentro tiene ver, cancelar
            System.out.println("4. Entrenamientos"); // Ver por cedula
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
                    Integer opcionUsuarios = scanner.nextInt();
                    if(opcionUsuarios == 1){ // Busqueda por entrenador
                        System.out.println("Registrando usuario");
                        usuario.registrarUsuario();
                    } else if (opcionUsuarios == 2) {
                        System.out.println("Actualizando los datos de usuario");
                    } else if (opcionUsuarios == 3) {
                        System.out.println("Eliminando el usuario");
                    }else{
                        System.out.println("Esta opcion no esta disponible");
                    }
                    break;
                case 2:


                    System.out.println("----------------Opciones de busqueda de clases:--------------");
                    System.out.println("1. Nombre del entrenador");
                    System.out.println("2. Tipo de clase");
                    System.out.println("3. Horario");
                    System.out.print("Seleccione una opción: ");
                    gimnasio.obtenerClase(null, 1, null, null, null);
                    break;
                case 3:

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




