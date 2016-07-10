package main.java.dao;

import main.java.dbmodels.Account;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAOAccount {
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOAccount.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOAccount(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean blockAccount(Account account) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (resourceBundle.getString(BLOCK_ACCOUNT));
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.error("sql exception", e);
            return false;
        }

    }

    public boolean unblockAccount(Account account) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (resourceBundle.getString(UNBLOCK_ACCOUNT));
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            logger.error("sql exception", e);
            return false;
        }

    }


    public boolean makeDeposit(Account account)


}
