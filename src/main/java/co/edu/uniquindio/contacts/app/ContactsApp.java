package co.edu.uniquindio.contacts.app;

import co.edu.uniquindio.contacts.controllers.ContactController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ContactsApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(ContactsApp.class.getResource("/index.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle("Contactos");
        stage.show();
    }

    public static void main(String[] args) {
        launch(ContactsApp.class, args);

    }
}
