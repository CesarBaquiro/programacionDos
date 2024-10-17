package co.edu.uniquindio.clinica.controladores;


import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;

public class PanelControlador implements Initializable {


    @FXML
    private Pane panelPrincipal;


    private final Clinica clinica;


    public PanelControlador() {
        this.clinica = new Clinica(); // Se crea una única instancia de la clase Clinica
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registrosPaciente.fxml");


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
        Parent node = cargarPanel("/mostrarRegistroCita.fxml");
        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaCitas(ActionEvent actionEvent) {
        //Completar
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            ((AbstractControlador)loader.getController()).inicializarClinica(clinica);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
