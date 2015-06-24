package dao;

import Domain.Adres;
import Domain.Auto;
import Domain.Klant;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 18-5-2015.
 */
public class KlantDAO implements GenericDAO {
    private DataSource data;
    private AdresDAO adresDAO;
    private AutoDAO autoDao;

    public KlantDAO(DataSource data) {
        adresDAO = new AdresDAO(data);
        autoDao = new AutoDAO(data);
        this.data = data;
    }

    @Override
    public Object find(Serializable id) {
        Klant klant = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM klant WHERE klant_id = ?;");
            pst.setLong(1, (long) id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                //column index 2 = acc id
               klant = new Klant(rst.getLong(1), rst.getString(4),
                       rst.getString(5),rst.getString(6),rst.getString(7),
                       rst.getString(8), (Adres)adresDAO.find(rst.getLong(3)),
                       (ArrayList<Auto>)autoDao.findByKlantId((long)id));

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
        return klant;
    }

    @Override
    public List find() {
        List<Klant> klantList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM klant;");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                klantList.add(new Klant(rst.getLong(1), rst.getString(4),
                        rst.getString(5),rst.getString(6),rst.getString(7),
                        rst.getString(8), (Adres)adresDAO.find(rst.getLong(3)),
                        (ArrayList<Auto>)autoDao.findByKlantId((long)rst.getLong(1))));
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
        return klantList;
    }
    //For convenience, user-registration is done in the DB-utility class.
    @Override
    public Serializable save(Object value) {
        return null;
    }


    @Override
    public void update(Object value) {
        if (!(value instanceof Klant)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            con = data.getConnection();
            Klant klant = (Klant) value;
            pst = con.prepareStatement("UPDATE klant SET voornaam= ?, toevoeging= ?,achternaam= ?, email= ?, telnr= ? WHERE klant_id = " + klant.getId() + ";");
            pst.setString(1, klant.getNaam());
            pst.setString(2, klant.getTussenvoegsel());
            pst.setString(3, klant.getAchternaam());
            pst.setString(4, klant.getEmail());
            pst.setString(5, klant.getTelnr());
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
        if (!(value instanceof Klant)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            con = data.getConnection();
            Klant klant = (Klant) value;
            pst = con.prepareStatement("DELETE FROM klant WHERE klant_id= ?;");
            pst.setLong(1, klant.getId());
            int rows = pst.executeUpdate();
            //if a customer is deleted, the associated cars are as well.
            if (rows > 0) {
                pst2 = con.prepareStatement("DELETE FROM auto WHERE klant_id = ?;");
                pst2.execute();
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
    }
}
