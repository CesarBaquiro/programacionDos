package co.edu.uniquindio.clinica.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    private LocalDate fecha;
    private String idFactura;
    private double total;
    private double subtotal;
}
