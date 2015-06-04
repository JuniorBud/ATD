package Domain;


import java.io.Serializable;

public class User implements Serializable{
	private String username, password;
	private int permission;

/**
 * 
 * Constructor van klasse User.
 * @param user Gebruikersnaam van de gebruiker.
 * @param pass Wachtwoord van de  gebruiker.
 * @param perm Permissies van de gebruiker.
 */
	public User(String user, String pass, int perm) {
		username = user;
		password = pass;
		permission = perm;
	}
/**
 * 
 * Haalt de gebruikersnaam op.
 * @return Geeft de gebruikersnaam terug.
 */
	public String getUsername() {
		return username;
	}
/**
 * 
 * Bepaald de gebruikersnaam.
 * @param username Gebruikersnaam van de gebruiker.
 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
 * 
 * Haalt het wachtwoord op.
 * @return Geeft het woachtwoord van de gebruiker terug.
 */
	public String getPassword() {
		return password;
	}
/**
 * 
 * Bepaald het wachtwoord.
 * @param password Wachtwoord van de gebruiker.
 */
	public void setPassword(String password) {
		this.password = password;
	}
/**
 * 
 * Haalt de permissies op.
 * @return Geeft de permissies van de gebruiker terug.
 */
	public int getPermission() {
		return permission;
	}
/**
 * 
 * Bepaald de permissies.
 * @param permission Permissies van de gebruiker.
 */
	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getType() {
		String type = "";
		switch (permission) {
			case 1:
				type = "Admin";
				break;
			case 2:
				type = "Administratief Medewerker";
				break;
			case 3:
				type = "Monteur";
				break;
		}
		return type;
	}
}
