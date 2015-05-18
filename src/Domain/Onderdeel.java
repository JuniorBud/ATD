package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
public class Onderdeel {
        private String onderdeelOmschrijving;
        private int onderdeelNummer;
        private int onderdeelMinimaleVoorraad;
        private int onderdeelVoorraad;

        public Onderdeel(String onderdeelOmschrijving, int onderdeelNummer, int onderdeelMinimaleVoorraad, int onderdeelVoorraad) {
            this.onderdeelOmschrijving = onderdeelOmschrijving;
            this.onderdeelNummer = onderdeelNummer;
            this.onderdeelMinimaleVoorraad = onderdeelMinimaleVoorraad;
            this.onderdeelVoorraad = onderdeelVoorraad;
        }

        public String getOnderdeelOmschrijving() {
            return onderdeelOmschrijving;
        }

        public void setOnderdeelOmschrijving(String onderdeelOmschrijving) {
            this.onderdeelOmschrijving = onderdeelOmschrijving;
        }

        public int getOnderdeelNummer() {
            return onderdeelNummer;
        }

        public void setOnderdeelNummer(int onderdeelNummer) {
            this.onderdeelNummer = onderdeelNummer;
        }

        public int getOnderdeelVoorraad() {
            return onderdeelVoorraad;
        }

        public void setOnderdeelVoorraad(int onderdeelVoorraad) {
            this.onderdeelVoorraad = onderdeelVoorraad;
        }

        public String toString() {
            return onderdeelOmschrijving + " " + onderdeelNummer + " " + onderdeelVoorraad;
        }

        public int getOnderdeelMinimaleVoorraad() {
            return onderdeelMinimaleVoorraad;
        }

        public void setOnderdeelMinimaleVoorraad(int onderdeelMinimaleVoorraad) {
            this.onderdeelMinimaleVoorraad = onderdeelMinimaleVoorraad;
        }
}
