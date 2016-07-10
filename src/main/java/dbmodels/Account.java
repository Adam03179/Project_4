package main.java.dbmodels;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    private int id;
    private int clientId;
    private String number;
    private double interest;
    private Date openDate;
    private BigDecimal balance;
    private String currency;
    private boolean isBlocked;

    public Account(int id, int clientId, String number, double interest,
                   Date openDate, BigDecimal balance, String currency) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
        this.isBlocked = false;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
