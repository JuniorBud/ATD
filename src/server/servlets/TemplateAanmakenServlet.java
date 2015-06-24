package server.servlets;

import Domain.Klantbinding;
import Domain.Template;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TemplateAanmakenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String templateNaam = req.getParameter("templateNaam");
		String templateTekst = req.getParameter("templateTekst");

		Klantbinding klantbinding = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
		}

		RequestDispatcher rd = null;

		boolean tagsTest = Template.checkTags(templateTekst);
		boolean naamTest = klantbinding.zoekTemplate(templateNaam) == null;

		String msgs = "";

		if (!tagsTest || !naamTest) {
			if (!tagsTest)
				msgs = msgs + "Er zitten ongeldige tags in<br>";

			if (!naamTest)
				msgs = msgs + "Template Bestaat al, kies een andere naam<br>";
		} else {
			Template template = new Template(templateNaam, templateTekst);
			if (klantbinding.voegTemplateToe(template)) {
				msgs = msgs + "Aanmaken van de template " + templateNaam + " is Gelukt";
			} else {
				msgs = msgs + "Aanmaken van de template " + templateNaam + " is NIET gelukt";
			}
		}

		rd = req.getRequestDispatcher("TemplateOverzicht.jsp");
		req.setAttribute("msgs", msgs);

		synchronized (sc) {
			sc.setAttribute("klantbinding", klantbinding);
		}

		rd.forward(req, resp);
	}

}
