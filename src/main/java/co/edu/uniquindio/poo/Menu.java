package co.edu.uniquindio.poo;

import javax.xml.transform.stream.StreamSource;
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
            System.out.println("\n" + "----------------Menú:--------------");
            System.out.println("1. Usuarios"); // Dentro lleva las opciones registrar, actualizar, eliminar
            System.out.println("2. Clases"); // Dentro lleva Busqueda, reservar
            System.out.println("3. Entrenamientos"); // Ver
            System.out.println("4. Reservas"); // Dentro tiene ver, cancelar
            System.out.println("5. Reportes"); // Ver
            System.out.println("6. Salir" + "\n");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n" + "----------------Opciones:--------------");
                    System.out.println("1. Registrar cliente");
                    System.out.println("2. Actualizar datos de cliente");
                    System.out.println("3. Eliminar cliente");
                    System.out.println("4. Ver listado de entrenadores" + "\n");
                    System.out.print("Seleccione una opción: ");
                    Integer opcionUsuarios = scanner.nextInt();

                    if (opcionUsuarios == 1) {
                        System.out.println("\n" + "----------------Registrando cliente--------------");
                        gimnasio.getListadoClientes().add(gimnasio.registrarCliente());
                        //para imprimir la lista despues
                        //System.out.println(gimnasio.getListadoClientes().toString()); 
                    } else if (opcionUsuarios == 2) {
                        System.out.println("\n" + "Actualizando los datos de cliente");
                    } else if (opcionUsuarios == 3) {
                        System.out.println("\n" + "Eliminación de cliente");
                        gimnasio.eliminarCliente();
                    } else if (opcionUsuarios == 4) {
                        System.out.println("\n" + "----Listado de entrenadores-----");
                        System.out.println(gimnasio.getListadoEntrenadores().toString());
                    } else {
                        System.out.println("\n" + "Esta opcion no esta disponible");
                    }
                    break;
                case 2:
                    System.out.println("----------------Opciones de busqueda de clases:--------------");
                    System.out.println("1. Ver clases disponibles");
                    System.out.println("2. Buscar por cedula del entrenador");
                    System.out.println("3. Buscar por tipo de clase");
                    System.out.println("4. Buscar por horario" + "\n");
                    System.out.print("Seleccione una opción: ");
                    Integer opcionClases = scanner.nextInt();
                    if (opcionClases == 1) {
                        gimnasio.mostrarClasesDisponibles(gimnasio.getListadoClases());
                    } else if (opcionClases == 2) {
                        System.out.println("Buscando por cedula de entrenador...");
                        System.out.println(" - Ingrese la cédula");
                        String cedulaEntrenador = scanner.next();
                        System.out.println(gimnasio.buscarClasePorCedulaEntrenador(cedulaEntrenador));
                    }else if (opcionClases == 3) {
                        System.out.println("Buscando por tipo...");
                        System.out.println(" - Ingrese el tipo de clase que desea");
                        System.out.println("1. Rumbaterapia");
                        System.out.println("2. Aerobicos");
                        System.out.println("3. Resistencia");
                        System.out.println("4. Fuerza");
                        System.out.println("5. Yoga");
                        Integer opcionTipo = scanner.nextInt();
                        gimnasio.mostrarClasesTodas(gimnasio.buscarClasePorTipo(opcionTipo));
                    }else if (opcionClases == 4) {
                        System.out.println("Buscando por horario...");
                    } else {
                        System.out.println("Esta opcion no esta disponible");
                    }
                    break;
                case 3:
                    System.out.println("------------Entrenamientos disponibles:----------");
                    System.out.println(gimnasio.getListadoEntrenamientos().toString());
                    break;
                case 4:
                    System.out.println("\n" + "----------------Opciones:--------------");
                    System.out.println("1. Ver reservas");
                    System.out.println("2. Registrar reserva");
                    System.out.println("3. Cancelar reserva");
                    Integer opcionReserva = scanner.nextInt();
                    if (opcionReserva == 1) {
                        gimnasio.mostrarReservasActivas(gimnasio.getListadoReservas());
                    }else if(opcionReserva == 2) {
                        System.out.println("Ingrese el ID de la clase");
                        String idClase = scanner.next();
                        System.out.println("Ingrese la cedula del cliente");
                        String cedulaCliente = scanner.next();
                        gimnasio.registrarReserva(idClase, cedulaCliente);
                    }else if (opcionReserva == 3) {
                        System.out.println("Ingrese la cedula del cliente");
                        String cedulaCliente = scanner.next();
                        gimnasio.cancelarReserva(cedulaCliente);
                    }else{
                        System.out.println("Esta opcion no esta disponible");
                    }

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
