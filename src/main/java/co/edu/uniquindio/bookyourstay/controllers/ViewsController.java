package co.edu.uniquindio.bookyourstay.controllers;


import javafx.event.ActionEvent;


public class ViewsController {


    private final MainController mainController;


    public ViewsController() {
        mainController = MainController.getInstancia();
    }


    public void showLoginView(ActionEvent actionEvent) {
        mainController.navigateWindow("/login.fxml","Iniciar Sesión");
    }


    public void showRegisterView(ActionEvent actionEvent) {
        mainController.navigateWindow("/register.fxml", "Registro Persona");
    }
}
