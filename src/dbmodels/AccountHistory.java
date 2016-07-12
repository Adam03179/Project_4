package dbmodels;

import java.sql.Date;

public class AccountHistory {

    private int id;
    private int accountId;
    private double sum;
    private String partnerName;
    private Date operationDate;
    private OperationType operationType;


    public AccountHistory(int id, int accountId, double sum,
                          String partnerName, Date operationDate,
                          OperationType operationType) {
        this.id = id;
        this.accountId = accountId;
        this.sum = sum;
        this.partnerName = partnerName;
        this.operationDate = operationDate;
        this.operationType = operationType;
    }

    public AccountHistory(int accountId, double sum, String partnerName, Date operationDate, OperationType operationType) {
        this.accountId = accountId;
        this.sum = sum;
        this.partnerName = partnerName;
        this.operationDate = operationDate;
        this.operationType = operationType;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
