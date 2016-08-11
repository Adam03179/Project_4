package ua.gerasymenko.dao;

import ua.gerasymenko.models.Model;

public interface GenericDAO<K extends Model> {
    boolean create(K k);
    K read(int id);
    boolean delete(int id);

}
