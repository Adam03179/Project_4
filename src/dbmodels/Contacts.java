package dbmodels;

public class Contacts {
    private int id;
    private int clientId;
    private int postCode;
    private String country;
    private String region;
    private String city;
    private String street;
    private String numOfHouse;
    private String numOfApartment;
    private String telephoneNum;
    private String email;

    public Contacts(int id, int clientId, int postCode, String country,
                    String region, String city, String street,
                    String numOfHouse, String numOfApartment,
                    String telephoneNum, String email) {
        this.id = id;
        this.clientId = clientId;
        this.postCode = postCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.numOfApartment = numOfApartment;
        this.telephoneNum = telephoneNum;
        this.email = email;
    }

    public Contacts(int clientId, int postCode, String country,
                    String region, String city, String street,
                    String numOfHouse, String numOfApartment,
                    String telephoneNum, String email) {
        this.clientId = clientId;
        this.postCode = postCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.numOfApartment = numOfApartment;
        this.telephoneNum = telephoneNum;
        this.email = email;
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

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumOfHouse() {
        return numOfHouse;
    }

    public void setNumOfHouse(String numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    public String getNumOfApartment() {
        return numOfApartment;
    }

    public void setNumOfApartment(String numOfApartment) {
        this.numOfApartment = numOfApartment;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
