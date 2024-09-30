package co.edu.uniquindio.contacts.app;

import co.edu.uniquindio.contacts.controllers.contactController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class contactsApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(contactsApp.class.getResource("/index.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle("Contactos");
        stage.show();

        System.out.println("---CONTACTOS---");
        new contactController();
    }

    public static void main(String[] args) {
        launch(contactsApp.class, args);

    }
}
