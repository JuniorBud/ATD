package Domain;



import java.io.Serializable;
import java.util.Date;



public class Reservering implements Serializable{
	private String	naam, kenteken;
	private Date	beginDatum, eindDatum;
	private boolean	invalide;
	private int		beginTijd, eindTijd, parkeerplaats;

	/**
	 * 
	 * Dit maakt een reservering aan met een naam, de kenteken van de auto, de
	 * begin- en einddatum en tijd van de reservering, de optie om aan te geven
	 * of het een invalide parkeer plaats is en de parkeeplaats zelf.
	 * 
	 * @param naam
	 *            Naam van de klant
	 * @param kenteken
	 *            Kenteken van de auto
	 * @param beginDatum
	 *            Datum wanneer de klant zijn auto in de garage zet.
	 * @param beginTijd
	 *            Tijd van hoe laat de klant zijn auto in de garage zet.
	 * @param eindDatum
	 *            Datum wanneer de klant zijn auto uit de garage haalt.
	 * @param eindTijd
	 *            Tijd van hoe laat de klant zijn auto uit de garage haalt
	 * @param invalide
	 *            Indicatie of het gaat om een parkeerplaats voor gehandicapten
	 * @param parkeerplaats
	 */
	public Reservering(String naam, String kenteken, Date beginDatum, int beginTijd, Date eindDatum, int eindTijd, boolean invalide, int parkeerplaats) {
		super();
		this.naam = naam;
		this.kenteken = kenteken;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
		this.invalide = invalide;
		this.beginTijd = beginTijd;
		this.eindTijd = eindTijd;
		this.parkeerplaats = parkeerplaats;
	}
/**
 * 
 * Haalt de naam van de klant op.
 * @return Geeft de naam van de klant.
 */
	public String getNaam() {
		return naam;
	}
/**
 * 
 * Bepaald de naam van de klant.
 * @param naam Naam van de klant.
 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
/**
 * 
 * Haalt de kenteken van de auto op.
 * @return Geeft de kenteken van de auto die op de gereserveerde plek zal parkeren.
 */
	public String getKenteken() {
		return kenteken;
	}
/**
 * 
 * Bepaald de kenteken van de te parkeren auto.
 * @param kenteken Kenteken van de auto die op de plek zal parkeren.
 */
	public void setKenteken(String kenteken) {
		this.kenteken = kenteken;
	}
/**
 * 
 * Haalt de datum op wanneer de reservering zal beginnen.
 * @return Geeft de bgein datum van de reservering terug.
 */
	public Date getBeginDatum() {
		return beginDatum;
	}
/**
 * 
 * Bepaald waneer de reservering zal beginnen.
 * @param beginDatum Datum wanneer de klant zijn auto in de garage zet.
 */
	public void setBeginDatum(Date beginDatum) {
		this.beginDatum = beginDatum;
	}
/**
 * 
 * Haalt de datum op wanneer de reservering eindigt.
 * @return Geeft de datum wanneer de reservering eindigt terug.
 */
	public Date getEindDatum() {
		return eindDatum;
	}
/**
 * 
 * Bepaald wanneer de reservering zal eindigen.
 * @param eindDatum Datum wanneer de klant zijn auto uit de garage haalt.
 */
	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}
/**
 * Controleert of de klant mindervalide is.
 * @return Geeft een indicatie of de klant een invalide parkeerplaats nodig heeft.
 */
	public boolean isInvalide() {
		return invalide;
	}
/**
 * 
 * Bepaald of de klant een invalide parkeerplaats nodig heeft.
 * @param invalide Indicatie of de resererveerde parkeerplaats een invalide parkeerplaats moet zijn.
 */
	public void setInvalide(boolean invalide) {
		this.invalide = invalide;
	}
/**
 * 
 * Haalt de tijd op dat de reservering begint.
 * @return Geeft de begintijd van de reservering.
 */
	public int getBeginTijd() {
		return beginTijd;
	}
/**
 * 
 * Bepaald hoe laat de reservering moet beginnen.
 * @param beginTijd Tijd dat de reservering begint.
 */
	public void setBeginTijd(int beginTijd) {
		this.beginTijd = beginTijd;
	}
/**
 * 
 * Haalt de tijd dat de reservering eindigt op.
 * @return Geeft de tijd dat de reservering eidigt terug
 */
	public int getEindTijd() {
		return eindTijd;
	}
/**
 * 
 * Bepaald hoe laat de reservering eindigt.
 * @param eindTijd Tijd dat de reservering eindigt.
 */
	public void setEindTijd(int eindTijd) {
		this.eindTijd = eindTijd;
	}
/**
 * 
 * Haalt de parrkeerplaats van de reservering op.
 * @return Geeft de gereserveerde parkeerplaats terug.
 */
	public int getParkeerplaats() {
		return parkeerplaats;
	}
/**
 * 
 * Bepaald welke parkeerplaats zal worden gereserveerd.
 * @param parkeerplaats Parkeerplaats waar de klant zijn/haar auto zal parkeren.
 */
	public void setParkeerplaats(int parkeerplaats) {
		this.parkeerplaats = parkeerplaats;
	}

	public String getTijd(int tijd) {
		int uren = tijd / 100;
		int minuten = (tijd - uren * 100) % 60;

		String urenString = Integer.toString(uren);
		String minutenString = Integer.toString(minuten);

		if (minutenString.length() == 1) {
			minutenString = "0" + minutenString;
		}

		if (urenString.length() == 1) {
			urenString = "0" + urenString;
		}

		String s = urenString + ":" + minutenString;

		return s;
	}

}
