package Domain;

/**
 * Created by Frits on 18-5-2015.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Template {
	private static String[] tags = { "   --Select--", "voornaam", "achternaam", "woonplaats", "laatste_onderhoud", "laatste_bezoek" };
	private String templateNaam;
	private String templateInhoud;

	public Template(String templateNaam, String templateInhoud) {
		this.templateNaam = templateNaam;
		this.templateInhoud = templateInhoud;
	}

	public static boolean checkTags(String txt) {
		ArrayList<String> tags = tagsInTemplate(txt);
		int found = 0;
		for (String tg : tags) {
			for (String t : getTags()) {
				if (t.equals(tg)) {
					found++;
				}
			}
		}
		return (found == tags.size());
	}

	public static ArrayList<String> tagsInTemplate(String txt) {
		ArrayList<String> tags = new ArrayList<String>();
		Pattern p = Pattern.compile("\\[([^\\]]+)]");
		Matcher m = p.matcher(txt);

		while (m.find()) {
			tags.add(m.group(1));
		}
		return tags;
	}

	public String getGegeven(String tag, Klant k) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String result = "";
		switch (tag.toLowerCase()) {
			case "voornaam":
				result = k.getNaam();
				break;
			case "achternaam":
				if (!k.getTussenvoegsel().equals("")) {
					result += k.getTussenvoegsel() + " ";
				}
				result += k.getAchternaam();
				break;
			case "leeftijd":
				result = "" + k.getLeeftijd();
				break;
			case "woonplaats":
				result = k.getAdres().getWoonplaats();
				break;
			case "laatste_onderhoud":
				result = dateFormat.format(k.getLaatsteOnderhoud());
				break;
			case "laatste_bezoek":
				result = dateFormat.format(k.getBezoek());
				break;
			default:
				result = "";
				break;
		}
		return result;
	}

	public static String[] getTags() {
		return tags;
	}

	public String getTemplateNaam() {
		return templateNaam;
	}

	public void setTemplateNaam(String templateNaam) {
		this.templateNaam = templateNaam;
	}

	public String getTemplateInhoud() {
		return templateInhoud;
	}

	public void setTemplateInhoud(String templateInhoud) {
		this.templateInhoud = templateInhoud;
	}
}
