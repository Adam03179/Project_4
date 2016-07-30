package main.java.entities;

/**
 * This class is an entity of database table 'contacts'
 *
 * @author Igor Gerasymenko
 */

public class Contacts {
    private int id;
    private final User user;
    private final int postCode;
    private final String region;
    private final String city;
    private final String street;
    private final String numOfHouse;
    private final String numOfApartment;
    private final String telephoneNum;
    private final String email;

    public Contacts(User user, int postCode, String region, String city,
                    String street, String numOfHouse, String numOfApartment,
                    String telephoneNum, String email) {
        this.user = user;
        this.postCode = postCode;
        this.region = region;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.numOfApartment = numOfApartment;
        this.telephoneNum = telephoneNum;
        this.email = email;
    }

    public Contacts(User user, int postCode, String region, String city,
                    String street, String numOfHouse, String numOfApartment,
                    String telephoneNum, String email, int id) {
        this.user = user;
        this.postCode = postCode;
        this.region = region;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.numOfApartment = numOfApartment;
        this.telephoneNum = telephoneNum;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumOfHouse() {
        return numOfHouse;
    }

    public String getNumOfApartment() {
        return numOfApartment;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public String getEmail() {
        return email;
    }
}
