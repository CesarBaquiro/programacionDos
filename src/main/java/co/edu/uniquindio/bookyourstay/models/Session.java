package co.edu.uniquindio.bookyourstay.models;
import co.edu.uniquindio.bookyourstay.controllers.MainController;
import lombok.Getter;
import lombok.Setter;

public class Session {

    public static Session INSTANCIA;

    @Getter @Setter
    private User user;
    private final MainController mainController;

    private Session() {
        this.mainController = MainController.getInstancia();
    }

    public static Session getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Session();
        }
        return INSTANCIA;
    }

    public void cerrarSesion() {
        user = null;
        mainController.navigateWindow("/home.fxml", "Iniciar sesión");
    }
}
