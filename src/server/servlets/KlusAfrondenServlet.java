package server.servlets;

import Domain.Klus;
import Domain.Onderdeel;
import Domain.Voorraad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class KlusAfrondenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		Voorraad voorraad = null;
		ServletContext sc = getServletContext();

		synchronized (sc) {
			voorraad = (Voorraad) sc.getAttribute("voorraad");
		}

		if (req.getParameter("afgerond") != null) {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			String opmerking = req.getParameter("opmerking");
			double manuren = Double.parseDouble(req.getParameter("manuren"));
			ArrayList<Onderdeel> onderdelenGebruikt = new ArrayList<Onderdeel>();
			ArrayList<Integer> aantalGebruikt = new ArrayList<Integer>();

			String validation = "";
			boolean correct = true;
			boolean opmerkingGevuld = true;
			for (Onderdeel o : voorraad.getAlleOnderdelen()) {
				if (isInt(req.getParameter("" + o.getOnderdeelNummer())) == -1) {
					correct = false;
					validation += "Er is geen correct getal ingevoerd bij "
							+ o.getOnderdeelOmschrijving() + ".</br>";
				} else if(Integer.parseInt(req.getParameter("" + o.getOnderdeelNummer())) > 0){
					onderdelenGebruikt.add(o);
					aantalGebruikt.add(Integer.parseInt(req.getParameter("" + o.getOnderdeelNummer())));
				}
				if (isInt(req.getParameter("" + o.getOnderdeelNummer())) > o.getOnderdeelVoorraad()) {
					correct = false;
					validation += "U heeft meer van "
							+ o.getOnderdeelOmschrijving()
							+ " gebruikt dan er in de voorraad zijn.</br>";
				}
			}

			if (opmerking == "" || opmerking == null) {
				correct = false;
				opmerkingGevuld = false;
				validation += "Er is geen opmerking ingevuld.</br>";
			}

			if (!correct) {
				rd = req.getRequestDispatcher("KlusAfronden.jsp");
				req.setAttribute("validation", validation);
			} else {
				for (Onderdeel o : onderdelenGebruikt) {
					o.setOnderdeelVoorraad((Integer.parseInt(o.getOnderdeelVoorraad() + "")) - (Integer.parseInt(req.getParameter("" + o.getOnderdeelNummer()))));
				}
				Klus k = (Klus) req.getSession().getAttribute("klus");
				k.setGebruikteOnderdelen(onderdelenGebruikt,aantalGebruikt);
				k.setOpmerkingAfronden(opmerking);
				k.setManuren(manuren);
				int eindtijd = (int) (k.getBeginTijd() - 1 + ((manuren * 2) / k.getMonteurs().size()));
				k.setEindTijd(eindtijd);
				rd = req.getRequestDispatcher("WeekplanningOverzicht.jsp");
				req.setAttribute("success", "Klus succesvol afgerond.");
			}

			synchronized (sc) {
				sc.setAttribute("voorraad", voorraad);
			}
			rd.forward(req, resp);
		}
	}

	private int isInt(String s) {
		int i;
		if (!s.equals("")) {
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException exc) {
				i = -1;
			}
		} else {
			i = -1;
		}
		return i;
	}
}
