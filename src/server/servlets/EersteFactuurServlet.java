package server.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Adres;
import Domain.Factuur;
import Domain.Klant;
import Domain.Onderdeel;

public class EersteFactuurServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		ArrayList<Factuur> facturen = (ArrayList<Factuur>)req.getAttribute("geselecteerdeFacturen");
		
		
		
		
		
		new File("Facturen").mkdirs();
		
		
		for (Factuur f : facturen){
			DecimalFormat df = new DecimalFormat("#.00");
			Klant k = f.getKlant();
			Adres a = k.getAdres();
			int dag = f.getDatum().get(Calendar.DAY_OF_MONTH);
			int maand = f.getDatum().get(Calendar.MONTH) + 1;
			int jaar = f.getDatum().get(Calendar.YEAR);
			int vervaldag = f.getVervalDatum().get(Calendar.DAY_OF_MONTH);
			int vervalmaand = f.getVervalDatum().get(Calendar.MONTH) + 1;
			int vervaljaar= f.getVervalDatum().get(Calendar.YEAR);
			double prijsZonderBtw = f.getKosten()/1.21;
			double btw = f.getKosten() - prijsZonderBtw;
			ArrayList<Onderdeel> onderdelen = f.getGebruikteOnderdelen();
			ArrayList<Integer> onderdelenAantal = f.getHoeveelGebruikt();
			PrintWriter out = new PrintWriter("Facturen/" + k.getNaam() + "_" + k.getAchternaam()+ "_" + dag + "-" + maand + "-" + jaar + "_Factuur.txt");
			out.println("                                                                 			AutoTotaalDiensten");
			out.println("                                                                 			OsseWagenDreef 23");
			out.println("                                                                 			4214 XC, Utrecht");
			out.println("");
			out.println("			                                                                KvK: 00000000");
			out.println("			                                                                BTW: Nl00000000B01");
			out.println("			                                                                Bank: 123456789");
			out.println("");
			out.println("");
			out.println("");
			out.println("");
			out.println("");
			out.println(k.getNaam() + " " + k.getAchternaam());
			out.println(a.getStraat() + " " + a.getHuisnummer());
			out.print(a.getPostcode() + " " + a.getWoonplaats());
			out.println("");
			out.println("");
			out.println("");
			out.println("");
			out.println("");
			out.println("                                                                 FactuurDatum: " + dag + "-" + maand + "-" + jaar);
			out.println("                                                                 VervalDatum: " + vervaldag + "-" + vervalmaand + "-" + vervaljaar);
			out.println("");
			out.println("");
			out.println("			Omschrijving				Bedrag				Totaal			btw");
			out.println("-------------------------------------------------------------------------------------------------------------");
			int i = 0;
			for (Onderdeel o : onderdelen){
				double totaal = onderdelenAantal.get(i) * o.getOnderdeelKosten();
			out.println(onderdelenAantal.get(i) + "			" + o.getOnderdeelOmschrijving() + "			"+ df.format(o.getOnderdeelKosten()) + "				"+ df.format(totaal) + "			21%" );	
			i++;
			}
			out.println(f.getAantalManuren()+ "			manuren					" + 45 + "				"+ f.getAantalManuren()*45 + "			21%");
			out.println("-------------------------------------------------------------------------------------------------------------");
			out.println("						subtotaal			" + df.format(prijsZonderBtw));
			out.println("						btw 19%				" + df.format(btw));
			out.println("					   -------------------------------------------------");
			out.println("						Totaal				" + df.format(f.getKosten()));
			
			
			
			
			
			out.close();
		}
		
		
		rd = req.getRequestDispatcher("/Facturatie/FactuurOverzicht.jsp");
		rd.forward(req, resp);
		

	}
	
	
	
	
	
	
	
	
	

}
