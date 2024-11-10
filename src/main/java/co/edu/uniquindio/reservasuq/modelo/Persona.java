package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    String cedula;
    String nombre;
    TipoPersona tipoPersona;
    String email;
    String password;
}
