package co.edu.uniquindio.bookyourstay.controllers;

import co.edu.uniquindio.bookyourstay.models.User;
import co.edu.uniquindio.bookyourstay.models.Session;

public class PanelAdminController {

    private final Session session = Session.getInstancia();
    User user = session.getUser();

}
