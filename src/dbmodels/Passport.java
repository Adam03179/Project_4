package dbmodels;


import java.sql.Date;

public class Passport {

    private int id;
    private int clientId;
    private int number;
    private String series;
    private String issuingAuthority;
    private Date dateOfIssue;

    public Passport(int id, int clientId, int number, String series,
                    String issuingAuthority, Date dateOfIssue) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
        this.series = series;
        this.issuingAuthority = issuingAuthority;
        this.dateOfIssue = dateOfIssue;
    }

    public Passport(int clientId, int number, String series,
                    String issuingAuthority, Date dateOfIssue) {
        this.clientId = clientId;
        this.number = number;
        this.series = series;
        this.issuingAuthority = issuingAuthority;
        this.dateOfIssue = dateOfIssue;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
