package co.edu.uniquindio.notas.modelo;

import java.util.ArrayList;

public class NotaPrincipal {

    private ArrayList<Nota> notas;

    public NotaPrincipal() {
        this.notas = new ArrayList<>();  // Inicializa la lista de notas
    }

    public void agregarNota(int idNota, String titulo, String categoria, String nota) {

      notas.add(new Nota(idNota, titulo, categoria, nota));
    }

    public ArrayList<Nota> listarNotas() {
        return this.notas;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}
