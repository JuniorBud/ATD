package dao;

import model.Adres;

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
public class AdresDAO implements GenericDAO {
    protected DataSource data = null;

    public AdresDAO(DataSource data) {
        this.data = data;
    }

    @Override
    public Object find(Serializable id) {
        Adres adr = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM adres WHERE adres_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
               adr = new Adres(rst.getLong(1),rst.getString(2),rst.getInt(3),rst.getString(4),
                       rst.getString(5),rst.getString(6));
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
        return adr;
    }

    @Override
    public List find() {
        List<Adres> adresList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM adres;");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                adresList.add(new Adres(rst.getLong(1),rst.getString(2),rst.getInt(3),rst.getString(4),
                        rst.getString(5),rst.getString(6)));
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
        return adresList;
    }

    @Override
    public Serializable save(Object value) {
        if (!(value instanceof Adres)) {
            return -1L;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("INSERT INTO adres (straat, huisnr, toevoeging, postcode, woonplaats) VALUES(?, ?, ?, ?, ?);", pst.RETURN_GENERATED_KEYS);
            Adres adres = (Adres) value;
            pst.setString(1, adres.getStraat());
            pst.setLong(2, (long) adres.getHuisnr());
            pst.setString(3, adres.getToevoeging());
            pst.setString(4, adres.getPostcode());
            pst.setString(5, adres.getWoonplaats());
            pst.execute();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
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
        return -1L;
    }

    @Override
    public void update(Object value) {
        if (!(value instanceof Adres)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Adres adres = (Adres) value;
            pst = con.prepareStatement("UPDATE adres SET straat= ?, huisnr= ?, toevoeging= ?, postcode= ?, woonplaats= ? WHERE adres_id = "+adres.getId()+";");
            pst.setString(1, adres.getStraat());
            pst.setLong(2, (long) adres.getHuisnr());
            pst.setString(3, adres.getToevoeging());
            pst.setString(4, adres.getPostcode());
            pst.setString(5, adres.getWoonplaats());
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
        if (!(value instanceof Adres)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Adres adres = (Adres) value;
            pst = con.prepareStatement("DELETE FROM adres WHERE adres_id= ?");
            pst.setLong(1, adres.getId());
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
}
