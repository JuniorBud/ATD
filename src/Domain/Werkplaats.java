package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;




public class Werkplaats implements Serializable{
	private ArrayList<Klus>	alleKlussen	= new ArrayList<Klus>();
	private ArrayList<Monteur>		alleMonteurs		= new ArrayList<Monteur>();
	private ArrayList<Weekplanning>	alleWeekplanningen	= new ArrayList<Weekplanning>();


	/**
	 * 
	 * Zoekt een monteur uit ArrayList alleMonteurs.
	 * 
	 * @param vnm
	 *            Voornaam van de monteur.
	 * @param ts
	 *            Eventuele tussenvoegsel van de monteur.
	 * @param anm
	 *            Achternaam van de monteur.
	 * @return Geeft de monteur.
	 */
	public Monteur zoekMonteur(String vnm, String ts, String anm) {
		Monteur monteur = null;
		for (Monteur m : alleMonteurs) {
			if (m.getAchternaam().equals(anm) && m.getNaam().equals(vnm)
					&& m.getTussenvoegsel().equals(ts)) {
				monteur = m;
			}
		}
		return monteur;
	}

	/**
	 * 
	 * Verwijderd monteur uit ArrayList alleMonteurs.
	 * 
	 * @param vnm
	 *            Voornaam van de monteur.
	 * @param ts
	 *            Eventuele tussenvoegsel van de monteur.
	 * @param anm
	 *            Achternaam van de monteur.
	 * @return Bevestigt het verwijderen van de monteur.
	 */
	public boolean verwijderMonteur(String vnm, String ts, String anm) {
		boolean verwijdert = false;

		if (zoekMonteur(vnm, ts, anm) != null) {
			alleMonteurs.remove(zoekMonteur(vnm, ts, anm));
			verwijdert = true;
		}
		return verwijdert;
	}

	/**
	 * 
	 * Haalt een lijst met nog niet ingeplande werkzaamheden.
	 * 
	 * @return Geeft een lijst met nog niet ingeplande werkzaamheden terug.
	 */
	public ArrayList<Klus> getAlleOningeplandeWerkzaamheden() {
		ArrayList<Klus> result = new ArrayList<Klus>();
		for (Klus k : alleKlussen) {
			if (k.getMonteurs().size() == 0) {
				result.add(k);
			}
		}
		return result;
	}

	/**
	 * 
	 * Voegt een nieuwe monteur toe aan ArrayList alleMonteurs.
	 * 
	 * @param monteur
	 *            Instantie van nieuwe object Monteur.
	 * @return Geeft een bevestiging van het toevoegen van de monteur.
	 */
	public boolean voegMonteurToe(Monteur monteur) {
		boolean toegevoegd = false;

		if (zoekMonteur(monteur.getNaam(), monteur.getTussenvoegsel(),
				monteur.getAchternaam()) == null) {
			alleMonteurs.add(monteur);
			toegevoegd = true;
		}
		return toegevoegd;
	}

	/**
	 * 
	 * Zoekt een Klus uit ArrayList alleWerkzaamheden.
	 * 
	 * @param k
	 *            Intantie van object Klant.
	 * @param a
	 *            Instantie van object Auto.
	 * @param KlusType
	 *            Type Klus (Reparatie of onderhoud).
	 * @param aangemaakt
	 *            Datum van wanneer de Klus is aangemaakt.
	 * @return Geeft de Klus terug.
	 */
	public Klus zoekKlus(Klant k, Auto a,
			String KlusType, Date aangemaakt) {
		Klus kl = null;
		for (Klus klus : alleKlussen) {
			if (klus.getKlant().equals(k) && klus.getAuto().equals(a)
					&& klus.getKlusType().equals(KlusType)
					&& klus.getAangemaakt().equals(aangemaakt)) {
				kl = klus;
			}
		}
		return kl;
	}
    
	public boolean voegKlusToe(Klus k) {
		boolean toegevoegd = false;
		alleKlussen.add(k);
		toegevoegd = true;
		return toegevoegd;
	}

	/**
	 * 
	 * Verwijderd een Klus uit ArrayList alleKlussen.
	 * 
	 * @param k
	 *            Intantie van object Klant.
	 * @param a
	 *            Instantie van object Auto.
	 * @param KlusType
	 *            Type Klus (Reparatie of onderhoud).
	 * @param aangemaakt
	 *            Datum van wanneer de Klus is aangemaakt.
	 * @return Geeft een bevestiging van het verwijderen van de Klus.
	 */
	public boolean verwijderKlus(Klant k, Auto a,
			String KlusType, Date aangemaakt) {
		boolean verwijdert = false;
		for (Klus klus : alleKlussen) {
			if (klus.getKlant().equals(k) && klus.getAuto().equals(a)
					&& klus.getKlusType().equals(KlusType)
					&& klus.getAangemaakt().equals(aangemaakt)) {
				alleKlussen.remove(k);
				verwijdert = false;
			}
		}
		return verwijdert;
	}

	/**
	 * 
	 * Zoekt planning in ArrayList alleWeekplanningen.s
	 * 
	 * @param weeknr
	 *            Week nummer van de planning.
	 * @param jaar
	 *            Jaar van de planning
	 * @return Geeft de weekplanning terug.
	 */
	public Weekplanning zoekWeekplanning(int weeknr, int jaar) {
		Weekplanning wp = null;
		for (Weekplanning w : alleWeekplanningen) {
			if (w.getWeeknr() == weeknr && w.getJaar() == jaar) {
				wp = w;
			}
		}
		return wp;
	}

	/**
	 * 
	 * Voegt een nieuwe weekplanning toe aan ArrayList alleWeekplanningen.
	 * 
	 * @param w
	 *            Instantie van object Weekplanning
	 * @return Geeft een bevestiging van dat de weekplanning is toegevoegd.
	 */
	public boolean voegWeekplanningToe(Weekplanning w) {
		boolean toegevoegd = false;
		if (zoekWeekplanning(w.getWeeknr(), w.getJaar()) == null) {
			alleWeekplanningen.add(w);
			toegevoegd = true;
		}
		return toegevoegd;
	}
	

	
	public Weekplanning zoekKlusWeekplanning(Klus k){
		Weekplanning returnWeekplanning = null;
		for (Weekplanning w2 : alleWeekplanningen){
			for (Klus k3 : w2.getIngeplandeKlus()){
				if (k3.equals(k)){
					returnWeekplanning = w2;
				}
			}
		}
		
		
		return returnWeekplanning;
	}

	/**
	 * 
	 * Haalt een lijst met werkzaamheden.
	 * 
	 * @return Geeft een lijst met werkzaamheden.
	 */
	public ArrayList<Klus> getAlleWerkzaamheden() {
		return alleKlussen;
	}

	/**
	 * 
	 * Haalt een lijst met monteurs.
	 * 
	 * @return Geeft een lijst met monteurs.
	 */
	public ArrayList<Monteur> getAlleMonteurs() {
		return alleMonteurs;
	}

	/**
	 * 
	 * Haalt een lijst met weekplaninningen.
	 * 
	 * @return Geeft een lisjt met weekplanningen.
	 */
	public ArrayList<Weekplanning> getAlleWeekplanningen() {
		return alleWeekplanningen;
	}

	/**
	 * 
	 * Haalt alle jaren waarin iets is gepland op.
	 * 
	 * @return Geeft jaren waar iets in is gepland op.
	 */
	public Object[] getAlleJaren() {
		ArrayList<Integer> jaren = new ArrayList<Integer>();
		for (Weekplanning w : alleWeekplanningen) {
			boolean found = false;
			for (Integer i : jaren) {
				if (w.getJaar() == i) {
					found = true;
				}
			}
			if (!found) {
				jaren.add(w.getJaar());
			}
		}
		Object[] result = new Object[jaren.size()];
		for (int j = 0; j < result.length; j++) {
			result[j] = "" + jaren.get(result.length - j - 1);
		}
		return result;
	}

	/**
	 * 
	 * Haalt alle weken van de planning op.
	 * 
	 * @param jaar
	 *            Jaar van de planning
	 * @return Geeft alle weken van de geselecteerde jaar in planning.
	 */
	public Object[] getAlleWeken(int jaar) {
		ArrayList<Integer> weken = new ArrayList<Integer>();
		for (Weekplanning w : alleWeekplanningen) {
			boolean found = false;
			for (Integer i : weken) {
				if (w.getJaar() != jaar || w.getWeeknr() == i) {
					found = true;
				}
			}
			if (!found) {
				weken.add(w.getWeeknr());
			}
		}
		Object[] result = new Object[weken.size()];
		for (int j = 0; j < result.length; j++) {
			result[j] = "" + weken.get(result.length - j - 1);
		}
		return result;
	}

	/**
	 * 
	 * Haalt Mogelijke tijdstippen in halve uren op.
	 * 
	 * @param halfuur
	 *            tijdstip per 30 minuten voor plan mogelijkheden.
	 * @return Geeft alle halfuren terug.
	 */
	public String getHalfUur(int halfuur) {
		String result = "";
		int uur = ((halfuur) / 2 + 8);
		if (halfuur % 2 == 1) {
			result += uur + ":30" + " - " + (uur + 1) + ":00";
		} else {
			result += uur + ":00" + " - " + uur + ":30";
		}
		return result;
	}

	/**
	 * . Haalt de tijden van de Klus op.
	 * 
	 * @param begintijd
	 *            Begin tijd van de werkzaamehid.
	 * @param eindtijd
	 *            Eind tijd van de Klus.
	 * @return Geeft de tijden van de Klus terug.
	 */
	public String getTijd(int begintijd, int eindtijd) {
		String result = "";
		result += getBeginTijd(begintijd) + " - " + getEindTijd(eindtijd);
		return result;
	}

	/**
	 * 
	 * Haalt de begin tijd van de Klus in halve uren op.
	 * 
	 * @param halfuur
	 *            tijdstip per 30 minuten voor plan mogelijkheden.
	 * @return Geeft de begin tijd van de Klus in halve uren.
	 */
	public String getBeginTijd(int halfuur) {
		String result = "";
		int uur = ((halfuur) / 2 + 8);
		if (halfuur >= 8) {
			uur += 1;
		}
		if (halfuur % 2 == 1) {
			result += uur + ":30";
		} else {
			result += uur + ":00";
		}
		return result;
	}

	/**
	 * 
	 * Haalt de eind tijd van de Klus in halve uren op.
	 * 
	 * @param halfuur
	 *            tijdstip per 30 minuten voor plan mogelijkheden.
	 * @return Geeft de eind tijd van de Klus in halve uren.
	 */
	public String getEindTijd(int halfuur) {
		String result = "";
		int uur = ((halfuur + 1) / 2 + 8);
		if (halfuur >= 8) {
			uur += 1;
		}
		if (halfuur % 2 == 0) {
			result += uur + ":30";
		} else {
			result += uur + ":00";
		}
		return result;
	}

}
