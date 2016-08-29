package ua.gerasymenko.dao;

import ua.gerasymenko.models.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The JdbcUser class responds for getting and putting information about
 * user into and from database with jdbc help.
 *
 * @author Igor Gerasymenko
 */
public class JdbcUser implements UserAPI {

    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(JdbcUser.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");


    public JdbcUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * This method adds information about user database.
     *
     * @param user
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean create(User user) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddClient = connection.prepareStatement
                    (resourceBundle.getString("ADD_USER"));

            psAddClient.setString(1, user.getName());
            psAddClient.setString(2, user.getSurname());
            psAddClient.setString(3, user.getIndividualTaxNumber());
            psAddClient.setString(4, user.getLogIn());
            psAddClient.setString(5, user.getPassword());
            psAddClient.setInt(6, user.getNumberOfPassport());
            psAddClient.setString(7, user.getSeriesOfPassport());
            psAddClient.execute();

            return true;

        } catch (SQLException e) {
            logger.error("add user error", e);
            return false;
        }

    }


    /**
     * This method gets user from database.
     *
     * @param logIn
     * @param password
     * @return User
     */
    public User getUser(String logIn, String password) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetUser = connection.prepareStatement
                    (resourceBundle.getString("GET_USER"));


            psGetUser.setString(1, logIn);
            psGetUser.setString(2, password);

            ResultSet resultSet = psGetUser.executeQuery();

            resultSet.next();

            return getUserFromDB(resultSet);


        } catch (SQLException e) {
            logger.error("get user error", e);
            return null;
        }

    }

    /**
     * This method checks if exist user in database or not.
     *
     * @param logIn
     * @param password
     * @return true - if user is exist, false - if not.
     */
    public boolean isExist(String logIn, String password) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psIsExist = connection.prepareStatement
                    (resourceBundle.getString("USER_IS_EXIST"));

            psIsExist.setString(1, logIn);
            psIsExist.setString(2, password);

            ResultSet resultSet = psIsExist.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("is exist user error", e);
            return false;
        }


    }

    /**
     * This method seeks in database the Id of user
     *
     * @param logIn
     * @return id of this user or null if user doesn't exist.
     */
    public Integer getId(String logIn) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetId = connection.prepareStatement
                    (resourceBundle.getString("GET_USER_ID"));

            psGetId.setString(1, logIn);
            ResultSet result = psGetId.executeQuery();
            result.next();
            return result.getInt("users_id");


        } catch (SQLException e) {
            logger.error("get user id error", e);
            return null;
        }

    }

    /**
     * This method checks if user admin, or not
     *
     * @param id
     * @return true if user id administrator, false if not.
     */
    public boolean isAdmin(int id) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psIsAdmin = connection.prepareStatement
                    (resourceBundle.getString("IS_ADMIN"));

            psIsAdmin.setInt(1, id);

            ResultSet resultSet = psIsAdmin.executeQuery();
            resultSet.next();
            return (resultSet.getInt("is_admin") == 1);

        } catch (SQLException e) {
            logger.error("is admin user error", e);
            return false;
        }
    }

    /**
     * This method seeks in database the user  by ID
     *
     * @param id
     * @return User or null if user doesn't exist.
     */
    public User read(int id) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement getUserById = connection.prepareStatement
                    (resourceBundle.getString("GET_USER_BY_ID"));

            getUserById.setInt(1, id);
            ResultSet resultSet = getUserById.executeQuery();
            resultSet.next();
            return getUserFromDB(resultSet);

        } catch (SQLException e) {
            logger.error("read user error", e);
            return null;
        }

    }


    /**
     * This method erases the User from database, by id
     *
     * @param id
     * @return true - if operation successful, false - if operation failed.
     */
    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("DELETE_USER"));
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("delete user error ", e);
            return false;
        }
    }


    /**
     * This method changes old password to new password
     *
     * @param id
     * @param newPassword
     * @return true - if operation successful, false - if operation failed.
     */
    @Override
    public boolean changePassword(int id, String newPassword) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("CHANGE_PASSWORD"));
            psDelete.setString(1, newPassword);
            psDelete.setInt(2, id);

            psDelete.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("change password error ", e);
            return false;
        }
    }

    /**
     * This method seeks in database the password of user
     *
     * @param userId
     * @return id of this user or null if password doesn't exist.
     */
    @Override
    public String getPassword(int userId) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetPassword = connection.prepareStatement
                    (resourceBundle.getString("GET_PASSWORD"));

            psGetPassword.setInt(1, userId);
            ResultSet result = psGetPassword.executeQuery();
            result.next();
            return result.getString("password");


        } catch (SQLException e) {
            logger.error("get password error", e);
            return null;
        }

    }


    /**
     * This method needs only for getting user from database by result of ResultSet
     *
     * @param resultSet
     * @return user or null if user doesn't exist.
     */
    private User getUserFromDB(ResultSet resultSet) {

        try {
            int id = resultSet.getInt("users_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String individualTaxNumber = resultSet.getString("individual_tax_number");
            String logIn = resultSet.getString("log_in");
            String password = resultSet.getString("password");
            boolean isAdmin = resultSet.getBoolean("is_admin");
            int numberOfPassport = resultSet.getInt("passport_number");
            String seriesOfPassport = resultSet.getString("passport_series");

            return new User(id, name, surname, individualTaxNumber, logIn, password,
                    numberOfPassport, seriesOfPassport, isAdmin);
        } catch (SQLException e) {
            logger.error("get user from DB error", e);
            return null;
        }
    }


}



