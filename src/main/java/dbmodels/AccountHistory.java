package main.java.dbmodels;

import java.math.BigDecimal;
import java.util.Date;

public class AccountHistory {

    private int id;
    private int accountId;
    private BigDecimal summ;
    private String contragentsName;
    private Date operationDate;

    private enum OperationType {DEPOSIT, BLOCK, TRANSFER, UNBLOCK}


    public AccountHistory(int id, int accountId, BigDecimal summ,
                          String contragentsName, Date operationDate) {
        this.id = id;
        this.accountId = accountId;
        this.summ = summ;
        this.contragentsName = contragentsName;
        this.operationDate = operationDate;
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

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public String getContragentsName() {
        return contragentsName;
    }

    public void setContragentsName(String contragentsName) {
        this.contragentsName = contragentsName;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
