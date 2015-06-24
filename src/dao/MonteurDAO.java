package dao;

import Domain.Adres;
import Domain.Monteur;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Samuel on 18-5-2015.
 */
public class MonteurDAO implements GenericDAO {
    private DataSource data;
    private AdresDAO adresDAO;

    public MonteurDAO(DataSource data) {
        this.data = data;
        adresDAO = new AdresDAO(data);
    }

    @Override
    public Object find(Serializable id) {
        Monteur monteur = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM monteur WHERE monteur_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                monteur = new Monteur(rst.getLong(1), rst.getString(4),
                        rst.getString(5),rst.getString(6),rst.getDouble(9),
                        (Adres)adresDAO.find(rst.getLong(3)),rst.getString(7), rst.getString(8));
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
                if (rst != null) {
                    rst.close();
                }
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return monteur;
    }

    @Override
    public List find() {
        List<Monteur> monteurList = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM monteur;");
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                 monteurList.add(new Monteur(rst.getLong(1), rst.getString(4),
                        rst.getString(5),rst.getString(6),rst.getDouble(9),
                        (Adres)adresDAO.find(rst.getLong(3)),rst.getString(7), rst.getString(8)));
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
                if (rst != null) {
                    rst.close();
                }
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return monteurList;
    }

    @Override
    public Serializable save(Object value) {
        return null;
    }

    @Override
    public void update(Object value) {
        if (!(value instanceof Monteur)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Monteur monteur = (Monteur) value;
            pst = con.prepareStatement("UPDATE monteur SET voornaam= ?, toevoeging= ?, achternaam = ?, email= ?, telnr= ?, salaris= ? WHERE monteur_id = "+ monteur.getId()+";");
            pst.setString(1, monteur.getNaam());
            pst.setString(2, monteur.getTussenvoegsel());
            pst.setString(3, monteur.getAchternaam());
            pst.setString(4, monteur.getEmail());
            pst.setString(5, monteur.getTelnr());
            pst.setDouble(6, monteur.getSalaris());
            pst.execute();
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
    }

    @Override
    public void delete(Object value) {

    }
}
