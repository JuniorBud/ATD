package Domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Frits on 18-5-2015.
 */

public class Garage {
	private static ArrayList<Klant> alleKlanten = new ArrayList<Klant>();
	private static ArrayList<Template> alleTemplates = new ArrayList<Template>();
	private static Voorraad voorraad = new Voorraad();
	private static ArrayList<Klus> alleKlussen = new ArrayList<Klus>();
	private static ArrayList<Monteur> alleMonteurs = new ArrayList<Monteur>();
	private static ArrayList<Weekplanning> alleWeekplanningen = new ArrayList<Weekplanning>();

	public Garage() throws ParseException {
		vullenMetTestData();
		maakWeekplanning();
	}

	public static void maakWeekplanning() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int weeknum = cal.get(Calendar.WEEK_OF_YEAR);

		if (zoekWeekplanning(weeknum, year) == null) {
			Weekplanning wp = new Weekplanning(weeknum, year);
			voegWeekplanningToe(wp);
		}
	}

	public static void vullenMetTestData() throws ParseException {
		voegKlantToe(new Klant("Henk", "", "Sjaak", "01-01-1973", new Adres("Houtstraat", 1, "", "1521GH", "Utrecht"), new Auto("Peugeot", "506", "NH-56-KJ", 2001)));
		voegKlantToe(new Klant("Liam", "de", "Haas", "03-09-1994", new Adres("Pinkstraat", 15, "", "1541HC", "Koog aan de Zaan"), new Auto("BMW", "M5", "GHJ-23-K", 2013)));
		voegKlantToe(new Klant("Nathan", "van", "Nispen", "21-09-1993", new Adres("Verschuurstraat", 21, "", "7891OP", "Hilversum"), new Auto("Mazda", "626", "ODX-1-00", 1999)));
		voegKlantToe(new Klant("Frits", "", "Budding", "15-01-1993", new Adres("Bakkerstraat", 67, "", "4652AL", "Hilversum"), new Auto("Nissan", "GTR", "81-PVJ-1", 2012)));
		voegKlantToe(new Klant("Jasper", "is de", "Sjaak", "01-01-1973", new Adres("Houtstraat", 1, "a", "1658FG", "Utrecht"), new Auto("Suzuki", "Swift", "1-KBB-00", 2011)));

		getVoorraad().voegOnderdeelToe(new Onderdeel("Boutje nummer 12", 123456, 9, 8));
		getVoorraad().voegOnderdeelToe(new Onderdeel("Boutje nummer 13", 123457, 9, 11));
		getVoorraad().voegOnderdeelToe(new Onderdeel("Boutje nummer 14", 123458, 9, 12));
		getVoorraad().voegOnderdeelToe(new Onderdeel("Boutje nummer 15", 123459, 10, 13));
		getVoorraad().voegOnderdeelToe(new Onderdeel("Boutje nummer 16", 123460, 11, 14));

		getVoorraad().voegBrandstofToe(new Brandstof("LPG", 121, 99, 100, 1.5));
		getVoorraad().voegBrandstofToe(new Brandstof("Gas", 121, 100, 100, 1.5));
		getVoorraad().voegBrandstofToe(new Brandstof("Ongelood", 121, 101, 100, 1.5));
		getVoorraad().voegBrandstofToe(new Brandstof("Gas", 123, 80, 100, 1.5));
		getVoorraad().voegBrandstofToe(new Brandstof("LPG", 123, 75, 100, 1.5));

		getKlant("Henk", "", "Sjaak").voegAutoToe(new Auto("BMW", "M3", "1-KBC-01", 2013));

		getKlant("Liam", "de", "Haas").zoekAuto("GHJ-23-K").setLaatsteOnderhoud("03-09-2012");
		getKlant("Frits", "", "Budding").zoekAuto("81-PVJ-1").setLaatsteOnderhoud("03-10-2012");
		getKlant("Henk", "", "Sjaak").zoekAuto("1-KBC-01").setLaatsteOnderhoud("03-09-2011");
		getKlant("Henk", "", "Sjaak").zoekAuto("NH-56-KJ").setLaatsteOnderhoud("03-09-2010");

		voegMonteurToe(new Monteur("Japie", "van", "Oosten", "12-08-1990", new Adres("Bakkersweg", 12, "a", "4687DK", "Lutjebroek")));
		voegMonteurToe(new Monteur("Kees", "", "Havik", "05-06-1980", new Adres("Houtveldweg", 156, "", "4791KL", "Utrecht")));
		voegMonteurToe(new Monteur("Joop", "", "Zoetemelk", "19-02-1975", new Adres("Nijenoord", 1, "", "1354HJ", "Amersfoort")));
		voegMonteurToe(new Monteur("Menno", "de", "Bakker", "21-03-1989", new Adres("Oudenoord", 700, "", "1492RT", "Utrecht")));
		voegMonteurToe(new Monteur("Wiebrand", "", "Langenbach", "21-11-1960", new Adres("Dorpsstraat", 210, "", "1376HJ", "Utrecht")));

		Klus w1 = new Klus("Reparatie", getKlant("Henk", "", "Sjaak"), getKlant("Henk", "", "Sjaak").zoekAuto("NH-56-KJ"), "Achteras gebroken en hij is hellemaal kapot", 5.0);
		Klus w2 = new Klus("Onderhoud", getKlant("Liam", "de", "Haas"), getKlant("Liam", "de", "Haas").zoekAuto("GHJ-23-K"), "Kleine beurt", 4.0);
		Klus w3 = new Klus("Onderhoud", getKlant("Henk", "", "Sjaak"), getKlant("Henk", "", "Sjaak").zoekAuto("1-KBC-01"), "Grote beurt", 8.0);

		voegKlusToe(w1);
		voegKlusToe(w2);
		voegKlusToe(w3);

		ArrayList<Monteur> testMonteurs = new ArrayList<Monteur>();
		testMonteurs.add(zoekMonteur("Japie", "van", "Oosten"));

		Weekplanning wp1 = new Weekplanning(1, 2014);
		Weekplanning wp2 = new Weekplanning(1, 2013);
		voegWeekplanningToe(wp2);
		voegWeekplanningToe(wp1);
		
		for(int i = 2; i < 15;i++){
			Weekplanning wp = new Weekplanning(i, 2014);
			voegWeekplanningToe(wp);
        }

        wp1.planKlusIn(w1, testMonteurs, 1, 1, 3);

		testMonteurs.add(zoekMonteur("Wiebrand", "", "Langenbach"));

        zoekWeekplanning(1, 2014).planKlusIn(w3, testMonteurs, 1, 14, 16);

		voegTemplateToe("testTemplate", "Hallo [voornaam] [achternaam], bij deze willen wij u laten weten dat uw auto voor het laatst op [laatste_onderhoud] voor onderhoud is langsgeweest");
	}



    public static ArrayList<Klant> getKlanten() {
		return alleKlanten;
	}

	public static Klant getKlant(String vnm, String ts, String anm) {
		Klant klant = null;
		for (Klant k : alleKlanten) {
			if (k.getAchternaam().equals(anm) && k.getNaam().equals(vnm) && k.getTussenvoegsel().equals(ts)) {
				klant = k;
			}
		}
		return klant;
	}

	public static boolean verwijderKlant(String vnm, String ts, String anm) {
		boolean verwijdert = false;

		if (getKlant(vnm, ts, anm) != null) {
			alleKlanten.remove(getKlant(vnm, ts, anm));
			verwijdert = true;
		}
		return verwijdert;
	}

	public static boolean voegKlantToe(Klant klant) {
		boolean toegevoegd = false;

		if (getKlant(klant.getNaam(), klant.getTussenvoegsel(), klant.getAchternaam()) == null) {
			alleKlanten.add(klant);
			toegevoegd = true;
		}

		return toegevoegd;
	}

	public static Template zoekTemplate(String nm) {
		Template aanwezig = null;
		for (Template t : alleTemplates) {
			if (t.getTemplateNaam().equals(nm)) {
				aanwezig = t;
			}
		}
		return aanwezig;
	}

	public static boolean voegTemplateToe(String nm, String txt) {
		boolean templateAangemaakt = false;

		if (zoekTemplate(nm) == null) {
			alleTemplates.add(new Template(nm, txt));
			templateAangemaakt = true;
		}

		return templateAangemaakt;
	}

	public static boolean wijzigTemplate(String nm, String txt) {
		boolean gewijzigd = false;

		Template tp = zoekTemplate(nm);
		if (tp != null) {
			tp.setTemplateInhoud(txt);
			gewijzigd = true;
		}

		return gewijzigd;
	}

	public static boolean verwijderTemplate(String nm) {
		boolean templateVerwijdert = false;

		Template tp = zoekTemplate(nm);

		if (tp != null) {
			alleTemplates.remove(tp);
			templateVerwijdert = true;
		}

		return templateVerwijdert;
	}

	public static ArrayList<Template> getAlleTemplates() {
		return alleTemplates;
	}

	public static boolean genereerBrief(Klant k, String nm) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Template tp = zoekTemplate(nm);
		String txt = tp.getTemplateInhoud();
		String result = "";
		txt = txt.replaceAll("\\]", "\\[");
		String[] rs = txt.split("\\[");
		for (int i = 0; i < rs.length; i++) {
			if (i % 2 == 0) {
				result += rs[i];
			} else {
				result += tp.getGegeven(rs[i], k);
			}
		}

		try {
			File theDir = new File("brieven");
			if (!theDir.exists()) {
				theDir.mkdir();
			}
			String datum = dateFormat.format(Calendar.getInstance().getTime()).replaceAll("/", "-");
			FileWriter fw = new FileWriter("./brieven/" + tp.getTemplateNaam() + "brief" + "_" + k.getNaam() + k.getTussenvoegsel() + k.getAchternaam() + "_" + datum + ".txt");

			PrintWriter pw = new PrintWriter(fw);
			pw.print(result.replace("\n", System.lineSeparator()).replace("\r\n", System.lineSeparator()));
			pw.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		return true;
	}

	public static Voorraad getVoorraad() {
		return voorraad;
	}

	public static Monteur zoekMonteur(String vnm, String ts, String anm) {
		Monteur monteur = null;
		for (Monteur m : alleMonteurs) {
			if (m.getAchternaam().equals(anm) && m.getNaam().equals(vnm) && m.getTussenvoegsel().equals(ts)) {
				monteur = m;
			}
		}
		return monteur;
	}

	public static boolean verwijderMonteur(String vnm, String ts, String anm) {
		boolean verwijdert = false;

		if (zoekMonteur(vnm, ts, anm) != null) {
			alleMonteurs.remove(zoekMonteur(vnm, ts, anm));
			verwijdert = true;
		}
		return verwijdert;
	}

	public static ArrayList<Klus> getAlleOningeplandeWerkzaamheden() {
		ArrayList<Klus> result = new ArrayList<Klus>();
		for (Klus k : alleKlussen) {
			if (k.getMonteurs().size() == 0) {
				result.add(k);
			}
		}
		return result;
	}

	public static boolean voegMonteurToe(Monteur monteur) {
		boolean toegevoegd = false;

		if (zoekMonteur(monteur.getNaam(), monteur.getTussenvoegsel(), monteur.getAchternaam()) == null) {
			alleMonteurs.add(monteur);
			toegevoegd = true;
		}
		return toegevoegd;
	}

	public static Klus zoekKlus(Klant k, Auto a, String klusType, Date aangemaakt) {
		Klus kl = null;
		for (Klus klus: alleKlussen) {
			if (klus.getKlant().equals(k) && klus.getAuto().equals(a) && klus.getKlusType().equals(klusType) && klus.getAangemaakt().equals(aangemaakt)) {
				kl = klus;
			}
		}
		return kl;
	}

	public static boolean voegKlusToe(Klus k) {
		boolean toegevoegd = false;
		if (zoekKlus(k.getKlant(), k.getAuto(), k.getKlusType(), k.getAangemaakt()) == null) {
			alleKlussen.add(k);
			toegevoegd = true;
		}
		return toegevoegd;
	}

	public static boolean verwijderKlus(Klant k, Auto a, String klusType, Date aangemaakt) {
		boolean verwijdert = false;
		for (Klus kl : alleKlussen) {
			if (kl.getKlant().equals(k) && kl.getAuto().equals(a) && kl.getKlusType().equals(klusType) && kl.getAangemaakt().equals(aangemaakt)) {
				alleKlussen.remove(kl);
				verwijdert = false;
			}
		}
		return verwijdert;
	}

	public static Weekplanning zoekWeekplanning(int weeknr, int jaar) {
		Weekplanning wp = null;
		for (Weekplanning w : alleWeekplanningen) {
			if (w.getWeeknr() == weeknr && w.getJaar() == jaar) {
				wp = w;
			}
		}
		return wp;
	}

	public static boolean voegWeekplanningToe(Weekplanning w) {
		boolean toegevoegd = false;
		if (zoekWeekplanning(w.getWeeknr(), w.getJaar()) == null) {
			alleWeekplanningen.add(w);
			toegevoegd = true;
		}
		return toegevoegd;
	}

	public static ArrayList<Klant> getAlleKlanten() {
		return alleKlanten;
	}

	public static ArrayList<Klus> getAlleKlussen() {
		return alleKlussen;
	}

	public static ArrayList<Monteur> getAlleMonteurs() {
		return alleMonteurs;
	}

	public static ArrayList<Weekplanning> getAlleWeekplanningen() {
		return alleWeekplanningen;
	}
	
	public static Object[] getAlleJaren(){
		ArrayList<Integer> jaren = new ArrayList<Integer>();
		for(Weekplanning w:alleWeekplanningen){
			boolean found = false;
			for(Integer i:jaren){
				if(w.getJaar() == i){
					found = true;
				}
			}
			if(!found){
				jaren.add(w.getJaar());
			}
		}
		Object[] result = new Object[jaren.size()];
		for(int j = 0; j < result.length;j++){
			result[j] = "" + jaren.get(result.length - j -1);		
		}
		return result;
	}
	
	public static Object[] getAlleWeken(int jaar){
		ArrayList<Integer> weken = new ArrayList<Integer>();
		for(Weekplanning w:alleWeekplanningen){
			boolean found = false;
			for(Integer i:weken){
				if(w.getJaar() != jaar || w.getWeeknr() == i ){
					found = true;
				}
			}
			if(!found){
				weken.add(w.getWeeknr());
			}
		}
		Object[] result = new Object[weken.size()];
		for(int j = 0; j < result.length;j++){
			result[j] = "" + weken.get(result.length - j - 1);		
		}
		return result;
	}
}
