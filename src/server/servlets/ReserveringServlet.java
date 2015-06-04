package server.servlets;

import Domain.Parkeergarage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ReserveringServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Parkeergarage parkeergarage = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			parkeergarage = (Parkeergarage) sc.getAttribute("parkeergarage");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<String> beschikbarePlekken = null;

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String beginDatumString = req.getParameter("beginDatum");
		String eindDatumString = req.getParameter("eindDatum");
		String begintijdString = req.getParameter("beginTijd").replace(":", "");
		String eindTijdString = req.getParameter("eindTijd").replace(":", "");
		boolean invalide = Boolean.parseBoolean(req.getParameter("invalide"));

		Date beginDatum = new Date();
		Date eindDatum = new Date();
		int beginTijd = 0;
		int eindTijd = 0;

		try {
			if (!beginDatumString.equals(""))
				beginDatum = dateFormat.parse(beginDatumString);
			if (!eindDatumString.equals(""))
				eindDatum = dateFormat.parse(eindDatumString);
			if (!begintijdString.equals(""))
				beginTijd = Integer.parseInt(begintijdString);
			if (!eindTijdString.equals(""))
				eindTijd = Integer.parseInt(eindTijdString);
			beschikbarePlekken = parkeergarage.getBeschikbarePlekken(beginDatum, eindDatum, beginTijd, eindTijd, invalide);
		} catch (ParseException exc) {
			exc.printStackTrace();
			out.println("<option value='fout'>" + exc.getMessage() + "</option>");
		}

		if (beschikbarePlekken == null) {
			out.println("<option value='null'>Geen plekken beschikbaar</option>");
		} else {

			for (String s : beschikbarePlekken) {
				out.println("<option value='" + s + "'>" + s + "</option>");
			}
		}

	}
}
