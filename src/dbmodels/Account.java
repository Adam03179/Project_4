package dbmodels;

import java.sql.Date;

public class Account {
    private int id;
    private int clientId;
    private String number;
    private double interest;
    private Date openDate;
    private double balance;
    private String currency;
    private boolean isBlocked;


    public Account(int id, int clientId, String number, double interest,
                   Date openDate, double balance, String currency, boolean isBlocked) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
        this.isBlocked = isBlocked;
    }

    public Account(int clientId, String number, double interest, Date openDate, double balance, String currency, boolean isBlocked) {
        this.clientId = clientId;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}
