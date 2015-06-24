package server.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Klant;
import Domain.Klantbinding;

public class GenereerBrievenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Klantbinding klantbinding = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
		}

		if (req.getParameter("template") != null) {
			for (int i = 0; i < klantbinding.getAlleKlanten().size(); i++) {
				Klant k = klantbinding.getAlleKlanten().get(i);
				if (req.getParameter("" + i) != null) {
					klantbinding.genereerBrief(k, req.getParameter("template"));
				}
			}
		}

		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher("BrievenGenereren.jsp");

		synchronized (sc) {
			sc.setAttribute("klantbinding", klantbinding);
		}

		rd.forward(req, resp);
	}
}
