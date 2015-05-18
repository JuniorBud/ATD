package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
public class Brandstof {
	private String brandstofType;
	private int tankstationID;
	private int minimaalAantalLiters;
	private int aantalLiters;
	private double brandstofPrijs;

	public Brandstof(String brandstofType, int tankstationID, int minimaalAantalLiters, int aantalLiters, double brandstofPrijs) {
		this.brandstofType = brandstofType;
		this.tankstationID = tankstationID;
		this.minimaalAantalLiters = minimaalAantalLiters;
		this.aantalLiters = aantalLiters;
		this.brandstofPrijs = brandstofPrijs;
	}

	public String getBrandstofType() {
		return brandstofType;
	}

	public void setBrandstofType(String brandstofType) {
		this.brandstofType = brandstofType;
	}

	public int getAantalLiters() {
		return aantalLiters;
	}

	public void setAantalLiters(int aantalLiters) {
		this.aantalLiters = aantalLiters;
	}

	public int getTankstationID() {
		return tankstationID;
	}

	public void setTankstationID(int tankstationID) {
		this.tankstationID = tankstationID;
	}

	public double getBrandstofPrijs() {
		return brandstofPrijs;
	}

	public void setBrandstofPrijs(double brandstofPrijs) {
		this.brandstofPrijs = brandstofPrijs;
	}

	public int getMinimaalAantalLiters() {
		return minimaalAantalLiters;
	}

	public void setMinimaalAantalLiters(int minimaalAantalLiters) {
		this.minimaalAantalLiters = minimaalAantalLiters;
	}
	
	
	
}
