package main.java.entities;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is an entity of database table 'accounts'
 *
 * @author Igor Gerasymenko
 */
public class Account {
    private int id;
    private final User user;
    private final String number;
    private final double interest;
    private final Date openDate;
    private BigDecimal balance;
    private final String currency;
    private boolean blocked;

    public Account(User user, String number, double interest, Date openDate,
                   BigDecimal balance, String currency, boolean blocked) {
        this.user = user;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
        this.blocked = blocked;
    }

    public Account(int id, User user, String number, double interest,
                   Date openDate, BigDecimal balance, String currency,
                   boolean blocked) {
        this.id = id;
        this.user = user;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getNumber() {
        return number;
    }

    public double getInterest() {
        return interest;
    }

    public Date getOpenDate() {
        return openDate;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
