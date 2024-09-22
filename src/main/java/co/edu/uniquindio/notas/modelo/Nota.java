package co.edu.uniquindio.notas.modelo;

import javafx.event.ActionEvent;

import java.time.LocalDateTime;

public class Nota {
    private String titulo;
    private String categoria;
    private String Nota;
    private String descripcion;
    private LocalDateTime fechaCreacion;

    NotaPrincipal notaPrincipal;

    public Nota(String titulo, String nota, String categoria) {
        this.titulo = titulo;
        this.Nota = nota;
        this.categoria = categoria;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNota() {
        return Nota;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString(){
        return "Titulo "+getTitulo()+ " Categoria "+getCategoria()+ " Nota "+getNota()+" Fecha "+getFechaCreacion();
    }

}
