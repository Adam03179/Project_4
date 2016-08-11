package ua.gerasymenko.dao;

import ua.gerasymenko.models.Card;

import java.util.List;

public interface CardAPI extends GenericDAO<Card> {
    public boolean isExist(String cardNumber);
    public List<Card> getAllCards(int accountId);
}
