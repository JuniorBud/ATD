package server;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Samuel on 20-4-2015.
 */
public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String voornaam = req.getParameter("voornaam");
        String achternaam = req.getParameter("achternaam");
        String email = req.getParameter("email");
        String telnr = req.getParameter("telnr");
        String adres = req.getParameter("adres");
        String plaats = req.getParameter("plaats");

        String msg = "";
        RequestDispatcher rd = null;
        /*
            * If the input is valid, the 'error'-String returned will be empty.
            * Otherwise, the RequestDispatcher will send the user back to the form
            * which will show the error message.
         */

        if (inputValid().length() == 0) {

        }
        else {

        }
    }
    public String inputValid() {
        String error = "";
        return error;
    }

}
