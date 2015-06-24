package nl.hu.tho4.garagebeheersysteem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletContext;


/**
 * 
 * @author THO4 team3
 * @version 1.0
 * 
 */

public class Factuur implements Serializable{

	private Calendar datum;
	private Calendar vervalDatum;
	private Klant klant;
	private double kosten;
	private double betaald;
	private ArrayList<Onderdeel> gebruikteOnderdelen;
	private ArrayList<Integer> hoeveelGebruikt;
	private double aantalManuren;
	File file = new File("onderdeelKosten.txt");

	@SuppressWarnings("deprecation")
	/**
	 * 
	 * Constructor van klasse
	 * @param datum
	 * @param klant
	 */

	public Factuur(Calendar datum, Klant klant, ArrayList<Onderdeel> gebruikteOnderdelen, ArrayList<Integer> hoeveelGebruikt, double aantalManuren) {

		super();
		this.datum = datum;
		this.klant = klant;
		this.gebruikteOnderdelen = gebruikteOnderdelen;
		this.aantalManuren = aantalManuren;

		Werkplaats werkplaats = new Werkplaats();

		ArrayList<Weekplanning> allePlanningen = werkplaats.getAlleWeekplanningen();
		this.vervalDatum = Calendar.getInstance();
		this.vervalDatum.set(datum.get(Calendar.YEAR), datum.get(Calendar.MONTH), datum.get(Calendar.DATE));
		this.vervalDatum.add(Calendar.MONTH, 1);
		this.hoeveelGebruikt = hoeveelGebruikt;
	}
/**
 * Haalt verval datum op.
 * @return Geeft verval datum.
 */
	public Calendar getVervalDatum() {
		return vervalDatum;
	}
/**
 * 
 * Controle of de factuur betaald is.
 * 
 * @param i
 *            Bedrag dat betaald is.
 * @return Geeft terug of de factuur is betaald of niet.
 */
	public boolean Betaal(double i){
		if (i > kosten-betaald){


			return true;
		} else {
			this.betaald = this.betaald + i;

			return false;
		}
	}

	/**
	 * 
	 * Haalt de datum van de factuur op.
	 * 
	 * @return Geeft de datum van de factuur terug.
	 */
	public Calendar getDatum() {
		return datum;
	}

	/**
	 * 
	 * Bepaalde datum van de factuur.
	 * 
	 * @param datum
	 *            Datum van de factuur.
	 */
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	/**
	 * 
	 * Haalt de klant van de factuur op.
	 * 
	 * @return Geeft de klant van de factuur terug.
	 */
	public Klant getKlant() {
		return klant;
	}

	/**
	 * 
	 * Bepaald de klant voor wie de klant
	 * 
	 * @param klant
	 */
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	
	
	/**
	 * haalt een lijst op met hoeveel onderdelen er zijn gebruikt
	 * @return lijst met hoeveel onderdelen
	 */
	public ArrayList<Integer> getHoeveelGebruikt() {
		return hoeveelGebruikt;
	}
	
	/**
	 * Haalt en lijst op met de onderdelen die gebruikt zijn
	 * @return een lijst met gebruikte onderdelen
	 */

	public ArrayList<Onderdeel> getGebruikteOnderdelen() {
		return gebruikteOnderdelen;
	}


	/**
	 * 
	 * Haalt de kosten die op de factuur genoteerd staan op.
	 * 
	 * @return Geeft de kosten van de werkzaamheid terug.
	 */
	public double getKosten() {
		return kosten;
	}

	/**
	 * 
	 * Haalt het bedrag op waarmee de factuur is betaald.
	 * 
	 * @return Geeft het betaalde bedrag terug.
	 */
	public double getBetaald() {
		return betaald;
	}

	/**
	 * 
	 * Voert een bedrag in om de factuur mee te betalen.
	 * 
	 * @param betaal
	 *            Bedrag om de factuur mee te betalen.
	 */
	public void setBetaald(double betaal) {
		this.betaald = betaal;
	}

	/**
	 * 
	 * Controle of twee facturen overeen komen.
	 * 
	 * @param f
	 *            Instantie van object Factuur.
	 * @return Geeft terug of een factuur identiek is aan een andere factuur.
	 */
	public boolean equals(Factuur f) {
		if (f instanceof Factuur
				&& this.getKlant() == f.getKlant()
				&& f.getDatum().get(Calendar.MONTH) == this.getDatum().get(
						Calendar.MONTH)
				&& f.getDatum().get(Calendar.YEAR) == this.getDatum().get(
						Calendar.YEAR)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 
	 * Berekend het te betalen bedrag voor de factuur.
	 * 
	 * @param werkuren
	 *            Werkuren voor de berekening van de totale kosten.
	 */

	
	public void berekenKosten(double werkuren){
		this.kosten = this.kosten + (werkuren * 45.0);
		int i = 0;
		for (Onderdeel o : gebruikteOnderdelen){
			double KostenditOnderdeel = o.getOnderdeelKosten() * hoeveelGebruikt.get(i);
			this.kosten = this.kosten + o.getOnderdeelKosten();
			i++;
		}
	}
	/**
	 * geeft het aantal gebruikte manuren terug
	 * @return het gebruikte aantal manuren
	 */
	public double getAantalManuren() {
		return this.aantalManuren;
	}

}
