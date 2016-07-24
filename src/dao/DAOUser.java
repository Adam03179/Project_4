package dao;

import dbmodels.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAOUser {

    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOUser.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");


    public DAOUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean addUser(User user) {

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

    public boolean changePassword(User user, String newPassword) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psChangePassword = connection.prepareStatement
                    (resourceBundle.getString("CHANGE_PASSWORD"));
            psChangePassword.setString(1, newPassword);
            psChangePassword.setInt(2, user.getId());
            psChangePassword.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("change password error", e);
            return false;
        }

    }

    public User getUser(String logIn, String password) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetUser = connection.prepareStatement
                    (resourceBundle.getString("GET_USER"));

            psGetUser.setString(1, logIn);
            psGetUser.setString(2, password);
            ResultSet result = psGetUser.executeQuery();

            int id = result.getInt("users_id");
            String name = result.getString("name");
            String surname = result.getString("surname");
            String individualTaxNumber = result.getString("individual_tax_number");
            boolean isAdmin = result.getBoolean("is_admin");
            int numberOfPassport = result.getInt("passport_number");
            String seriesOfPassport = result.getString("passport_series");

            result.next();

            return new User(id, name, surname, individualTaxNumber, logIn, password,
                    numberOfPassport, seriesOfPassport, isAdmin);


        } catch (SQLException e) {
            logger.error("get user error", e);
            return null;
        }

    }

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


}



