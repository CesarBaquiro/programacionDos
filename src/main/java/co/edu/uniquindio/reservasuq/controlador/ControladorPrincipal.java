package co.edu.uniquindio.reservasuq.controlador;


import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControladorPrincipal implements ServiciosReservasUQ {


    private static ControladorPrincipal INSTANCIA;
    private final ReservasUQ reservasUQ;


    private ControladorPrincipal() {
        reservasUQ = new ReservasUQ();

        // Datos de prueba
        try {
            reservasUQ.registrarPersona( "1234",  "Cesar Administrador", TipoPersona.ADMIN, "cesar@gmail.com", "1212");
            reservasUQ.registrarPersona("4321", "Cesar Usuario", TipoPersona.ESTUDIANTE, "cesar2@gmail.com", "1212");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    public Boolean validarCorreo(String correo) {
        for(Persona p: reservasUQ.getPersonas()){
            System.out.println(p.getEmail());
            if(correo.equals(p.getEmail())){
                return true;
            }
        }
        System.out.println("No existe ese correo");
        return false;
    }

    public Boolean validarContrasena(String contrasena) {
        for (Persona p : reservasUQ.getPersonas()) {
            if (p.getPassword().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return reservasUQ.login(correo, contrasena);
    }


    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {
        reservasUQ.registrarPersona(cedula, nombre, tipoPersona, email, password);
    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        reservasUQ.crearInstalacion(nombre, aforo, costo, horarios);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }


    //TODO Completar con el resto de métodos necesarios para la aplicación
    public void mostrarAlerta(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {


            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();


            // Crear la escena
            Scene scene = new Scene(root);


            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);


            // Mostrar la nueva ventana
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}