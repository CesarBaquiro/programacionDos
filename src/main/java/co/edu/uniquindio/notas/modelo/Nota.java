package co.edu.uniquindio.notas.modelo;

import java.time.LocalDateTime;

public class Nota {
    private int idNota = 0;
    private String titulo;
    private String categoria;
    private String Nota;
    private String descripcion;
    private LocalDateTime fechaCreacion;


    public Nota(String titulo, String nota, String categoria) {
        this.idNota++;
        this.titulo = titulo;
        this.Nota = nota;
        this.categoria = categoria;
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getIdNota() {return idNota;}

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
