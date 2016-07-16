package dao;

import dbmodels.User;
import dbmodels.Passport;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;

public class DAOPassport {
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOCard.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOPassport(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean addPassport(Passport passport) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psAddPAssport = connection.prepareStatement
                    (resourceBundle.getString("ADD_PASSPORT"));

            psAddPAssport.setInt(1, passport.getNumber());
            psAddPAssport.setString(2, passport.getSeries());
            psAddPAssport.setString(3, passport.getIssuingAuthority());
            psAddPAssport.setDate(4, passport.getDateOfIssue());
            psAddPAssport.setInt(5, passport.getClientId());

            return true;

        } catch (SQLException e) {
            logger.error("add passport error", e);
            return false;
        }
    }

    public Passport getPassport(User user) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetPAssport = connection.prepareStatement
                    (resourceBundle.getString("GET_PASSPORT"));

            psGetPAssport.setInt(1, user.getId());

            ResultSet resultSet = psGetPAssport.executeQuery();

            int id = resultSet.getInt("passport_id");
            int clientId = resultSet.getInt("clients_id");
            int number = resultSet.getInt("number");
            String series = resultSet.getString("series");
            String issuingAuthority = resultSet.getString("issuing_authority");
            Date dateOfIssue = resultSet.getDate("date_of_issue");

            return new Passport(id, clientId, number, series, issuingAuthority, dateOfIssue);

        } catch (SQLException e) {
            logger.error("get passport error", e);
            return null;
        }


    }
}
