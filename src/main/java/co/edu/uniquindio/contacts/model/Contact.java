package co.edu.uniquindio.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private LocalDateTime birthday;
    private String urlPhoto;

    @Override
    public String toString() {
        return "Contact {"+getName()+", "+getLastname()+", "+getPhone()+", "+getEmail()+", "+getBirthday()+", "+getUrlPhoto()+"}";
    }
}
