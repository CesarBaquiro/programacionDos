package co.edu.uniquindio.notas.controladores;

import co.edu.uniquindio.notas.modelo.Nota;
import co.edu.uniquindio.notas.modelo.NotaPrincipal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class InicioControlador implements Initializable {

    private final NotaPrincipal notaPrincipal;

    public InicioControlador() {
        notaPrincipal = new NotaPrincipal();
        tablaNotas = new TableView<>();
    }

    @FXML
    private TableView<Nota> tablaNotas;

    @FXML
    private Button btnNuevaNota;

    @FXML
    private TableColumn<Nota, String> colTitulo;

    @FXML
    private TableColumn<Nota, String> colCategoria;

    @FXML
    private TableColumn<Nota, String> colTexto;

    @FXML
    private TableColumn<Nota, String> colFecha;

    @FXML
    private TableColumn<Nota, Void> editButton;

    @FXML
    private TableColumn<Nota, Void> deleteButton;

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
            validarFormulario(); //Validar para crear nueva nota

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
        boolean esValido = true;

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
            int indice = notaPrincipal.getNotas().size();
            notaPrincipal.agregarNota(indice, txtTitulo.getText(), txtNota.getText(), txtCategoria.getText());
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

    private void agregarBotonEditar() {
        // Usar un CellFactory para crear el botón en cada fila
        Callback<TableColumn<Nota, Void>, TableCell<Nota, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Nota, Void> call(final TableColumn<Nota, Void> param) {
                final TableCell<Nota, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Editar");

                    {
                        // Acción del botón Editar
                        btn.setOnAction((ActionEvent event) -> {
                            btnNuevaNota.setText("Editar");
                            Nota nota = getTableView().getItems().get(getIndex());
                            txtTitulo.setText(nota.getTitulo());
                            txtCategoria.setText(nota.getCategoria());
                            txtNota.setText(nota.getNota());
                            btnNuevaNota.setOnAction((ActionEvent e) -> {
                                System.out.println("Editando nota: " + nota.getTitulo());
                                btnNuevaNota.setText("Crear nueva nota");
                            });

                            // Aquí puedes añadir lógica para editar la nota
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        editButton.setCellFactory(cellFactory);
    }

    private void agregarBotonEliminar() {
        // Usar un CellFactory para crear el botón en cada fila
        Callback<TableColumn<Nota, Void>, TableCell<Nota, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Nota, Void> call(final TableColumn<Nota, Void> param) {
                final TableCell<Nota, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Eliminar");

                    {
                        // Acción del botón Eliminar
                        btn.setOnAction((ActionEvent event) -> {
                            Nota nota = getTableView().getItems().get(getIndex());
                            notaPrincipal.getNotas().remove(nota.getIdNota());
                            tablaNotas.getItems().remove(nota.getIdNota());
                            // Aquí puedes añadir lógica para eliminar la nota
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        deleteButton.setCellFactory(cellFactory);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //boxCategorias.getItems().addAll("Tarea", "Trabajo", "Examen", "Reunion");

        // Agregar el botón de Editar
        agregarBotonEditar();

        // Agregar el botón de Eliminar
        agregarBotonEliminar();

        //colId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdNota())));
        colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colTexto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNota()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCreacion().toString()));

    }

}
