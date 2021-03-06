package ua.gerasymenko.dao;

import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.OperationType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The JdbcAccountHistory class responds for getting and putting information about
 * operations history in bank account with jdbc help.
 *
 * @author Igor Gerasymenko
 */

public class JdbcAccountHistory implements AccountHistoryAPI {
    private DataSource dataSource;

    private static final Logger logger =
            Logger.getLogger(JdbcAccountHistory.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public JdbcAccountHistory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * This method adds information about new history to database.
     *
     * @param history
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean create(AccountHistory history) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psCreateHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));

            psCreateHistory.setInt(1, history.getAccount().getId());
            psCreateHistory.setString(2, history.getOperationType().name());
            psCreateHistory.setBigDecimal(3, history.getSum());
            psCreateHistory.setString(4, history.getPartnerName());
            psCreateHistory.setTimestamp(5, history.getOperationDate());
            psCreateHistory.execute();
            return true;
        } catch (SQLException e) {
            logger.error("create history error ", e);
            return false;
        }


    }

    /**
     * This method seeks in database the accountHistory by ID
     *
     * @param id
     * @return AccountHistory object
     */
    @Override
    public AccountHistory read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetHistoryById = connection.prepareStatement
                    (resourceBundle.getString("GET_HISTORY_BY_ID"));

            psGetHistoryById.setInt(1, id);
            ResultSet result = psGetHistoryById.executeQuery();

            result.next();
            return getAccountHistoryFromDB(result);

        } catch (SQLException e) {
            logger.error("read history by id error", e);
            return null;
        }
    }

    /**
     * This method erases the accountHistory from database, by id
     *
     * @param id
     * @return true - if operation successful, false - if operation failed.
     */
    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("DELETE_HISTORY"));

            psDelete.setInt(1, id);

            psDelete.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("delete history error ", e);
            return false;
        }
    }


    /**
     * This method adds to database information about operation in bank account.
     *
     * @param history    about the operation which will be added in database
     * @param connection
     * @return true - if operation successful, false - if operation failed.
     * @throws SQLException
     */
    public boolean writeHistory(AccountHistory history,
                                Connection connection) throws SQLException {

        try {

            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));

            psWriteHistory.setInt(1, history.getAccount().getId());
            psWriteHistory.setString(2, history.getOperationType().name());
            psWriteHistory.setBigDecimal(3, history.getSum());
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setTimestamp(5, history.getOperationDate());
            psWriteHistory.execute();
            return true;
        } catch (SQLException e) {
            logger.error("write history error ", e);
            connection.rollback();
            return false;
        }


    }

    /**
     * This method gets information about all history one specific bank account .
     *
     * @param accountId
     * @return list of all history of one bank account
     */
    public List<AccountHistory> getHistory(int accountId) {

        List<AccountHistory> histories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement psHistory = connection.prepareStatement
                    (resourceBundle.getString("GET_HISTORY"));

            psHistory.setInt(1, accountId);

            ResultSet resultSet = psHistory.executeQuery();

            while (resultSet.next()) {
                histories.add(getAccountHistoryFromDB(resultSet));
            }
            return histories;


        } catch (SQLException e) {
            logger.error("get history error", e);
            return null;
        }


    }

    /**
     * This method needs only for getting an account history from database by result of ResultSet
     *
     * @param resultSet
     * @return AccountHistory or null if history doesn't exist.
     */
    private AccountHistory getAccountHistoryFromDB(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("history_id");
            int accountId = resultSet.getInt("account_id");
            BigDecimal sum = resultSet.getBigDecimal("sum");
            String partnerName = resultSet.getString("partner_name");
            Timestamp operationDate = resultSet.getTimestamp("operation_date");
            OperationType operationType = OperationType.valueOf
                    (resultSet.getString("operation_type").toUpperCase());

            JdbcFactory jdbcFactory = JdbcFactory.getInstance();
            AccountAPI accountAPI = jdbcFactory.getJdbcAccount();
            Account account = accountAPI.read(accountId);

            return new AccountHistory(id, account, sum, partnerName,
                    operationDate, operationType);

        } catch (SQLException e) {
            logger.error("get history from db error", e);
            return null;
        }
    }
}
