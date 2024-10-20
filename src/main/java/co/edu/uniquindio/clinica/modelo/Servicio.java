package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {
    private Double precio;
    private String idServicio;
    private String nombre;
}
