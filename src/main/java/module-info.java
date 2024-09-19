module co.edu.uniquindio.poo.programaciondos {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.programaciondos to javafx.fxml;
    exports co.edu.uniquindio.poo.programaciondos;
}