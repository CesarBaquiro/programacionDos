package co.edu.uniquindio.notas.modelo;

import java.util.ArrayList;

public class NotaPrincipal {

    private ArrayList<Nota> notas;

    public NotaPrincipal() {
        this.notas = new ArrayList<>();  // Inicializa la lista de notas
    }

    public void agregarNota(String titulo, String categoria, String nota) {

      notas.add(new Nota(titulo, categoria, nota));
    }

    public ArrayList<Nota> listarNotas() {
        return this.notas;
    }

}
