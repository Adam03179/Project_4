package dbmodels;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String individualTaxNumber;
    private String logIn;
    private String password;

    public Client(int id, String name, String surname,
                  String individualTaxNumber, String logIn, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.individualTaxNumber = individualTaxNumber;
        this.logIn = logIn;
        this.password = password;
    }

    public Client(String name, String surname, String individualTaxNumber, String logIn, String password) {
        this.name = name;
        this.surname = surname;
        this.individualTaxNumber = individualTaxNumber;
        this.logIn = logIn;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIndividualTaxNumber() {
        return individualTaxNumber;
    }

    public void setIndividualTaxNumber(String individualTaxNumber) {
        this.individualTaxNumber = individualTaxNumber;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
