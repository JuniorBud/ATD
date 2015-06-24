package server.servlets;

import Domain.Klantbinding;
import Domain.Template;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TemplateVerwijderenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String msgs = "";
		ArrayList<String> teVerwijderenTemplates = new ArrayList<String>();

		Klantbinding klantbinding = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
		}

		for (Template t : klantbinding.getAlleTemplates()) {
			String template = req.getParameter(t.getTemplateNaam());
			if (template != null) {
				teVerwijderenTemplates.add(t.getTemplateNaam());
				msgs += "Template " + t.getTemplateNaam() + " succesvol verwijdert<br>";
			}
		}

		for (String s : teVerwijderenTemplates) {
			klantbinding.verwijderTemplate(s);
		}

		RequestDispatcher rd = req.getRequestDispatcher("TemplateOverzicht.jsp");
		req.setAttribute("msgs", msgs);

		synchronized (sc) {
			sc.setAttribute("klantbinding", klantbinding);
		}

		rd.forward(req, resp);
	}
}
