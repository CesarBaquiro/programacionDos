package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.observer.Observer;
import co.edu.uniquindio.bookyourstay.observer.ObserverWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class VerifyCodeController extends ObserverWindow{

    @FXML
    public TextField txtCode;

    private Observer observer;
    private final MainController mainController;

    public VerifyCodeController() {
        this.mainController = MainController.getInstancia();
    }

    @Override
    public void setObservador(Observer observer) {
        this.observer = observer;
    }

    // Método que se ejecuta al presionar el botón verificar
    @FXML
    public void verify(ActionEvent event) {
        String codeInput = txtCode.getText(); // Obtener el código ingresado
        // Notificar al observador (RegisterController) con el código ingresado
        if (observer != null) {
            observer.storeReceivedCode(codeInput);
            mainController.cerrarVentana(txtCode); // Cerrar la ventana después de la verificación
        }
    }

}
