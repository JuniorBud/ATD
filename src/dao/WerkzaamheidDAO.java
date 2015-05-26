package dao;

import model.Adres;
import model.Onderdeel;
import model.Werkzaamheid;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Samuel on 23-5-2015.
 */
public class WerkzaamheidDAO implements GenericDAO {
    private DataSource data;

    public WerkzaamheidDAO(DataSource data) {
        this.data =data;
    }

    @Override
    public Object find(Serializable id) {
        Werkzaamheid werkzaamheid = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM werkzaamheid WHERE werkzaamheid_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                //column index 2 = onderhoud_id
                HashMap<Onderdeel, Integer> items = new HashMap<>();
                //TODO: when onderdeelDAO updated
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
        return werkzaamheid;
    }

    @Override
    public List find() {
        List<Werkzaamheid> werkzaamheidList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM werkzaamheid;");
            pst.execute();
            rst = pst.getResultSet();
            while (rst.next()) {
                HashMap<Onderdeel, Integer> items = new HashMap<>();
                //TODO: when onderdeelDAO updated
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
        return werkzaamheidList;
    }

    @Override
    public Serializable save(Object value) {
        return null;
    }

    @Override
    public void update(Object value) {

    }

    @Override
    public void delete(Object value) {

    }
}
