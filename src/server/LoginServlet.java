package server;

import db.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Samuel on 20-4-2015.
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        RequestDispatcher rd = null;
        try {
            if (Database.verifyLogin(username, password)) {
                System.out.println("hey there!");
                //TODO: session management here!
            }
            else {

            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Something went wrong whilst verifying the password: " + ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("Something went horribly wrong, this is embarrassing");
        }
    }
}
