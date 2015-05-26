package dao;

import Domain.Adres;
import Domain.Auto;

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
public class AutoDAO implements GenericDAO {
    private DataSource data;

    public AutoDAO(DataSource data) {
        this.data =data;
    }

    @Override
    public Object find(Serializable id) {
        Auto auto = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM auto WHERE auto_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                //column index 2 = customer id
                auto = new Auto(rst.getLong(1),rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getDate(6),rst.getInt(7));
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
        return auto;
    }

    @Override
    public List find() {
        List<Auto> autoList = new ArrayList<Auto>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM auto;");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                autoList.add(new Auto(rst.getLong(1),rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getDate(6),rst.getInt(7)));
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
        return autoList;
    }
    public List findByKlantId(Long id) {
        List<Auto> autoList = new ArrayList<Auto>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM auto WHERE klant_id = " + id +";");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                autoList.add(new Auto(rst.getLong(1),rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getDate(6),rst.getInt(7)));
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
        return autoList;
    }

    @Override
    public Serializable save(Object value) {
        return null;
    }
    /*
        * A car is always saved concurrently with a user.
     */
    public Serializable save2(Object value, Long klantId) {
        if (!(value instanceof Auto)) {
            return -1L;
        }
        Auto auto = (Auto) value;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("INSERT INTO auto (klant_id, merk, type, kenteken, bouwjaar, aantalkm) VALUES (?,?,?,?,?,?);", pst.RETURN_GENERATED_KEYS);
            pst.setLong(1,klantId);
            pst.setString(2, auto.getMerk());
            pst.setString(3, auto.getType());
            pst.setString(4, auto.getKenteken());
            pst.setDate(5, auto.getBouwjaar());
            pst.setInt(6, auto.getAantalkm());
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
        if (!(value instanceof Auto)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Auto auto = (Auto) value;
            pst = con.prepareStatement("UPDATE auto SET merk= ?, type= ?, kenteken= ?, bouwjaar= ?, aantalkm = ? WHERE auto_id = "+auto.getId()+";");
            pst.setString(1, auto.getMerk());
            pst.setString(2, auto.getType());
            pst.setString(3, auto.getKenteken());
            pst.setDate(4, auto.getBouwjaar());
            pst.setInt(5, auto.getAantalkm());
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
        if (!(value instanceof Auto)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Auto auto = (Auto) value;
            pst = con.prepareStatement("DELETE FROM auto WHERE auto_id= ?");
            pst.setLong(1, auto.getId());
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
