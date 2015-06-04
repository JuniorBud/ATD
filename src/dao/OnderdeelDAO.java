package dao;

import model.Onderdeel;

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
public class OnderdeelDAO implements GenericDAO {
    private DataSource data;

    public OnderdeelDAO(DataSource data) {
        this.data = data;
    }

    @Override
    public Object find(Serializable id) {
        Onderdeel onderdeel = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM onderdeel WHERE onderdeel_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                onderdeel = new Onderdeel(rst.getLong(1),rst.getString(2),rst.getString(4)
                ,rst.getDouble(3),rst.getString(5));
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
        return onderdeel;
    }

    @Override
    public List find() {
        List<Onderdeel> onderdeelList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM onderdeel;");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                onderdeelList.add(new Onderdeel(rst.getLong(1),rst.getString(2),rst.getString(4)
                        ,rst.getDouble(3),rst.getString(5)));
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
        return onderdeelList;
    }

    @Override
    public Serializable save(Object value) {
        if (!(value instanceof Onderdeel)) {
            return -1L;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Onderdeel onderdeel = (Onderdeel) value;
            pst = con.prepareStatement("INSERT INTO onderdeel(artikelnr, prijs, merk, type) VALUES (?,?,?,?);", pst.RETURN_GENERATED_KEYS);
            pst.setString(1, onderdeel.getArtikelnr());
            pst.setDouble(2, onderdeel.getPrijs());
            pst.setString(3, onderdeel.getMerk());
            pst.setString(4, onderdeel.getType());
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
        if (!(value instanceof Onderdeel)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Onderdeel onderdeel = (Onderdeel) value;
            pst = con.prepareStatement("UPDATE onderdeel SET artikelnr = ?, prijs = ?, merk = ?, type = ? " +
                    "WHERE onderdeel_id = " +onderdeel.getId() +";");
            pst.setString(1, onderdeel.getArtikelnr());
            pst.setDouble(2, onderdeel.getPrijs());
            pst.setString(3, onderdeel.getMerk());
            pst.setString(4, onderdeel.getType());
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
        if (!(value instanceof Onderdeel)) {
            return;
        }
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = data.getConnection();
            Onderdeel onderdeel = (Onderdeel) value;
            pst = con.prepareStatement("DELETE FROM onderdeel WHERE onderdeel_id= ?");
            pst.setLong(1, onderdeel.getId());
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
