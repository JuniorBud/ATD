package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
public class Adres {
	private String straat;
	private int huisnummmer;
	private String toevoeging;
	private String postcode;
	private String woonplaats;

	public Adres(String straat, int huisnummmer, String toevoeging, String postcode, String woonplaats) {
		setStraat(straat);
		setHuisnummmer(huisnummmer);
		setToevoeging(toevoeging);
		setPostcode(postcode);
		setWoonplaats(woonplaats);
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		if (postcode.matches("^[1-9][0-9]{3}\\s?((?!SS|SA|SD)[a-zA-Z]{2})?$")) {
			this.postcode = postcode;
		}
	}

	public int getHuisnummmer() {
		return huisnummmer;
	}

	public void setHuisnummmer(int huisnummmer) {
		this.huisnummmer = huisnummmer;
	}

	public String getToevoeging() {
		return toevoeging;
	}

	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
}
