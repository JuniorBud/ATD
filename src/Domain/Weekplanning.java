package Domain;

/**
 * Created by Frits on 18-5-2015.
 */

import java.util.ArrayList;


public class Weekplanning {
	private int weeknr, jaar;
	private ArrayList<Klus> ingeplandeKlus = new ArrayList<Klus>();
	
	public Weekplanning(int weeknr, int jaar){
		this.weeknr = weeknr;
		this.jaar = jaar;
	}
		
	public int getWeeknr() {
		return weeknr;
	}

	public int getJaar() {
		return jaar;
	}

	public ArrayList<Klus> getIngeplandeKlus() {
		return ingeplandeKlus;
	}
	
	public void planKlusIn(Klus k,ArrayList<Monteur> monteurs,int dag, int beginTijd, int eindTijd) {
        k.setDag(dag);
		k.setBeginTijd(beginTijd);
        k.setEindTijd(eindTijd);
		k.setMonteurs(monteurs);
		ingeplandeKlus.add(k);
	}

	public boolean[] getBeschikbareTijden(ArrayList<Monteur> monteurs, int dag){
		boolean[] result = new boolean[16];
		for(int i = 0;i < 16;i++){
			result[i] = true;
		}
		for(Klus w: ingeplandeKlus){
			for(Monteur m1: monteurs){
				for(Monteur m2: w.getMonteurs()){
					if(m1.getAchternaam().equals(m2.getAchternaam()) && m1.getNaam().equals(m2.getNaam()) && 
							m1.getTussenvoegsel().equals(m2.getTussenvoegsel()) && w.getDag() == dag){
						for(int i = w.getBeginTijd(); i <= w.getEindTijd(); i++){
							result[i-1] = false;
						}
					}
				}
			}
		}
		return result;
	}
	
	public Klus getKlus(Monteur m1, int dag, int uur){
		Klus result = null;
		for(Klus k: ingeplandeKlus){
			for(Monteur m2: k.getMonteurs()){
				if(m1.equals(m2) && k.getDag() == dag && uur >= k.getBeginTijd() && uur <= k.getEindTijd()){
					result =  k;
				}
			}
		}
		return result;
	}
}
