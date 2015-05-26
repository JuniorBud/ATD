package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Samuel on 18-5-2015.
 */
public interface GenericDAO<K extends Serializable, T> {
    public T find(K id);
    public List<T> find();
    public K save(T value);
    public void update(T value);
    public void delete(T value);
}