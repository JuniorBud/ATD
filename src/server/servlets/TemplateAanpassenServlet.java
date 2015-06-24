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



public class TemplateAanpassenServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String foutieveTemplates = "TemplateAanpassen.jsp";
		boolean foutieveTemplatesAanwezig = false;
		String msgs = "";
		RequestDispatcher rd = null;

		Klantbinding klantbinding = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
		}

		for (Template t : klantbinding.getAlleTemplates()) {
			String templateInhoud = req.getParameter(t.getTemplateNaam());
			if (templateInhoud != null) {
				boolean tagsTest = Template.checkTags(templateInhoud);

				t.setTemplateInhoud(templateInhoud);

				if (!tagsTest) {
					msgs += "Template '" + t.getTemplateNaam() + "' bevat ongeldige tags<br>";
					foutieveTemplates = foutieveTemplates + "?" + t.getTemplateNaam() + "=on";
					foutieveTemplatesAanwezig = true;
				}
			}
		}

		if (foutieveTemplatesAanwezig) {
			rd = req.getRequestDispatcher(foutieveTemplates);
		} else {
			msgs = "Aanpassen van de templates is gelukt";
			rd = req.getRequestDispatcher("TemplateOverzicht.jsp");
		}

		synchronized (sc) {
			sc.setAttribute("klantbinding", klantbinding);
		}

		req.setAttribute("msgs", msgs);
		rd.forward(req, resp);
	}

}
