package server.servlets;

import db.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Samuel on 20-4-2015.
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if (Database.verifyLogin(username, password)) {
                req.getSession().setAttribute("user", username);
                req.getSession().setAttribute("klant", username);
                req.getRequestDispatcher("...").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("...").forward(req, resp);
            }
            //TODO: The err-println will be a error message shown with the redirect
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Something went wrong whilst verifying the password: " + ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println("Something went horribly wrong, this is embarrassing");
        }
    }
}
