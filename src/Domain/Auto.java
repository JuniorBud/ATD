package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Auto {
    private String merk, kenteken, model;
    private int bouwjaar;
    private Date laatsteOnderhoud;

    public Auto(String mk, String md, String kt, int bj){
        merk = mk;
        model = md;
        kenteken = kt;
        bouwjaar = bj;
        laatsteOnderhoud = new Date();
    }

    public String getMerk() {
        return merk;
    }
    // comment2
    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getLaatsteOnderhoud() {
        return laatsteOnderhoud;
    }

    public void setLaatsteOnderhoud(String laatsteOnderhoud) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.laatsteOnderhoud = sdf.parse(laatsteOnderhoud);
        } catch (ParseException exc) {
            exc.printStackTrace();
        }
    }
}

