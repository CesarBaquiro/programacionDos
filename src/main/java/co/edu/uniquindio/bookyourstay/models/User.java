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
    String activationCode;

    @Override
    public String toString() {
        return "User [iDdocumentation=" + iDdocumentation + ", fullname=" + fullname + ", phone=" + phone + ", role="+ role.toString() + ", email="+email+", password="+password+", activationCode="+activationCode+"]";
    }
}
