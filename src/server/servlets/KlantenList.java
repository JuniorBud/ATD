package server.servlets;

import Domain.Klant;

import java.util.ArrayList;

public class KlantenList {
	public ArrayList<Klant> klanten;
	public KlantenList(){
		klanten = new ArrayList<Klant>();
	}
	
	public ArrayList<Klant> getList(){
		return klanten;
	}
	
	public void voegKlantToe(Klant k){
		klanten.add(k);
	}
	
	public void verwijderKlant(String e, String w){
		
		for(int i = 0; i < klanten.size(); i++){
			if(klanten.get(i).getEmail().equals(e) && klanten.get(i).getWachtwoord().equals(w)){
				klanten.remove(i);
			}
		}
	
		
	}
}
