package ua.gerasymenko.dao;

import ua.gerasymenko.models.Contacts;
import ua.gerasymenko.models.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The JdbcContacts class responds for getting and putting information about
 * user's contacts into and from database.
 *
 * @author Igor Gerasymenko
 */
public class JdbcContacts implements ContactsAPI {
    private DataSource dataSource;
    private JdbcFactory jdbcFactory;
    private static final Logger logger = Logger.getLogger(JdbcCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public JdbcContacts(DataSource dataSource) {
        this.jdbcFactory = JdbcFactory.getInstance();
        this.dataSource = dataSource;

    }

    /**
     * This method adds information about user's contacts to database.
     *
     * @param contacts
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean create(Contacts contacts) {


        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddContacts = connection.prepareStatement
                    (resourceBundle.getString("ADD_CONTACTS"));


            psAddContacts.setString(1, contacts.getCity());
            psAddContacts.setInt(2, contacts.getPostCode());
            psAddContacts.setString(3, contacts.getStreet());
            psAddContacts.setString(4, contacts.getNumOfHouse());
            psAddContacts.setString(5, contacts.getNumOfApartment());
            psAddContacts.setInt(6, contacts.getUser().getId());
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

    @Override
    public Contacts read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetContactsById = connection.prepareStatement
                    (resourceBundle.getString("GET_CONTACTS_BY_ID"));

            psGetContactsById.setInt(1, id);
            ResultSet result = psGetContactsById.executeQuery();

            result.next();
            return getContactsFromDb(result);

        } catch (SQLException e) {
            logger.error("read card error", e);
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("DELETE_CONTACTS"));

            psDelete.setInt(1, id);

            ResultSet resultSet = psDelete.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("delete contacts error ", e);
            return false;
        }
    }

    /**
     * This method gets information about contacts one specific user .
     *
     * @param userId
     * @return list of all contacts of one user
     */
    public List<Contacts> getContacts(int userId) {

        List<Contacts> contacts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetContacts = connection.prepareStatement
                    (resourceBundle.getString("GET_CONTACTS"));
            psGetContacts.setInt(1, userId);

            ResultSet resultSet = psGetContacts.executeQuery();

            while (resultSet.next()) {

                contacts.add(getContactsFromDb(resultSet));
            }
            return contacts;

        } catch (SQLException e) {
            logger.error("error get contacts", e);
            return null;
        }


    }

    /**
     * This method needs only for getting a contacts from database by result of ResultSet
     *
     * @param resultSet
     * @return contacts or null if contacts doesn't exist.
     */
    private Contacts getContactsFromDb(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("contacts_id");
            int userId = resultSet.getInt("users_id");
            int postCode = resultSet.getInt("post_code");
            String region = resultSet.getString("region");
            String city = resultSet.getString("city");
            String street = resultSet.getString("street");
            String numOfHouse = resultSet.getString("house_number");
            String numOfApartment = resultSet.getString("apartment_number");
            String telephoneNum = resultSet.getString("telephone_number");
            String email = resultSet.getString("email");

            JdbcUser jdbcUser = jdbcFactory.getDAOUser();
            User user = jdbcUser.read(userId);
            return new Contacts(user, postCode, region, city, street,
                    numOfHouse, numOfApartment, telephoneNum, email, id);

        } catch (SQLException e) {
            logger.error("error get contacts from db", e);
            return null;
        }
    }


}
