package dao;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Samuel on 18-5-2015.
 */
public class BrandstofDAO implements GenericDAO {
    private DataSource data;
    public BrandstofDAO(DataSource data) {
        this.data = data;
    }

    @Override
    public Object find(Serializable id) {
        return null;
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
