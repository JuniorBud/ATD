package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
	private ArrayList<User> users;

	/**
	 * 
	 * Constructor van klasse Users.
	 */
	public Users() {
		users = new ArrayList<User>();
	}

	/**
	 * 
	 * Controle of de te registreren gebruiker al bestaat.
	 * 
	 * @param username
	 *            Gebruikersnaam van de gebruiker.
	 * @param password
	 *            Wachtwoord van de gebruiker.
	 * @param permission
	 *            Regelt de persmissies van de gebruiker.
	 * @return Bevestigd of de gebruiker bestaat of niet.
	 */
	public boolean addUser(String username, String password, int permission) {
		boolean exists = false;
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			users.add(new User(username, password, permission));
		}
		return exists;
	}

	/**
	 * 
	 * Voegt de nieuwe gebruiker toe aan ArrayList users.
	 * 
	 * @param u
	 *            Verwijzing naar ArrayList users.
	 */
	public void addUser(User u) {
		users.add(u);
	}

	/**
	 * 
	 * Verwijderd de gebruiker uit ArrayList users
	 * 
	 * @param u
	 *            Verwijzing naar ArrayList users.
	 */
	public void removeUser(User u) {
		users.remove(u);
	}

	/**
	 * 
	 * Zoekt gebruiker op uit ArrayList users.
	 * 
	 * @param username
	 *            Gebruikersnaam van de gebruiker.
	 * @return Geeft de gevonden gebruiker terug.
	 */
	public User getUser(String username) {
		User user = null;
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		return user;
	}

	/**
	 * 
	 * Haalt een lijst met gebruikers.
	 * @return Geeft een lijst met gebruikers.
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * 
	 * Controle of gebruiker bestaat in de ArrayList users.
	 * 
	 * @param username
	 *            Gebruikersnaam van de gebruiker.
	 * @param password
	 *            Wachtwoord van de gebruiker.
	 * @return Geeft de gevonden gebruiker terug.
	 */
	public User checkUser(String username, String password) {

		User user = null;
		for (User u : users) {
			if (u.getPassword().equals(password)
					&& u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		return user;
	}
}
