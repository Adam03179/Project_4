package dao;

import dbmodels.Account;
import dbmodels.AccountHistory;
import dbmodels.Client;
import dbmodels.OperationType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAOAccount {
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(DAOAccount.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    public DAOAccount(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean createAccount(Account account) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psCreateAccount = connection.prepareStatement
                    (resourceBundle.getString("CREATE_ACCOUNT"));

            psCreateAccount.setInt(1, account.getClientId());
            psCreateAccount.setString(2, account.getNumber());
            psCreateAccount.setDouble(3, account.getInterest());
            psCreateAccount.setDate(4, account.getOpenDate());
            psCreateAccount.setDouble(5, account.getBalance());
            psCreateAccount.setString(6, account.getCurrency());
            psCreateAccount.setBoolean(7, account.isBlocked());
            return true;
        } catch (SQLException e) {
            logger.error("create account error ", e);
            return false;
        }


    }

    public boolean blockAccount(AccountHistory history) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));
            psWriteHistory.setInt(1, history.getAccountId());
            psWriteHistory.setString(2, OperationType.BLOCK.name());
            psWriteHistory.setDouble(3, history.getSum());
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setDate(5, history.getOperationDate());
            psWriteHistory.execute();


            PreparedStatement psBlockAccount = connection.prepareStatement
                    (resourceBundle.getString("BLOCK_ACCOUNT"));
            psBlockAccount.setInt(1, history.getAccountId());
            psBlockAccount.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("block account error", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }

    }

    public boolean unblockAccount(AccountHistory history) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));
            psWriteHistory.setInt(1, history.getAccountId());
            psWriteHistory.setString(2, OperationType.UNBLOCK.name());
            psWriteHistory.setDouble(3, history.getSum());
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setDate(5, history.getOperationDate());
            psWriteHistory.execute();


            PreparedStatement preparedStatement = connection.prepareStatement
                    (resourceBundle.getString("UNBLOCK_ACCOUNT"));
            preparedStatement.setInt(1, history.getAccountId());
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("unblock account error", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }

    }


    public List<Account> getAllAccounts(Client client) {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAllAccounts = connection.prepareStatement
                    (resourceBundle.getString("GET_ALL_ACCOUNTS"));
            psGetAllAccounts.setInt(1, client.getId());

            ResultSet resultSet = psGetAllAccounts.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                String number = resultSet.getString("number");
                double interest = resultSet.getDouble("interest");
                Date openDate = resultSet.getDate("open_date");
                double balance = resultSet.getDouble("balance");
                String currency = resultSet.getString("currency");
                int clientId = resultSet.getInt("clients_id");
                boolean isBlocked = resultSet.getBoolean("is_blocked");

                accounts.add(new Account(id, clientId, number, interest, openDate,
                        balance, currency, isBlocked));

            }
            return accounts;

        } catch (SQLException e) {
            logger.error("error get all accounts", e);
            return null;
        }

    }

    public boolean addFunds(AccountHistory history, double sum) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));
            psWriteHistory.setInt(1, history.getAccountId());
            psWriteHistory.setString(2, OperationType.DEPOSIT.name());
            psWriteHistory.setDouble(3, sum);
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setDate(5, history.getOperationDate());
            psWriteHistory.execute();


            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("ADD_FUNDS"));
            psAddFunds.setDouble(1, sum);
            psAddFunds.setInt(2, history.getAccountId());
            psAddFunds.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            logger.error("add funds error ", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }


    }

    public boolean withdrawFunds(AccountHistory history, double sum) throws SQLException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));
            psWriteHistory.setInt(1, history.getAccountId());
            psWriteHistory.setString(2, OperationType.WITHDRAW.name());
            psWriteHistory.setDouble(3, sum);
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setDate(5, history.getOperationDate());
            psWriteHistory.execute();


            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("WITHDRAW_FUNDS"));
            psAddFunds.setDouble(1, sum);
            psAddFunds.setInt(2, history.getAccountId());
            psAddFunds.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            logger.error("withdraw funds error ", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }


    }

    public boolean transferFunds(AccountHistory history, double sum,
                                 AccountHistory partnersHistory) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement psWriteHistory = connection.prepareStatement
                    (resourceBundle.getString("WRITE_HISTORY"));
            psWriteHistory.setInt(1, history.getAccountId());
            psWriteHistory.setString(2, OperationType.TRANSFER.name());
            psWriteHistory.setDouble(3, sum);
            psWriteHistory.setString(4, history.getPartnerName());
            psWriteHistory.setDate(5, history.getOperationDate());

            withdrawFunds(history, sum);
            addFunds(partnersHistory, sum);


            return true;

        } catch (SQLException e) {
            logger.error("transfer funds error ", e);
            return false;
        }

    }


}
