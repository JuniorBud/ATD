package server.servlets;

import Domain.Parkeergarage;
import Domain.Reservering;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ReserveringAanmakenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Parkeergarage parkeergarage = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			parkeergarage = (Parkeergarage) sc.getAttribute("parkeergarage");
		}

		String naam = req.getParameter("naam");
		String kenteken = req.getParameter("kenteken");

		String beginDatum = req.getParameter("beginDatum");
		String eindDatum = req.getParameter("eindDatum");

		int beginTijd = isInt(req.getParameter("beginTijd").replace(":", ""));
		int eindTijd = isInt(req.getParameter("eindTijd").replace(":", ""));

		boolean invalide = req.getParameter("invalide") != null;

		Date binnenDatum = null;
		Date wegDatum = null;

		SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);

		try {
			binnenDatum = df.parse(beginDatum);
			wegDatum = df.parse(eindDatum);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		int parkeerplaats = Integer.parseInt(req.getParameter("plek"));
		RequestDispatcher rd = null;

		Reservering reservering = new Reservering(naam, kenteken, binnenDatum, beginTijd, wegDatum, eindTijd, invalide, parkeerplaats);
		parkeergarage.voegReserveringToe(reservering);
		req.setAttribute("success", "De werkzaamheid is succesvol aan het systeem toegevoegd");
		rd = req.getRequestDispatcher("ReserveringOverzicht.jsp");

		synchronized (sc) {
			sc.setAttribute("parkeergarage", parkeergarage);
		}

		rd.forward(req, resp);
	}

	private int isInt(String s) {
		int i;
		if (!s.equals("") || !s.equals(null)) {
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
