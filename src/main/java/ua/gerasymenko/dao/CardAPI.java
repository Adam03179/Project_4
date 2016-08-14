package ua.gerasymenko.dao;

import ua.gerasymenko.models.Card;

import java.util.List;

/**
 * The CardAPI extends GenericDAO interface.
 * It needs for separation of the layers of MVC.
 * Class that implements this interface, should work with database.
 *
 * @author Igor Gerasymenko
 */
public interface CardAPI extends GenericDAO<Card> {
    public boolean isExist(String cardNumber);

    public List<Card> getAllCards(int accountId);
}
