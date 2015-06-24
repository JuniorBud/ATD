package nl.hu.tho4.garagebeheersysteem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * 
 * @author THO4 team3
 * @version 1.0
 * 
 */

public class Facturatie implements Serializable{
	private ArrayList<Factuur>	alleFacturen	= new ArrayList<Factuur>();

	/**
	 * 
	 * Geeft een lijst van alle facturen.
	 * 
	 * @param k
	 *            Instantie van object Klant.
	 * @param datum
	 *            Datum waarop de factuur is gemaakt.
	 * @return Geeft een lijst van alle facturen.
	 */

	public boolean voegFactuurToe(Klant k, Calendar datum, ArrayList<Onderdeel> onderdelen, ArrayList<Integer> hoeveel, double aantalManuren) {
		return alleFacturen.add(new Factuur(datum, k, onderdelen, hoeveel, aantalManuren));

	}

	/**
	 * 
	 * Voegt een nieuwe factuur toe aan ArrayList alleFacturen.
	 * 
	 * @param f
	 *            Instantie van object Factuur.
	 * @return Voegt de nieuwe factuur toe aan ArayList alleFacturen.
	 */
	public boolean voegFactuurToe(Factuur f) {
		return alleFacturen.add(f);

	}

	/**
	 * 
	 * Haalt een lijst met facturen op.
	 * 
	 * @return Geeft een lijst met facturen terug.
	 */
	public ArrayList<Factuur> getAlleFacturen() {
		return alleFacturen;
	}
}
