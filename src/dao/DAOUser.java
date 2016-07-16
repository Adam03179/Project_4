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
    private static final Logger logger = Logger.getLogger(DAOCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");


    public DAOUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean addClient(User user) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddClient = connection.prepareStatement
                    (resourceBundle.getString("ADD_CLIENT"));

            psAddClient.setString(1, user.getName());
            psAddClient.setString(2, user.getSurname());
            psAddClient.setString(3, user.getIndividualTaxNumber());
            psAddClient.setString(4, user.getLogIn());
            psAddClient.setString(5, user.getPassword());

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
            return true;

        } catch (SQLException e) {
            logger.error("change password error", e);
            return false;
        }

    }

    public User getClient(String logIn, String password) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psChangePassword = connection.prepareStatement
                    (resourceBundle.getString("GET_CLIENT"));

            psChangePassword.setString(1, logIn);
            psChangePassword.setString(2, password);
            ResultSet result = psChangePassword.executeQuery();

            int id = result.getInt("clients_id");
            String name = result.getString("name");
            String surname = result.getString("surname");
            String individualTaxNumber = result.getString("individual_tax_number");
            boolean isAdmin = result.getBoolean("is_admin");

            return new User(id, name, surname, individualTaxNumber, logIn, password,isAdmin);

        } catch (SQLException e) {
            logger.error("get client error", e);
            return null;
        }

    }


}



