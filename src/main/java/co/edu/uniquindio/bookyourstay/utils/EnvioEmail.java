package co.edu.uniquindio.bookyourstay.utils;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.Random;
import java.util.UUID;

public class EnvioEmail {

    public static String createCodeActivation() {
        // Generar un número aleatorio de 6 dígitos (100000 a 999999)
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);

        // Convertir el número a String y retornarlo
        return String.valueOf(codigo);
    }

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {

        Email email = EmailBuilder.startingBlank()
                .from("cmbm.fk@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "cmbm.fk@gmail.com", "vopd lsuc grro sskn")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}