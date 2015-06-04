package Domain;


import java.io.Serializable;

public class Afspraak implements Serializable{
	private String voornaam, tussenvoegsel, achternaam, kenteken, telefoonnummer;

	/**
	 * 
	 * Constructor van Afsrpaak.
	 * 
	 * @param voornaam
	 *            Voornaam van de klant die de afspraak heeft.
	 * @param tussenvoegsel
	 *            Eventuele tussenvoegsel van de klant die de afspraak heeft.
	 * @param achternaam
	 *            Achternaam van de klant die de afspraak heeft.
	 * @param kenteken
	 *            Kenteken van de klant zijn/haar auto.
	 * @param telefoonnummer
	 *            Nummer waarop de klant telefonisch te bereiken is.
	 */
	public Afspraak(String voornaam, String tussenvoegsel, String achternaam,
			String kenteken, String telefoonnummer) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.kenteken = kenteken;
		this.telefoonnummer = telefoonnummer;
	}

	/**
	 * 
	 *Haalt de voornaam op.
	 * 
	 * @return Geeft de voornaam van de klant terug.
	 */
	public String getVoornaam() {
		return voornaam;
	}

	/**
	 * 
	 * Bepaald de voornaam.
	 * 
	 * @param voornaam
	 *            Voornaam van de klant die de afspraak heeft.
	 */
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	/**
	 * 
	 * Haalt het tussenvoegsel op.
	 * 
	 * @return Geeft indien beschikbaar de tussenvoegsel terug.
	 */
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	/**
	 * 
	 * Bepaald het tussenvoegsel.
	 * 
	 * @param tussenvoegsel
	 *            Eventuele tussenvoegsel van de klant die de afspraak heeft.
	 */
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	/**
	 * 
	 * Haalt de achernaam op.
	 * 
	 * @return Geeft de achternaam van de klant terug.
	 */
	public String getAchternaam() {
		return achternaam;
	}

	/**
	 * 
	 * Bepaald de achternaam.
	 * 
	 * @param achternaam
	 *            Achternaam van de klant die de afspraak heeft.
	 */
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	/**
	 * 
	 * Haalt het kenteken op.
	 * 
	 * @return Geeft de kenteken van de auto terug.
	 */
	public String getKenteken() {
		return kenteken;
	}

	/**
	 * 
	 * Bepaald het kenteken.
	 * 
	 * @param kenteken
	 *            Kenteken van de klant zijn/haar auto.
	 */
	public void setKenteken(String kenteken) {
		this.kenteken = kenteken;
	}

	/**
	 * 
	 * Haalt het telefoonnummer op.
	 * 
	 * @return Geeft de telefoonnummer van de klant terug.
	 */
	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	/**
	 * 
	 * Bepaald het telefoonnummer.
	 * 
	 * @param telefoonnummer
	 *            Nummer waarop de klant telefonisch te bereiken is.
	 */
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}
}
