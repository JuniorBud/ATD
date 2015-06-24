package server.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Facturatie;
import Domain.Factuur;
import Domain.Klantbinding;
import Domain.Onderdeel;
import Domain.Weekplanning;
import Domain.Werkplaats;
import Domain.Klus;

public class FactuurToevoegenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		Klantbinding klantbinding = null;
		Facturatie facturatie = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
			facturatie = (Facturatie) sc.getAttribute("facturatie");
		}

		ArrayList<Factuur> alleFacturen = facturatie.getAlleFacturen();
		

		RequestDispatcher rd = null;

		Werkzaamheid werk = (Werkzaamheid)req.getAttribute("WerkzaamheidFactuur");
		werk.setHeeftFactuur(true);
		ArrayList<Onderdeel> gebruikteOnderdelen = werk.getGebruikteOnderdelen();
		ArrayList<Integer> hoeveelGebruikt = werk.getGebruikteOnderdelenAantallen();
		
		
		
		Werkplaats werkplaats = (Werkplaats)sc.getAttribute("werkplaats");
		
		Weekplanning weekplanning = werkplaats.zoekWerkzaamheidWeekplanning(werk);
		
		
		
		
		Date date = werk.getAangemaakt();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, weekplanning.getWeeknr());
		cal.set(Calendar.YEAR, weekplanning.getJaar());
		cal.set(Calendar.DAY_OF_WEEK, werk.getDag());
		
		Factuur factuur = new Factuur(cal, werk.getKlant(), gebruikteOnderdelen, hoeveelGebruikt, werk.getManuren());
		
		factuur.berekenKosten(werk.getManuren());
		

		
	
			alleFacturen.add(factuur);
	
		
		
		rd = req.getRequestDispatcher("/Facturatie/FactuurOverzicht.jsp");
		
		synchronized (sc) {
			sc.setAttribute("klantbinding", klantbinding);
			sc.setAttribute("facturatie", facturatie);
		}
		rd.forward(req, resp);
	}

}
