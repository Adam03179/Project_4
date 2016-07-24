package dbmodels;

public class Contacts {
    private int id;
    private int userId;
    private int postCode;
    private String region;
    private String city;
    private String street;
    private String numOfHouse;
    private String numOfApartment;
    private String telephoneNum;
    private String email;

    public Contacts(int id, int userId, int postCode,
                    String region, String city, String street,
                    String numOfHouse, String numOfApartment,
                    String telephoneNum, String email) {
        this.id = id;
        this.userId = userId;
        this.postCode = postCode;
        this.region = region;
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
        this.numOfApartment = numOfApartment;
        this.telephoneNum = telephoneNum;
        this.email = email;
    }

    public Contacts(int userId, int postCode,
                    String region, String city, String street,
                    String numOfHouse, String numOfApartment,
                    String telephoneNum, String email) {
        this.userId = userId;
        this.postCode = postCode;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
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
