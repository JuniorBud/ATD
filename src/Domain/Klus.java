package Domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Frits on 18-5-2015.
 */

public class Klus {
    private String klusType;
    private ArrayList<Monteur> monteurs;
    private Klant klant;
    private Auto auto;
    private double manuren;
    private ArrayList<Onderdeel> gebruikteOnderdelen;
    private String opmerkingAanmaken, opmerkingAfronden;
    private double verwachteUrenNodig;
    private Date aangemaakt;
    private int beginTijd, eindTijd, dag;

    public Klus(String klusType, Klant klant, Auto auto, String opmerkingAanmaken, double verwachteUrenNodig) {
        this.klusType = klusType;
        this.klant = klant;
        this.auto = auto;
        this.opmerkingAanmaken = opmerkingAanmaken;
        this.verwachteUrenNodig = verwachteUrenNodig;
        manuren = 0.0;
        monteurs = new ArrayList<Monteur>();
        gebruikteOnderdelen = new ArrayList<Onderdeel>();
        aangemaakt = new Date();
    }

    public double getManuren() {
        return manuren;
    }

    public void setManuren(double manuren) {
        this.manuren = manuren;
    }

    public String getOpmerkingAfronden() {
        return opmerkingAfronden;
    }

    public void setOpmerkingAfronden(String opmerkingAfronden) {
        this.opmerkingAfronden = opmerkingAfronden;
    }

    public String getKlusType() {
        return klusType;
    }

    public void setKlusType(String klusType) {
        this.klusType = klusType;
    }

    public ArrayList<Monteur> getMonteurs() {
        return monteurs;
    }

    public void setMonteurs(ArrayList<Monteur> monteurs) {
        this.monteurs = monteurs;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public ArrayList<Onderdeel> getGebruikteOnderdelen() {
        return gebruikteOnderdelen;
    }

    public void setGebruikteOnderdelen(ArrayList<Onderdeel> gebruikteOnderdelen) {
        this.gebruikteOnderdelen = gebruikteOnderdelen;
    }

    public String getOpmerkingAanmaken() {
        return opmerkingAanmaken;
    }

    public void setOpmerkingAanmaken(String opmerkingAanmaken) {
        this.opmerkingAanmaken = opmerkingAanmaken;
    }

    public double getVerwachteUrenNodig() {
        return verwachteUrenNodig;
    }

    public void setVerwachteUrenNodig(double verwachteUrenNodig) {
        this.verwachteUrenNodig = verwachteUrenNodig;
    }

    public Date getAangemaakt() {
        return aangemaakt;
    }

    public void setAangemaakt(Date aangemaakt) {
        this.aangemaakt = aangemaakt;
    }

    public int getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(int beginTijd) {
        this.beginTijd = beginTijd;
    }

    public int getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(int eindTijd) {
        this.eindTijd = eindTijd;
    }

    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }
}
