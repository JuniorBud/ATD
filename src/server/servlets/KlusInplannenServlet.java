package server.servlets;

import Domain.Klus;
import Domain.Monteur;
import Domain.Werkplaats;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KlusInplannenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Werkplaats werkplaats = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			werkplaats = (Werkplaats) sc.getAttribute("werkplaats");
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Calendar huidigedatumCal = Calendar.getInstance();
		int huidigeWeek = huidigedatumCal.get(Calendar.WEEK_OF_YEAR);
		int huidigeJaar = huidigedatumCal.get(Calendar.YEAR);

		ArrayList<Monteur> geselecteerdeMonteurs = new ArrayList<Monteur>();
		String[] monteurs = req.getParameter("monteurs").toString().split("_");
		for (int i = 0; i < monteurs.length; i++) {
			String[] monteur = monteurs[i].split("-");
			Monteur m = werkplaats.zoekMonteur(monteur[0], monteur[1], monteur[2]);
			geselecteerdeMonteurs.add(m);
		}

		boolean tijd = false;
		Klus k = werkplaats.getAlleOningeplandeWerkzaamheden().get(Integer.parseInt(req.getParameter("klus").toString()));
		boolean[] beschikbareTijden = werkplaats.zoekWeekplanning(huidigeWeek, huidigeJaar).getBeschikbareTijden(geselecteerdeMonteurs,
				Integer.parseInt(req.getParameter("dag").toString()));
		int halfuren = (int) Math.ceil(2 * k.getVerwachteUrenNodig() / geselecteerdeMonteurs.size());
		for (int i = 0; i < (beschikbareTijden.length - halfuren); i++) {
			boolean mogelijk = true;
			for (int j = 0; j < halfuren; j++) {
				if (!beschikbareTijden[i + j]) {
					mogelijk = false;
				}
			}
			if (mogelijk) {
				String txt = werkplaats.getTijd(i + 1, i + halfuren);
				if ((i + 1) < 8 && (i + halfuren) >= 8) {
					txt += " (+pauze)";
				}
				tijd = true;
				out.println("<option value='" + (i + 1) + "-" + (i + halfuren) + "'>" + txt + "</option>");
			}
		}
		if (!tijd) {
			out.println("<option>Geen mogelijkheid</option>");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Werkplaats werkplaats = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			werkplaats = (Werkplaats) sc.getAttribute("werkplaats");
		}

		Calendar huidigedatumCal = Calendar.getInstance();
		int huidigeWeek = huidigedatumCal.get(Calendar.WEEK_OF_YEAR);
		int huidigeJaar = huidigedatumCal.get(Calendar.YEAR);
		ArrayList<Monteur> geselecteerdeMonteurs = new ArrayList<Monteur>();
		for (Monteur m : werkplaats.getAlleMonteurs()) {
			String monteur = m.getNaam() + "-" + m.getTussenvoegsel() + "-" + m.getAchternaam();
			if (req.getParameter(monteur) != null) {
				geselecteerdeMonteurs.add(m);
			}
		}
		int dag = Integer.parseInt(req.getParameter("dag").toString());
		String[] tijden = req.getParameter("tijden").toString().split("-");
		Klus k = werkplaats.getAlleOningeplandeWerkzaamheden().get(Integer.parseInt(req.getParameter("klus").toString()));
		werkplaats.zoekWeekplanning(huidigeWeek, huidigeJaar).planKlusIn(k, geselecteerdeMonteurs, dag, Integer.parseInt(tijden[0]), Integer.parseInt(tijden[1]));
		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher("WeekplanningOverzicht.jsp");

		synchronized (sc) {
			sc.setAttribute("werkplaats", werkplaats);
		}
		rd.forward(req, resp);
	}
}