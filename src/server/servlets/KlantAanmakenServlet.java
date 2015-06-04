package server.servlets;

import Domain.Klant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "KlantAanmakenServlet")
public class KlantAanmakenServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String voornaam = req.getParameter("voornaam");
		String achternaam = req.getParameter("achternaam");
		String wachtwoord = req.getParameter("wachtwoord");
		String adres = req.getParameter("adres");
		String woonplaats = req.getParameter("woonplaats");
		String telefoonnummer = req.getParameter("telefoonnummer");
		String email = req.getParameter("email");
		
		if(voornaam != null && achternaam != null && wachtwoord != null && adres != null && woonplaats != null && email != null && telefoonnummer != null){
			Klant k = new Klant(voornaam,achternaam,wachtwoord,adres,woonplaats,email,telefoonnummer);
			Klant kl = (Klant)this.getServletContext().getAttribute("Klanten");
			kl.voegKlantToe(k);
			
			RequestDispatcher rd = req.getRequestDispatcher("Start.jsp");
			rd.forward(req,resp);
		}
		else{
			req.setAttribute("message","Niet alle velden zijn ingevuld!");
			RequestDispatcher rd = req.getRequestDispatcher("KlantAanmaken.jsp");
			rd.forward(req,resp);
			
		}
	}
}
