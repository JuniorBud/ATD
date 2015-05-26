package dao;

import model.Onderhoud;

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
public class KlusDAO implements GenericDAO {
    private DataSource data;
    private OnderdeelDAO onderdeelDAO;
    public KlusDAO(DataSource data) {
        this.data = data;
        onderdeelDAO = new OnderdeelDAO(data);
    }

    @Override
    public Object find(Serializable id) {
        Onderhoud onderhoud = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            con = data.getConnection();
            pst = con.prepareStatement("SELECT * FROM onderhoud WHERE onderhoud_id = ?;");
            pst.setLong(1, (long)id);
            pst.execute();
            rst = pst.getResultSet();
            if (rst.next()) {
                //todo: when monteurDAO done
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
        return onderhoud;
    }

    @Override
    public List find() {
        return null;
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

