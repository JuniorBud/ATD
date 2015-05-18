package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
import java.util.ArrayList;


public class Voorraad {
	private ArrayList<Onderdeel> alleOnderdelen;
	private ArrayList<Brandstof> alleBrandstof;

	public Voorraad() {
		alleBrandstof = new ArrayList<Brandstof>();
		alleOnderdelen = new ArrayList<Onderdeel>();
	}

	public boolean voegOnderdeelToe(Onderdeel onderdeel) {
		boolean toegevoegd = false;

		if (zoekOnderdeel(onderdeel.getOnderdeelNummer()) == null) {
			alleOnderdelen.add(onderdeel);
			toegevoegd = true;
		}

		return toegevoegd;
	}

	public boolean verwijderOnderdeel(int onderdeelNummer) {
		boolean verwijdert = false;

		if (zoekOnderdeel(onderdeelNummer) != null) {
			alleOnderdelen.remove(zoekOnderdeel(onderdeelNummer));
			verwijdert = true;
		}

		return verwijdert;
	}

	public Onderdeel zoekOnderdeel(int onderdeelNummer) {
		Onderdeel gevondenOnderdeel = null;
		for (Onderdeel a : alleOnderdelen) {
			if (onderdeelNummer == a.getOnderdeelNummer()) {
				gevondenOnderdeel = a;
			}
		}
		return gevondenOnderdeel;
	}

	public boolean voegBrandstofToe(Brandstof brandstof) {
		boolean toegevoegd = false;

		if (zoekBrandstof(brandstof) == null) {
			alleBrandstof.add(brandstof);
			toegevoegd = true;
		}

		return toegevoegd;
	}
	
	public boolean verwijderBrandstof(Brandstof brandstof){
		boolean verwijdert = false;
		
		if(zoekBrandstof(brandstof) != null){
			alleBrandstof.remove(brandstof);
			verwijdert = true;
		}
		
		return verwijdert;		
	}

	public Brandstof zoekBrandstof(Brandstof brandstof) {
		Brandstof gevondenBrandstof = null;
		
		for(Brandstof a: alleBrandstof){
			if(a.getTankstationID() == brandstof.getTankstationID() && a.getBrandstofType().equals(brandstof.getBrandstofType())){
				gevondenBrandstof = a;
			}
		}

		return gevondenBrandstof;
	}
	
	public ArrayList<Onderdeel> alleOnderdelen(){
		return alleOnderdelen;
	}
	
	public ArrayList<Brandstof> alleBrandstof(){
		return alleBrandstof;		
	}
}
