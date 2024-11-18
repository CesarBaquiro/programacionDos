package co.edu.uniquindio.bookyourstay.models;

import co.edu.uniquindio.bookyourstay.models.enums.Role;
import lombok.*;

import java.util.ArrayList;


@Getter
@Setter

public class User {
    String iDdocumentation;
    String fullname;
    String phone;
    Role role;
    String email;
    String password;
    String activationCode;
    ArrayList<String> myHotelsId;

    public User(String iDdocumentation, String fullname, String phone, Role role, String email, String password, String activationCode) {
        this.iDdocumentation = iDdocumentation;
        this.fullname = fullname;
        this.phone = phone;
        this.role = role;
        this.email = email;
        this.password = password;
        this.activationCode = activationCode;
        if(role.equals(Role.HOTELIER)) {
            myHotelsId = new ArrayList<>();
        }else{
            myHotelsId = null;
        }
    }

    @Override
    public String toString() {
        String msg = "";
        if(role.equals(Role.USER)) {
            msg = "User [iDdocumentation=" + iDdocumentation + ", fullname=" + fullname + ", phone=" + phone + ", role=" + role.toString() + ", email=" + email + ", password=" + password + ", activationCode=" + activationCode + "]";

        } else if (role.equals(Role.HOTELIER)) {
            msg = "User [iDdocumentation=" + iDdocumentation + ", fullname=" + fullname + ", phone=" + phone + ", role=" + role.toString() + ", email=" + email + ", password=" + password + ", activationCode=" + activationCode + ", hotels="+ myHotelsId + "]";

        }
        return msg;
    }
}
