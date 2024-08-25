package co.edu.uniquindio.poo;

public class Entrenamiento {
    // Atributos para la clase de entrenamiento
    private TipoEjercicio tipoEjercicio;
    private Integer duracion;
    private Integer caloriasQuemadas;

    // Constructor que recibe todos los parametros
    public Entrenamiento(TipoEjercicio tipoEjercicio, Integer duracion, Integer caloriasQuemadas) {
        this.tipoEjercicio = tipoEjercicio;
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
    }

    // Constructor que no recibe los parametros
    public Entrenamiento() {
    }

    // Getters and setters
    public TipoEjercicio getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(TipoEjercicio tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(Integer caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

}
