package ua.gerasymenko.dao;

import ua.gerasymenko.models.Card;

import java.util.List;

/**
 * The CardAPI interface extends GenericDAO interface, this interface
 * needs for separation of the layers of MVC. Class that implements
 * this interface, may work with database.
 *
 * @author Igor Gerasymenko
 */
public interface CardAPI extends GenericDAO<Card> {
    public boolean isExist(String cardNumber);
    public List<Card> getAllCards(int accountId);
}
