package server.servlets;

import Domain.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class KlusServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Klantbinding klantbinding = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		RequestDispatcher rd = null;
		if ((req.getParameter("action").toString().equals("klant"))) {
			if ((req.getParameter("klant").toString().equals("nieuw"))) {
				out.println("<input type='text' name='voornaam' required><br />	");
				out.println("<input type='text' name='tussenvoegsel'><br />	");
				out.println("<input type='text' name='achternaam' required><br />	");
				out.println("<input type='text' name='geboortedatum' required><br />	");
				out.println("<input type='text' name='straatnaam' required><br />	");
				out.println("<input type='text' name='huisnummer' required><br />	");
				out.println("<input type='text' name='toevoeging'><br />	");
				out.println("<input type='text' name='postcode' required><br />		");
				out.println("<input type='text' name='woonplaats' required><br />	");
			} else {
				String[] klant = req.getParameter("klant").toString().split("-");
				Klant k = klantbinding.getKlant(klant[0], klant[1], klant[2]);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				out.println("<input type='text' name='voornaam' value='" + klant[0] + "' readonly><br />	");
				out.println("<input type='text' name='tussenvoegsel' value='" + klant[1] + "' readonly><br />	");
				out.println("<input type='text' name='achternaam' value='" + klant[2] + "' readonly><br />	");
				out.println("<input type='text' name='geboortedatum' value='" + dateFormat.format(k.getGeboortedatum()) + "' readonly><br />	");
				out.println("<input type='text' name='straatnaam' value='" + k.getAdres().getStraat() + "' readonly><br />	");
				out.println("<input type='text' name='huisnummer' value='" + k.getAdres().getHuisnummer() + "' readonly><br />	");
				out.println("<input type='text' name='toevoeging' value='" + k.getAdres().getToevoeging() + "' readonly><br />	");
				out.println("<input type='text' name='postcode' value='" + k.getAdres().getPostcode() + "' readonly><br />	");
				out.println("<input type='text' name='woonplaats' value='" + k.getAdres().getWoonplaats() + "' readonly><br />	");
			}
		} else if ((req.getParameter("action").toString().equals("auto"))) {
			if ((req.getParameter("auto").toString().equals("nieuw"))) {
				out.println("<input type='text' name='merk' required><br />	");
				out.println("<input type='text' name='model' required><br />	");
				out.println("<input type='text' name='kenteken' required><br />	");
				out.println("<input type='text' name='bouwjaar' required><br />	");
			} else {
				String[] klant = req.getParameter("klant").toString().split("-");
				Klant k = klantbinding.getKlant(klant[0], klant[1], klant[2]);
				Auto a = k.zoekAuto(req.getParameter("auto").toString());
				out.println("<input type='text' name='merk' value='" + a.getMerk() + "' readonly><br />	");
				out.println("<input type='text' name='model' value='" + a.getModel() + "' readonly><br />	");
				out.println("<input type='text' name='kenteken' value='" + a.getKenteken() + "' readonly><br />	");
				out.println("<input type='text' name='bouwjaar' value='" + a.getBouwjaar() + "' readonly><br />	");
			}
		} else {
			if ((req.getParameter("klant").toString().equals("nieuw"))) {
				out.println("<option value='nieuw'>Nieuwe Auto</option>");
			} else {
				String[] klant = req.getParameter("klant").toString().split("-");
				Klant k = klantbinding.getKlant(klant[0], klant[1], klant[2]);
				out.println("<option value='nieuw'>Nieuwe Auto</option>");
				for (Auto a : k.getAlleAutos()) {
					out.println("<option value='" + a.getKenteken() + "'>" + a.getKenteken() + "</option>");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Klantbinding klantbinding = null;
		Werkplaats werkplaats = null;

		ServletContext sc = getServletContext();

		synchronized (sc) {
			klantbinding = (Klantbinding) sc.getAttribute("klantbinding");
			werkplaats = (Werkplaats) sc.getAttribute("werkplaats");

		String validation = "";
		boolean correct = true;
		Klant k = null;
		Auto a = null;
		Klus kl = null;
		RequestDispatcher rd = null;
		String voornaam = req.getParameter("voornaam");
		String tussenvoegsel = req.getParameter("tussenvoegsel");
		String achternaam = req.getParameter("achternaam");
		String geboortedatum = req.getParameter("geboortedatum");
		String straat = req.getParameter("straatnaam");
		String huisnummer = req.getParameter("huisnummer");
		String toevoeging = req.getParameter("toevoeging");
		String postcode = req.getParameter("postcode");
		String woonplaats = req.getParameter("woonplaats");
		String opmerking = req.getParameter("opmerking");
		String manuren = req.getParameter("manuren");
		boolean nieuweKlant = false;
		if (req.getParameter("aangemaakt") != null) {
			if ((req.getParameter("klant").toString().equals("nieuw"))) {
				nieuweKlant = true;
				if (voornaam.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Voornaam mag niet leeg zijn";
				}
				if (achternaam.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Achternaam mag niet leeg zijn";
				}
				if (geboortedatum.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Geboortedatum mag niet leeg zijn";
				}
				if (straat.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Straatnaam mag niet leeg zijn";
				}
				if (isInt(huisnummer) == -1) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Voer een correct getal in bij huisnummer";
				}
				if (postcode.equals("") || !postcode.matches("^[1-9][0-9]{3}\\s?((?!SS|SA|SD)[a-zA-Z]{2})?$")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Postcode is niet juist ingevoerd";
				}
				if (woonplaats.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Woonplaats mag niet leeg zijn";
				}
			} else {
				String[] klant = req.getParameter("klant").toString().split("-");
				k = klantbinding.getKlant(klant[0], klant[1], klant[2]);
			}

			if ((req.getParameter("auto").toString().equals("nieuw"))) {
				String merk = req.getParameter("merk");
				String model = req.getParameter("model");
				String kenteken = req.getParameter("kenteken");
				String bouwjaar = req.getParameter("bouwjaar");
				if (merk.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Merk mag niet leeg zijn";
				}
				if (model.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Model mag niet leeg zijn";
				}
				if (kenteken.equals("")) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Kenteken mag niet leeg zijn";
				}
				if (isInt(bouwjaar) == -1) {
					if (!validation.equals("")) {
						validation += "</br>";
					}
					correct = false;
					validation += "Bouwjaar mag niet leeg zijn";
				}
				if (correct) {
					a = new Auto(merk, model,kenteken, Integer.parseInt(bouwjaar));
				}
			} else {
				a = k.zoekAuto(req.getParameter("auto").toString());
			}

			if (opmerking.equals("")) {
				if (!validation.equals("")) {
					validation += "</br>";
				}
				correct = false;
				validation += "Opmerking mag niet leeg zijn";
			}

			if (correct) {
				if (nieuweKlant) {
					Adres adres = new Adres(straat, Integer.parseInt(huisnummer), toevoeging, postcode, woonplaats);
					k = new Klant(voornaam, tussenvoegsel, achternaam, geboortedatum, adres, a);
				}
				kl = new Klus(req.getParameter("type").toString(), k, a, opmerking, Double.parseDouble(manuren));
				werkplaats.voegKlusToe(kl);
				req.setAttribute("success", "De werkzaamheid is succesvol aan het systeem toegevoegd");
				rd = req.getRequestDispatcher("WeekplanningOverzicht.jsp");
			} else {
				req.setAttribute("validation", validation);
				rd = req.getRequestDispatcher("KlusAanmaken.jsp");
			}

				sc.setAttribute("werkplaats", werkplaats);
				if(nieuweKlant){
					klantbinding = (Klantbinding)sc.getAttribute("klantbinding");
					klantbinding.voegKlantToe(k);
					sc.setAttribute("klantbinding", klantbinding);
				}
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
