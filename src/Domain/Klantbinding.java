package Domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Klantbinding implements Serializable{
	private  ArrayList<Klant>		alleKlanten		= new ArrayList<Klant>();
	private ArrayList<Template>	alleTemplates	= new ArrayList<Template>();


	/**
	 * 
	 * Haalt de klant op.
	 * 
	 * @param vnm
	 *            Voornaam van de klant.
	 * @param ts
	 *            Eventuele tussenvoegsel van de klant zijn/haar naam.
	 * @param anm
	 *            Achternaam van de klant.
	 * @return Geeft de klant terug.
	 */
	public Klant getKlant(String vnm, String ts, String anm) {
		Klant klant = null;
		for (Klant k : alleKlanten) {
			if (k.getAchternaam().equals(anm) && k.getNaam().equals(vnm)
					&& k.getTussenvoegsel().equals(ts)) {
				klant = k;
			}
		}
		return klant;
	}

	/**
	 * 
	 * Verwijderd de klant uit het klantbestand.
	 * 
	 * @param vnm
	 *            Voornaam van de klant.
	 * @param ts
	 *            Eventuele tussenvoegsel van de klant zijn/haar naam.
	 * @param anm
	 *            Achternaam van de klant.
	 * @return Geeft bevestiging dat de klant is verwijderd.
	 */
	public boolean verwijderKlant(String vnm, String ts, String anm) {
		boolean verwijdert = false;

		if (getKlant(vnm, ts, anm) != null) {
			alleKlanten.remove(getKlant(vnm, ts, anm));
			verwijdert = true;
		}
		return verwijdert;
	}

	/**
	 * 
	 * Voegt een nieuwe klant toe aan het klantbestand.
	 * 
	 * @param klant
	 *            Instantie van nieuwe object Klant.
	 * @return Geeft een bevestiging van het toevoegen van de klant.
	 */
	public boolean voegKlantToe(Klant klant) {
		boolean toegevoegd = false;

		if (getKlant(klant.getNaam(), klant.getTussenvoegsel(),
				klant.getAchternaam()) == null) {
			alleKlanten.add(klant);
			toegevoegd = true;
		}

		return toegevoegd;
	}

	/**
	 * 
	 * Zoekt een template voor brieven naar de klant.
	 * 
	 * @param nm
	 *            Naam van de template.
	 * @return Geeft een indicatie of de template aanwezig is.
	 */
	public Template zoekTemplate(String nm) {
		Template aanwezig = null;
		for (Template t : alleTemplates) {
			if (t.getTemplateNaam().equals(nm)) {
				aanwezig = t;
			}
		}
		return aanwezig;
	}

	/**
	 * 
	 * Voegt een template voor brieven toe aan deze klant.
	 * 
	 * @param nm
	 *            Naam van template.
	 * @param txt
	 *            Inhoud van de template.
	 * @return Bevestigt het aanmaken van de template.
	 */
	public boolean voegTemplateToe(Template t) {
		boolean templateAangemaakt = false;

		if (zoekTemplate(t.getTemplateNaam()) == null) {
			alleTemplates.add(t);
			templateAangemaakt = true;
		}

		return templateAangemaakt;
	}

	/**
	 * 
	 * Wijzigd de gevevens van de template.
	 * 
	 * @param nm
	 *            Naam van de template.
	 * @param txt
	 *            Inhoud van de template.
	 * @return Bevestigt het aanbrengen van wijzigingen aan de template.
	 */
	public boolean wijzigTemplate(String nm, String txt) {
		boolean gewijzigd = false;

		Template tp = zoekTemplate(nm);
		if (tp != null) {
			tp.setTemplateInhoud(txt);
			gewijzigd = true;
		}

		return gewijzigd;
	}

	/**
	 * 
	 * Verwijderd een template uit de klantenbinding.
	 * 
	 * @param nm
	 *            Naam van de template.
	 * @return Bevestigt het verwijderen van de template.
	 */
	public boolean verwijderTemplate(String nm) {
		boolean templateVerwijdert = false;

		Template tp = zoekTemplate(nm);

		if (tp != null) {
			alleTemplates.remove(tp);
			templateVerwijdert = true;
		}

		return templateVerwijdert;
	}

	/**
	 * 
	 * Haalt een lijst met templates op.
	 * 
	 * @return Geeft een lijst met templates.
	 */
	public ArrayList<Template> getAlleTemplates() {
		return alleTemplates;
	}

	/**
	 * 
	 * Maakt met behulp van een template een brief voor de klant.
	 * 
	 * @param k
	 *            Instantie van object Klant.
	 * @param nm
	 *            Naam van de Template.
	 * @return Bevestigt het aanmaken van de bief.
	 */
	public boolean genereerBrief(Klant k, String nm) {
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
			String datum = dateFormat.format(Calendar.getInstance().getTime())
					.replaceAll("/", "-");
			FileWriter fw = new FileWriter("./brieven/" + tp.getTemplateNaam()
					+ "brief" + "_" + k.getNaam() + k.getTussenvoegsel()
					+ k.getAchternaam() + "_" + datum + ".txt");

			PrintWriter pw = new PrintWriter(fw);
			pw.print(result.replace("\n", System.lineSeparator()).replace(
					"\r\n", System.lineSeparator()));
			pw.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		return true;
	}

	/**
	 * 
	 * Haalt een lijst met klanten op.
	 * 
	 * @return Geeft een lijst met klanten.
	 */
	public ArrayList<Klant> getAlleKlanten() {
		return alleKlanten;
	}
}
