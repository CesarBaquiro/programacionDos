package co.edu.uniquindio.clinica.controladores;


import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.collections.FXCollections;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;


public abstract class AbstractControlador {


    @Getter
    private Clinica clinica;

    public void inicializarClinica(Clinica clinica){
        this.clinica = clinica;
    }
}
