package main.java.entities;

/**
 * This class is an entity of database table 'users'
 *
 * @author Igor Gerasymenko
 */
public class User {
    private int id;
    private final String name;
    private final String surname;
    private final String individualTaxNumber;
    private final String logIn;
    private final String password;
    private final int numberOfPassport;
    private final String seriesOfPassport;
    private final boolean isAdmin;

     public User(int id, String name, String surname,
                String individualTaxNumber, String logIn, String password,
                int numberOfPassport, String seriesOfPassport,
                boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.individualTaxNumber = individualTaxNumber;
        this.logIn = logIn;
        this.password = password;
        this.numberOfPassport = numberOfPassport;
        this.seriesOfPassport = seriesOfPassport;
        this.isAdmin = isAdmin;
    }

    public User(String name, String surname, String individualTaxNumber,
                String logIn, String password, int numberOfPassport,
                String seriesOfPassport, boolean isAdmin) {

        this.name = name;
        this.surname = surname;
        this.individualTaxNumber = individualTaxNumber;
        this.logIn = logIn;
        this.password = password;
        this.numberOfPassport = numberOfPassport;
        this.seriesOfPassport = seriesOfPassport;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIndividualTaxNumber() {
        return individualTaxNumber;
    }

    public String getLogIn() {
        return logIn;
    }

    public String getPassword() {
        return password;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }

    public String getSeriesOfPassport() {
        return seriesOfPassport;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
