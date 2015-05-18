package Domain;

/**
 * Created by Frits on 18-5-2015.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Klant {
    private String naam, achternaam, tussenvoegsel;
    private int korting;
    private Date bezoek, geboortedatum;
    private Adres adres;
    private ArrayList<Auto> alleAutos;
    // Comment door Dominic
    public Klant(String nm, String ts, String anm, String gbt, Adres adres, Auto auto) {
        alleAutos = new ArrayList<Auto>();
        naam = nm;
        tussenvoegsel = ts;
        achternaam = anm;
        try {
            setGeboortedatum(gbt);
        } catch (ParseException exc) {
            exc.printStackTrace();
        }
        this.adres = adres;
        bezoek = new Date();
        voegAutoToe(auto);
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public ArrayList<Auto> getAlleAutos() {
        return alleAutos;
    }

    public void setAlleAutos(ArrayList<Auto> alleAutos) {
        this.alleAutos = alleAutos;
    }

    public boolean voegAutoToe(Auto a) {
        boolean toegevoegd = false;
        if (zoekAuto(a.getKenteken()) == null) {
            alleAutos.add(a);
            toegevoegd = true;
        }
        return toegevoegd;
    }

    public Auto zoekAuto(String kenteken) {
        Auto gevondenAuto = null;
        for (Auto a : alleAutos) {
            if (a.getKenteken().equals(kenteken)) {
                gevondenAuto = a;
            }
        }
        return gevondenAuto;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getBezoek() {
        return bezoek;
    }

    public void setBezoek(Date bezoek) {
        this.bezoek = bezoek;
    }

    public int getKorting() {
        return korting;
    }

    public void setKorting(int korting) {
        this.korting = korting;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.geboortedatum = sdf.parse(geboortedatum);
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getLeeftijd() {
        Calendar cal = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        cal.setTime(this.geboortedatum);
        now.setTime(new Date());

        int leeftijd = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            leeftijd--;
        }
        return leeftijd;
    }

    public Date getLaatsteOnderhoud() {
        Calendar cal = Calendar.getInstance();
        cal.set(1111, 1, 1);
        Date latestDate = cal.getTime();

        for (Auto a : alleAutos) {
            if (a.getLaatsteOnderhoud().after(latestDate))
                latestDate = a.getLaatsteOnderhoud();
        }
        return latestDate;
    }
}