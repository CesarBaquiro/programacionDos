package co.edu.uniquindio.notas.controladores;

import co.edu.uniquindio.notas.modelo.Nota;
import co.edu.uniquindio.notas.modelo.NotaPrincipal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class InicioControlador implements Initializable {

    private final NotaPrincipal notaPrincipal;


    public InicioControlador() {
        notaPrincipal = new NotaPrincipal();
    }

    @FXML
    private TableView<Nota> tablaNotas;

    @FXML
    private TableColumn<Nota, String> colTitulo;

    @FXML
    private TableColumn<Nota, String> colCategoria;

    @FXML
    private TableColumn<Nota, String> colTexto;

    @FXML
    private TableColumn<Nota, String> colFecha;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextArea txtNota;

    @FXML
    private Label mensajeErrorTitulo;

    @FXML
    private Label mensajeErrorNota;

    @FXML
    private Label mensajeErrorCategoria;

    public void crearNota(ActionEvent e){
        try {
            // El metodo validar formulario maneja la verificacion de los datos
            validarFormulario();
        }catch (Exception ex){
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){

        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    @FXML
    public void validarFormulario() {
        // Obtener el valor del campo de texto
        String titulo = txtTitulo.getText();
        String nota = txtNota.getText();
        String categoria = txtCategoria.getText();
        Boolean esValido = true;

        // ------------ Validaciones -----------

        // Validar que no esté vacío
        if (titulo == null || titulo.trim().isEmpty()) {
            mensajeErrorTitulo.setText("El titulo no puede estar vacío.");
            esValido= false;
        } else if (titulo.length() < 3) {
            // Validar longitud mínima
            mensajeErrorTitulo.setText("El titulo debe tener al menos 3 caracteres.");
            esValido= false;
        }else{
            mensajeErrorTitulo.setText("");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            mensajeErrorCategoria.setText("La categoria no puede estar vacía.");
            esValido= false;
        } else if (categoria.length() < 3) {
            mensajeErrorCategoria.setText("La categoria debe tener al menos 3 caracteres.");
            esValido= false;
        }else{
            mensajeErrorCategoria.setText("");
        }

        if (nota == null || nota.trim().isEmpty()) {
            mensajeErrorNota.setText("La nota no puede estar vacía.");
            esValido= false;
        } else {
            mensajeErrorNota.setText("");
        }

        if(esValido){
            notaPrincipal.agregarNota(txtTitulo.getText(), txtNota.getText(), txtCategoria.getText());
            mostrarAlerta("Nota creada correctamente", Alert.AlertType.INFORMATION);
            // Si la validación es exitosa, limpiar el mensaje de error
            mensajeErrorTitulo.setText("");
            mensajeErrorNota.setText("");
            mensajeErrorCategoria.setText("");

            tablaNotas.setItems( FXCollections.observableArrayList(notaPrincipal.listarNotas()) );

            txtTitulo.clear();
            txtCategoria.clear();
            txtNota.clear();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colTexto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNota()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCreacion().toString()));


    }

}
