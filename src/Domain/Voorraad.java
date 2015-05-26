package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
	
	public ArrayList<Onderdeel> getAlleOnderdelen(){
		return alleOnderdelen;
	}
	
	public ArrayList<Brandstof> getAlleBrandstof(){
		return alleBrandstof;		
	}

    public void bestelBrandstof(ArrayList<Brandstof> b,
                                ArrayList<Integer> aantal) {
        try {
            System.out.println("test");
            File theDir = new File("bestellingen");
            if (!theDir.exists()) {
                theDir.mkdir();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String datumVandaag = dateFormat.format(new Date());
            FileWriter fw = new FileWriter(
                    "./bestellingen/Bestellen_Brandstof_" + datumVandaag
                            + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < b.size(); i++) {
                Brandstof br = b.get(i);
                pw.print("TankstationID: " + br.getTankstationID() + " Type: "
                        + br.getBrandstofType() + " Aantal liter: "
                        + aantal.get(i));
                pw.print(System.lineSeparator());
            }
            pw.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void bestelOnderdelen(ArrayList<Onderdeel> o,
                                 ArrayList<Integer> aantal) {
        try {
            File theDir = new File("bestellingen");
            if (!theDir.exists()) {
                theDir.mkdir();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String datumVandaag = dateFormat.format(new Date());
            FileWriter fw = new FileWriter(
                    "./bestellingen/Bestellen_Onderdelen_" + datumVandaag
                            + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < o.size(); i++) {
                Onderdeel onderdeel = o.get(i);
                pw.print("OnderdeelNR: " + onderdeel.getOnderdeelNummer()
                        + " Omschrijving: "
                        + onderdeel.getOnderdeelOmschrijving() + " Aantal: "
                        + aantal.get(i));
                pw.print(System.lineSeparator());
            }
            pw.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
