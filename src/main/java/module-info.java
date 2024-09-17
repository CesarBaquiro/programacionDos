module co.edu.uniquindio.poo.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens co.edu.uniquindio.poo to javafx.fxml;
    exports co.edu.uniquindio.poo;
}