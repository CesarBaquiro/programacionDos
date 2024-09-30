package co.edu.uniquindio.contacts.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class contactsApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(contactsApp.class.getResource("/inicio.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle("Contactos");
        stage.show();
    }



    public static void main(String[] args) {
        launch(contactsApp.class, args);

    }
}
