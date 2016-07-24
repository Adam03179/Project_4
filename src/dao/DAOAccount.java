package dao;

import dbmodels.Account;
import dbmodels.AccountHistory;
import dbmodels.OperationType;
import dbmodels.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAOAccount {
    private DataSource dataSource;
    private DAOAccountHistory historyDAO;
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

            psCreateAccount.setInt(1, account.getUserId());
            psCreateAccount.setString(2, account.getNumber());
            psCreateAccount.setDouble(3, account.getInterest());
            psCreateAccount.setDate(4, account.getOpenDate());
            psCreateAccount.setDouble(5, account.getBalance());
            psCreateAccount.setString(6, account.getCurrency());
            psCreateAccount.execute();

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

            historyDAO = new DAOAccountHistory(dataSource);
            historyDAO.writeHistory(history, OperationType.BLOCK, connection);

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

            historyDAO = new DAOAccountHistory(dataSource);
            historyDAO.writeHistory(history, OperationType.UNBLOCK, connection);

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


    public List<Account> getAllAccounts(int userId) {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAllAccounts = connection.prepareStatement
                    (resourceBundle.getString("GET_ALL_ACCOUNTS"));
            psGetAllAccounts.setInt(1, userId);

            ResultSet resultSet = psGetAllAccounts.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                String number = resultSet.getString("number");
                double interest = resultSet.getDouble("interest");
                Date openDate = resultSet.getDate("open_date");
                double balance = resultSet.getDouble("balance");
                String currency = resultSet.getString("currency");
                int clientId = resultSet.getInt("users_id");
                boolean isBlocked = resultSet.getBoolean("is_blocked");

                accounts.add(new Account(id, clientId, number,
                        interest, openDate, balance, currency, isBlocked));

            }
            return accounts;

        } catch (SQLException e) {
            logger.error("error get all accounts", e);
            return null;
        }

    }

    public boolean addFunds(AccountHistory history)
            throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            historyDAO = new DAOAccountHistory(dataSource);
            historyDAO.writeHistory
                    (history, OperationType.DEPOSIT, connection);

            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("ADD_FUNDS"));
            psAddFunds.setDouble(1, history.getSum());
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

    public boolean withdrawFunds(AccountHistory history)
            throws SQLException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            historyDAO = new DAOAccountHistory(dataSource);
            historyDAO.writeHistory(history,
                    OperationType.WITHDRAW, connection);

            PreparedStatement psWithdrawFunds = connection.prepareStatement
                    (resourceBundle.getString("WITHDRAW_FUNDS"));
            psWithdrawFunds.setDouble(1, history.getSum());
            psWithdrawFunds.setInt(2, history.getAccountId());
            psWithdrawFunds.executeUpdate();

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

    public boolean transferFunds(AccountHistory history,
                                 AccountHistory partnersHistory) throws SQLException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            historyDAO = new DAOAccountHistory(dataSource);
            historyDAO.writeHistory(history,
                    OperationType.TRANSFER, connection);


            PreparedStatement psWithdrawFunds = connection.prepareStatement
                    (resourceBundle.getString("WITHDRAW_FUNDS"));
            psWithdrawFunds.setDouble(1, history.getSum());
            psWithdrawFunds.setInt(2, history.getAccountId());
            psWithdrawFunds.executeUpdate();


            historyDAO.writeHistory(partnersHistory,
                    OperationType.DEPOSIT, connection);


            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("ADD_FUNDS"));
            psAddFunds.setDouble(1, partnersHistory.getSum());
            psAddFunds.setInt(2, partnersHistory.getAccountId());
            psAddFunds.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            logger.error("transfer funds error ", e);
            connection.rollback();
            return false;
        } finally {

            connection.close();
        }

    }

    public boolean isExist(String accountNumber){

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psIsAccountExist = connection.prepareStatement
                    (resourceBundle.getString("IS_ACCOUNT_EXIST"));

            psIsAccountExist.setString(1, accountNumber);

            ResultSet resultSet = psIsAccountExist.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            logger.error("is account exist error ", e);
            return false;
        }




    }
    public Integer getId(String numberOfAccount) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetId = connection.prepareStatement
                    (resourceBundle.getString("GET_ACCOUNT_ID"));

            psGetId.setString(1, numberOfAccount);
            ResultSet result = psGetId.executeQuery();

            result.next();
            return result.getInt("account_id");


        } catch (SQLException e) {
            logger.error("get account id error", e);
            return null;
        }

    }


}
