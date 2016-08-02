package ua.gerasymenko.models;


import java.sql.Date;

/**
 * This class is an entity of database table 'cards'
 *
 * @author Igor Gerasymenko
 */

public class Card {
    private int id;
    private final Account account;
    private final String number;
    private final int pinCode;
    private final Date expirationDate;
    private final String cardStandard;

    public Card(Account account, String number, int pinCode,
                Date expirationDate, String cardStandard) {
        this.account = account;
        this.number = number;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.cardStandard = cardStandard;
    }

    public Card(int id, Account account, String number, int pinCode,
                Date expirationDate, String cardStandard) {
        this.id = id;
        this.account = account;
        this.number = number;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.cardStandard = cardStandard;
    }

    public int getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getNumber() {
        return number;
    }

    public int getPinCode() {
        return pinCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getCardStandard() {
        return cardStandard;
    }
}


