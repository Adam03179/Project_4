package dao;

import dbmodels.Client;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAOClient {

    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");


    public DAOClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean addClient(Client client) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddClient = connection.prepareStatement
                    (resourceBundle.getString("ADD_CLIENT"));

            psAddClient.setString(1, client.getName());
            psAddClient.setString(2, client.getSurname());
            psAddClient.setString(3, client.getIndividualTaxNumber());
            psAddClient.setString(4, client.getLogIn());
            psAddClient.setString(5, client.getPassword());

            return true;

        } catch (SQLException e) {
            logger.error("add client error", e);
            return false;
        }

    }

    public boolean changePassword(Client client, String newPassword) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psChangePassword = connection.prepareStatement
                    (resourceBundle.getString("CHANGE_PASSWORD"));
            psChangePassword.setString(1, newPassword);
            psChangePassword.setInt(2, client.getId());
            return true;

        } catch (SQLException e) {
            logger.error("change password error", e);
            return false;
        }

    }

    public Client getClient(String logIn, String password) {

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

            return new Client(id, name, surname, individualTaxNumber, logIn, password);

        } catch (SQLException e) {
            logger.error("get client error", e);
            return null;
        }

    }


}



