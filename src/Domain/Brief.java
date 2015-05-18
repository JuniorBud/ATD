package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
import java.util.Date;


public class Brief {
	private Template template;
	private Klant klant;
	private Date datumVerzending;
	
	public Brief(Template template, Klant klant, Date datumVerzending) {
		this.template = template;
		this.klant = klant;
		this.datumVerzending = datumVerzending;
	}
}
