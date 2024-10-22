package co.edu.uniquindio.clinica.controladores;


import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;

import static co.edu.uniquindio.clinica.modelo.Clinica.getInstanciaClinica;

public class PanelControlador {

    @FXML
    private Pane panelPrincipal;

    Clinica clinica;

    // Crear un ComboBox
    @FXML
    private ComboBox<String> comboBoxSuscripcion;


    public PanelControlador() {
        this.clinica = getInstanciaClinica();
        System.out.println("Clinica guardada en el panel: " + clinica.hashCode());
    }



    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registrarPaciente.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");
        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarRegistroCita(ActionEvent actionEvent) {
        //Completar
        Parent node = cargarPanel("/registrarCita.fxml");
        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaCitas(ActionEvent actionEvent) {
        //Completar
        //Completar
        Parent node = cargarPanel("/listarCitas.fxml");
        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            ((AbstractControlador)loader.getController()).inicializarClinica(clinica);
            //System.out.println(clinica.hashCode()); // Comprobar el hascode de la clinica instanciada
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
