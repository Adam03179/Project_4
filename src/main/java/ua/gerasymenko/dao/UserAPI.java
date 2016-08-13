package ua.gerasymenko.dao;

import ua.gerasymenko.models.User;

/**
 * The UserAPI interface extends GenericDAO interface, this interface
 * needs for separation of the layers of MVC. Class that implements
 * this interface, may work with database.
 *
 * @author Igor Gerasymenko
 */
public interface UserAPI extends GenericDAO<User> {
    public User getUser(String logIn, String password);
    public boolean isExist(String logIn, String password);
    public Integer getId(String logIn);
    public boolean isAdmin(int id);

}
