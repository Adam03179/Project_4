package ua.gerasymenko.dao;

import ua.gerasymenko.models.Contacts;

import java.util.List;

public interface ContactsAPI extends GenericDAO<Contacts> {
    public List<Contacts> getContacts(int userId);
}
