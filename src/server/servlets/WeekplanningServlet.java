package server.servlets;

import Domain.Klus;
import Domain.Monteur;
import Domain.Weekplanning;
import Domain.Werkplaats;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class WeekplanningServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Werkplaats werkplaats = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			werkplaats = (Werkplaats) sc.getAttribute("werkplaats");
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		if ((req.getParameter("action").toString().equals("planning"))) {
			int week = Integer.parseInt(req.getParameter("week").toString());
			int jaar = Integer.parseInt(req.getParameter("jaar").toString());
			String[] monteur = req.getParameter("monteur").toString().split("-");
			out.println("<tr>");
			out.println("<th></th>");
			out.println("<th>Maandag</th>");
			out.println("<th>Dinsdag</th>");
			out.println("<th>Woensdag</th>");
			out.println("<th>Donderdag</th>");
			out.println("<th>Vrijdag</th>");
			out.println("</tr>");
			for (int i = 0; i < 18; i++) {
				String[] dagen = new String[5];
				for (int j = 0; j < 5; j++) {
					int k = i + 1;
					if (i > 8) {
						k -= 2;
					}
					if (i == 7 && j == 1) {
						dagen[j] = "<td class='blue' colspan='5' rowspan='2'>Pauze</td>";
					} else if (i == 8 || (i == 7 && j != 1)) {
						dagen[j] = "";
					} else {
						Monteur m = werkplaats.zoekMonteur(monteur[0], monteur[1], monteur[2]);
						req.getSession().setAttribute("monteur", m);
						Weekplanning p = werkplaats.zoekWeekplanning(week, jaar);
						Klus kl = p.getKlus(m, j + 1, k);
						if (kl != null) {
							int rowspan = 1;
							if (kl.getBeginTijd() == k || (k == 8 && (kl.getEindTijd() > 7 && kl.getBeginTijd() < 7))) {
								if (kl.getEindTijd() > 7 && kl.getBeginTijd() < 7) {
									if (k == 8) {
										rowspan = kl.getEindTijd() - 7;
									} else {
										rowspan = 8 - kl.getBeginTijd();
									}
								} else {
									rowspan = kl.getEindTijd() + 1 - kl.getBeginTijd();
								}
								String clas = (kl.getManuren() > 0.0) ? "green" : "yellow";

								dagen[j] = "<td class='" + clas + "' rowspan='" + rowspan + "'><a href='/THO4/Werkplaats/KlusInfo.jsp?week=" + week
										+ "&jaar=" + jaar + "&dag=" + (j + 1) + "&dagDeel=" + k + "' class='_" + rowspan + "'>";
								dagen[j] += kl.getKlusType();
								if (kl.getAfspraak() == null) {
									dagen[j] += " - " + kl.getAuto().getKenteken();
								}
								dagen[j] += "</a></td>";
							} else {
								dagen[j] = "";
							}
						} else {
							dagen[j] = "<td></td>";
						}
					}
				}
				out.println("<tr>");
				out.println("<td class='orange'>" + werkplaats.getHalfUur(i + 1) + "</td>");
				for (int j = 0; j < dagen.length; j++) {
					out.println(dagen[j]);
				}
				out.println("</tr>");
			}
		} else {
			int jaar = Integer.parseInt(req.getParameter("jaar").toString());
			for (Object o : werkplaats.getAlleWeken(jaar)) {
				out.println("<option>" + o.toString() + "</option>");
			}
		}
	}
}
