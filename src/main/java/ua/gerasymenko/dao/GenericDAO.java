package ua.gerasymenko.dao;

import ua.gerasymenko.models.Model;

/**
 * This interface needs for separation CRUD-methods from all others. This interface
 * should be implemented by another mor specific interface, that works with any Model-class.
 * Update method is absent because in this project it does not using.
 *
 * @author Igor Gerasymenko
 */
public interface GenericDAO<K extends Model> {
    boolean create(K k);
    K read(int id);
    boolean delete(int id);

}
