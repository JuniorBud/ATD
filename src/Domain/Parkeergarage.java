package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;




public class Parkeergarage implements Serializable{
	private ArrayList<Reservering>	alleReserveringen	= new ArrayList<Reservering>();


	/**
	 * 
	 * Haalt een lijst met beschikbare plekken om te reserveren.
	 * 
	 * @param beginDatum
	 *            Begin datum dat de plek beschibaar moet zijn.
	 * @param eindDatum
	 *            eind datum dat de plek beschikbaar moet zijn.
	 * @param beginTijd
	 *            Begin tijd dat de parkeerplek beschikbaar moet zijn.
	 * @param eindTijd
	 *            Eind tijd dat de parkeerplek beschikbaar moet zijn.
	 * @param invalide
	 *            Indicatie of het een invalide parkeerplaats moet zijn.
	 * @return Geeft een lijst met beschikbare parkeerplaatsen.
	 */
	public ArrayList<String> getBeschikbarePlekken(Date beginDatum,
			Date eindDatum, int beginTijd, int eindTijd, boolean invalide) {
		ArrayList<String> beschikbarePlekken = new ArrayList<String>();

		int plekken = 200;

		if (invalide)
			plekken += 5;

		for (int i = 1; i <= plekken; i++) {
			beschikbarePlekken.add("" + i);
		}

		for (Reservering res : getAlleReserveringen()) {
			Date resBeginDatum = res.getBeginDatum();
			Date resEindDatum = res.getEindDatum();
			int resBeginTijd = res.getBeginTijd();
			int resEindTijd = res.getEindTijd();

			if (((resBeginDatum.after(beginDatum)) && (resEindDatum
					.before(eindDatum)))
					|| resEindDatum.equals(eindDatum)
					|| resBeginDatum.equals(beginDatum)) {
				beschikbarePlekken.remove("" + res.getParkeerplaats());
			} else if (resEindDatum.equals(beginDatum)) {
				if (resEindTijd > beginTijd)
					beschikbarePlekken.remove("" + res.getParkeerplaats());

			} else if (resBeginDatum.equals(eindDatum)) {
				if (resBeginTijd < eindTijd)
					beschikbarePlekken.remove("" + res.getParkeerplaats());

			}
		}

		return beschikbarePlekken;
	}

	/**
	 * 
	 * Haalt een lijst met reserveringen op.
	 * 
	 * @return Geeft een lijst met reserveringen.
	 */
	public ArrayList<Reservering> getAlleReserveringen() {
		return alleReserveringen;
	}

	/**
	 * 
	 * Voegt een nieuwe reservering toe aan ArrayList alleReserveringen.
	 * 
	 * @param res
	 *            Intantie van de nieuwe object Reservering.
	 */
	public void voegReserveringToe(Reservering res) {
		alleReserveringen.add(res);
	}
}
