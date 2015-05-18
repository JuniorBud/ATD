package server.servlets;

import db.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Samuel on 20-4-2015.
 */
public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        /*
            * The input-validation will be handled client-side, to minimize network-traffic and server-load.
         */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String voornaam = req.getParameter("voornaam");
        String achternaam = req.getParameter("achternaam");
        String email = req.getParameter("email");
        String telnr = req.getParameter("telnr");
        String adres = req.getParameter("adres");
        String plaats = req.getParameter("plaats");
        RequestDispatcher rd = null;
        try {
            if (Database.registerUser(username, password, voornaam, achternaam, email, adres, plaats, telnr)) {
                //TODO: redirect with successful registration message here
            }
            else {
                //TODO: redirect with error message here
            }
        } catch (InvalidKeySpecException |NoSuchAlgorithmException | InstantiationException | IllegalAccessException ex) {
            //TODO: redirect with error message here
        }

    }

}
