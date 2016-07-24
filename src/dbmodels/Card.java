package dbmodels;


import java.sql.Date;

public class Card {
    private int id;
    private int accountId;
    private String number;
    private int pinCode;
    private Date expirationDate;
    private String cardStandard;


    public Card(int id, int accountId, String number, int pinCode, Date expirationDate, String cardStandard) {
        this.id = id;
        this.accountId = accountId;
        this.number = number;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.cardStandard = cardStandard;
    }

    public Card(int accountId, String number, int pinCode, Date expirationDate, String cardStandard) {
        this.accountId = accountId;
        this.number = number;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.cardStandard = cardStandard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardStandard() {
        return cardStandard;
    }

    public void setCardStandard(String cardStandard) {
        this.cardStandard = cardStandard;
    }
}
