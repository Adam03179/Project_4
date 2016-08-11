package ua.gerasymenko.dao;

import ua.gerasymenko.models.User;

public interface UserAPI extends GenericDAO<User> {
    public User getUser(String logIn, String password);
    public boolean isExist(String logIn, String password);
    public Integer getId(String logIn);
    public boolean isAdmin(int id);

}
