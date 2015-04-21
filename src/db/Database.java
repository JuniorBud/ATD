package db;

import resources.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
/*
    * General database-utility class for common DB operations
    * such as logging in and registering a new user.
    *
    * Author: Samuel Hessel
    *
    * TODO: The methods from this class have not be tested, and neither have the SQL-statements.
    * TODO: As soon as the logic is in place, please TEST ASAP!
 */


public class Database {

    public Database() {

    }
    public static boolean verifyLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InstantiationException, IllegalAccessException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = getDBConnection();
            pst = con.prepareStatement("SELECT * FROM account WHERE username = " + username);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                /*
                    * Get the hashed-password from the database, and check whether
                    * hashing the supplied password with the same salt matches it.
                 */
                String returnedPass = rst.getString(3);
                if (PasswordHash.validatePassword(password, returnedPass)) {
                    return true; //Success!
                }
            }
            else {
                return false; //The user does not exist.
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return false; //Default return-value, in case anything goes wrong.
    }
    public static boolean registerUser(String username, String password, String voornaam,
                                String achternaam, String adres, String plaats, String telnr) throws InvalidKeySpecException, NoSuchAlgorithmException,
            InstantiationException, IllegalAccessException {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            con = getDBConnection();
            //The PBKDF2-hash of the password is stored in the database
            pst = con.prepareStatement("INSERT INTO account (username, password)" + "VALUES (" + username + ", " + PasswordHash.createHash(password) + ");",
                    pst.RETURN_GENERATED_KEYS); //TODO: Will "RETURN_GENERATED KEYS" work?
            int rows = pst.executeUpdate();
            if (rows == 0) { //Checks if the new user account was created successfully
                throw new SQLException("Creation of the user failed");
            }
            /*
                *  When a user-account has successfully been created, a customer with the supplied credentials is automatically added.
                *  This could eventually be changed to having the accounts and customers separated, but probably won't for convenience.
             */
            try (ResultSet keys = pst.getGeneratedKeys()){
                /*
                    * If the new user account was created successfully, get the generated primary key for the new user account,
                    * to be used as foreign-key for the new customer-record.
                */
                if (keys.next()) {
                    pst2 = con.prepareStatement("INSERT INTO klant (account_id, voornaam, achternaam, adres, plaats, telnr)" + "VALUES ("
                            + keys.getLong(1) + "," + voornaam + ", " + achternaam + ", " + adres + ", " + plaats + ", " + telnr + ");");
                    pst2.execute();
                    return true; //Success!
                }
                else {
                    throw new SQLException("Obtaining the account-id failed");
                }
            }

        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }

        }
        return false;
    }

    public static Connection getDBConnection() throws IllegalAccessException, InstantiationException {
        /*
            * Method opens and returns a new database-connection;
            * will return null upon failure
         */
        Connection con = null;
        /*
            * Keep in mind that the DB either has to be configured as a Tomcat-resource,
            * or that the DB-connector-jar has to be added to $TOMCAT/lib
         */
        try {
            Class.forName(DBReference.DBDriver).newInstance();
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DBReference.DBHost, DBReference.DBUser, DBReference.DBPass);
            return con;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return con;
    }

}
