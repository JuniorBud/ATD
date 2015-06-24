package server.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Auto;
import Domain.Facturatie;
import Domain.Factuur;
import Domain.Klant;
import Domain.Monteur;
import Domain.Weekplanning;
import Domain.Werkplaats;
import Domain.Klus;

public class FactuurBeheerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("FactuurOverzicht.jsp");
		ArrayList<Factuur> geselecteerdeFacturen = new ArrayList<Factuur>();

		Facturatie facturatie = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			facturatie = (Facturatie) sc.getAttribute("facturatie");
		}
		Object betalen = req.getParameter("betalen");
		Object blokkeer = req.getParameter("blokkeer");
		Object toevoegen = req.getParameter("toevoegen");
		Object eersteFactuur = req.getParameter("eersteFactuur");
		Object waarschuwing = req.getParameter("waarschuwing");
		
		if (betalen != null || blokkeer != null || eersteFactuur != null || waarschuwing != null ) {
			int i = 0;
			if (betalen != null){
				rd = req.getRequestDispatcher("BetalingInvoeren.jsp");
			}
			if (blokkeer != null){
				rd = req.getRequestDispatcher("KlantBlokkeren.do");
			}
			if (eersteFactuur != null){
				rd = req.getRequestDispatcher("EersteFactuur.do");
			}
			if (waarschuwing != null){
				rd = req.getRequestDispatcher("WaarschuwingsBrief.do");
			}

			for (Factuur f : facturatie.getAlleFacturen()) {

				if (req.getParameter("" + i) != null) {
					geselecteerdeFacturen.add(f);
				}
				i++;
			}
			if (geselecteerdeFacturen.size() == 0) {
				rd = req.getRequestDispatcher("FactuurOverzicht.jsp");
			}
			else {
				req.setAttribute("geselecteerdeFacturen", geselecteerdeFacturen);
			}
		}
		
		else if (toevoegen != null) {

			Werkplaats werkplaats = (Werkplaats) sc.getAttribute("werkplaats");
			Werkzaamheid werk;
			Object dag = req.getParameter("dag");
			Object dagDeel = req.getParameter("dagDeel");
			Object week = req.getParameter("week");
			Object jaar = req.getParameter("jaar");
			Monteur monteur = (Monteur) req.getSession().getAttribute("monteur");
			if (dag == null || dagDeel == null || week == null || jaar == null || monteur == null) {
				rd = req.getRequestDispatcher("/Werkplaats/WeekplanningOverzicht.jsp");
			} else {
				Weekplanning w = werkplaats.zoekWeekplanning(
						Integer.parseInt(week.toString()),
						Integer.parseInt(jaar.toString()));
				if (w == null) {
					rd = req.getRequestDispatcher("/Werkplaats/WeekplanningOverzicht.jsp");
					req.setAttribute("error", "2");
				} else {
					werk = w.getWerkzaamheid(monteur,
							Integer.parseInt(dag.toString()),
							Integer.parseInt(dagDeel.toString()));
					req.getSession().setAttribute("Klus", werk);
					if (werk == null) {
						rd = req.getRequestDispatcher("/Werkplaats/WeekplanningOverzicht.jsp");
						req.setAttribute("error",
								"3" + Integer.parseInt(dag.toString())
										+ Integer.parseInt(dagDeel.toString()));
					} else {
						req.setAttribute("WerkzaamheidFactuur", werk);
						rd = req.getRequestDispatcher("FactuurToevoegen.do");

						}
					}
				}

		}
		
		
			
			
			
			
			
		

		synchronized (sc) {
			sc.setAttribute("facturatie", facturatie);
		}

		rd.forward(req, resp);

	}

}
		
		
