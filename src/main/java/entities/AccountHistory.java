package main.java.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * This class is an entity of database table 'history'
 *
 * @author Igor Gerasymenko
 */

public class AccountHistory {

    private int id;
    private final Account account;
    private final BigDecimal sum;
    private final String partnerName;
    private final Timestamp operationDate;
    private final OperationType operationType;

    public AccountHistory(Account account, BigDecimal sum, String partnerName,
                          Timestamp operationDate, OperationType operationType) {
        this.account = account;
        this.sum = sum;
        this.partnerName = partnerName;
        this.operationDate = operationDate;
        this.operationType = operationType;
    }

    public AccountHistory(int id, Account account, BigDecimal sum,
                          String partnerName, Timestamp operationDate,
                          OperationType operationType) {
        this.id = id;
        this.account = account;
        this.sum = sum;
        this.partnerName = partnerName;
        this.operationDate = operationDate;
        this.operationType = operationType;
    }

    public int getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public Timestamp getOperationDate() {
        return operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
