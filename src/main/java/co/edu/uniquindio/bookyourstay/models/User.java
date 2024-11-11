package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String iDdocumentation;
    String fullname;
    String phone;
    Role role;
    String email;
    String password;
}
