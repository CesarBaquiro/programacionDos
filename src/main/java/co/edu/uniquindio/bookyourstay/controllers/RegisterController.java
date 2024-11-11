package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public TextField txtCedula;

    @FXML
    public TextField txtNombre;

    @FXML
    private ComboBox<String> comboBoxTipoPersona;

    @FXML
    public TextField txtCorreo;

    @FXML
    public TextField txtPassword;

    private final MainController mainController;

    public RegisterController() {
        this.mainController = MainController.getInstancia();
    }

    public void registro(ActionEvent actionEvent) {

        try {

            String idDocumentation = txtCedula.getText();
            String fullname = txtNombre.getText();
            String phone = "Terminar esto";
            //String phone = txtPhone.getText();
            String email = txtCorreo.getText();
            Role role = Role.USER;
            String password = txtPassword.getText();

            // Cambiar throws a try catch
            mainController.registerUser(idDocumentation, fullname, phone, role, email, password);

            mainController.cerrarVentana(txtCorreo);
            mainController.navigateWindow("/login.fxml", "Iniciar sesion");
        } catch (Exception e) {
            mainController.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxTipoPersona.setItems( FXCollections.observableList(mainController.listarTiposPesonas()) );
    }
}
