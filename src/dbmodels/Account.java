package dbmodels;

import java.sql.Date;

public class Account {
    private int id;
    private int userId;
    private String number;
    private double interest;
    private Date openDate;
    private double balance;
    private String currency;
    private boolean blocked;


    public Account(int id, int userId, String number, double interest,
                   Date openDate, double balance, String currency) {
        this.id = id;
        this.userId = userId;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int userId, String number, double interest, Date openDate,
                   double balance, String currency) {
        this.userId = userId;
        this.number = number;
        this.interest = interest;
        this.openDate = openDate;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int id, int userId, String number, double interest,
                   Date openDate, double balance, String currency,
                   boolean blocked) {
        this.id = id;
        this.userId = userId;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return blocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.blocked = isBlocked;
    }
}
