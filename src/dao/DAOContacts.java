package dao;

import dbmodels.Contacts;
import dbmodels.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAOContacts {
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOContacts(DataSource dataSource) {
        this.dataSource = dataSource;

    }


    public boolean addContacts(Contacts contacts) {


        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddContacts = connection.prepareStatement
                    (resourceBundle.getString("ADD_CONTACTS"));


            psAddContacts.setString(1, contacts.getCity());
            psAddContacts.setInt(2, contacts.getPostCode());
            psAddContacts.setString(3, contacts.getStreet());
            psAddContacts.setString(4, contacts.getNumOfHouse());
            psAddContacts.setString(5, contacts.getNumOfApartment());
            psAddContacts.setInt(6, contacts.getUserId());
            psAddContacts.setString(7, contacts.getTelephoneNum());
            psAddContacts.setString(8, contacts.getEmail());
            psAddContacts.setString(9, contacts.getRegion());
            psAddContacts.execute();
            return true;

        } catch (SQLException e) {
            logger.error("add contacts error", e);
            return false;
        }


    }

    public List<Contacts> getContacts(User user) {

        List<Contacts> contacts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetContacts = connection.prepareStatement
                    (resourceBundle.getString("GET_CONTACTS"));
            psGetContacts.setInt(1, user.getId());

            ResultSet resultSet = psGetContacts.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("contacts_id");
                int clientId = resultSet.getInt("clients_id");
                int postCode = resultSet.getInt("post_code");
                String region = resultSet.getString("region");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String numOfHouse = resultSet.getString("house_number");
                String numOfApartment = resultSet.getString("apartment_number");
                String telephoneNum = resultSet.getString("telephone_number");
                String email = resultSet.getString("email");

                contacts.add(new Contacts(id, clientId, postCode,
                        region, city, street, numOfHouse, numOfApartment,
                        telephoneNum, email));
            }
            return contacts;

        } catch (SQLException e) {
            logger.error("error get contacts", e);
            return null;
        }


    }


}
