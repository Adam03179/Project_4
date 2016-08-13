package ua.gerasymenko.dao;

import ua.gerasymenko.models.Contacts;

import java.util.List;


/**
 * The ContactsAPI interface extends GenericDAO interface, this interface
 * needs for separation of the layers of MVC. Class that implements
 * this interface, may work with database.
 *
 * @author Igor Gerasymenko
 */
public interface ContactsAPI extends GenericDAO<Contacts> {
    public List<Contacts> getContacts(int userId);
}
