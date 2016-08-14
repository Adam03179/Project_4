package ua.gerasymenko.dao;

import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The JdbcAccount class responds for getting and putting information about
 * bank account into and from database with jdbc help.
 *
 * @author Igor Gerasymenko
 */
public class JdbcAccount implements AccountAPI {
    private static final Logger logger = Logger.getLogger(JdbcAccount.class);
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("requestsql");

    private JdbcFactory jdbcFactory = JdbcFactory.getInstance();
    private AccountHistoryAPI historyAPI;
    private UserAPI userAPI;
    private DataSource dataSource;

    public JdbcAccount(DataSource dataSource) {
        historyAPI = jdbcFactory.getJdbcAccountHistory();
        userAPI = jdbcFactory.getJdbcUser();
        this.dataSource = dataSource;
    }

    /**
     * This method adds information about new bank account to database.
     *
     * @param account
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean create(Account account) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psCreateAccount = connection.prepareStatement
                    (resourceBundle.getString("CREATE_ACCOUNT"));

            psCreateAccount.setInt(1, account.getUser().getId());
            psCreateAccount.setString(2, account.getNumber());
            psCreateAccount.setDouble(3, account.getInterest());
            psCreateAccount.setDate(4, account.getOpenDate());
            psCreateAccount.setBigDecimal(5, account.getBalance());
            psCreateAccount.setString(6, account.getCurrency());

            psCreateAccount.execute();

            return true;
        } catch (SQLException e) {
            logger.error("create account error ", e);
            return false;
        }


    }

    /**
     * This method adds information about new locking bank account to database.
     *
     * @param history about the operation which will be added in database.
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean lockAccount(AccountHistory history) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            historyAPI.writeHistory(history, connection);

            PreparedStatement psLockAccount = connection.prepareStatement
                    (resourceBundle.getString("LOCK_ACCOUNT"));

            psLockAccount.setInt(1, history.getAccount().getId());
            psLockAccount.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("lock account error", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }

    }

    /**
     * This method adds information about new unlocking bank account to database.
     *
     * @param history about the operation which will be added in database.
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean unlockAccount(AccountHistory history) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            historyAPI.writeHistory(history, connection);

            PreparedStatement preparedStatement = connection.prepareStatement
                    (resourceBundle.getString("UNLOCK_ACCOUNT"));
            preparedStatement.setInt(1, history.getAccount().getId());
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("unlock account error", e);
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }

    }

    /**
     * This method gets information about all accounts one specific user .
     *
     * @param userId
     * @return list of all bank accounts of one user
     */
    public List<Account> getAllAccounts(int userId) {
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAllAccounts = connection.prepareStatement
                    (resourceBundle.getString("GET_ALL_ACCOUNTS"));
            psGetAllAccounts.setInt(1, userId);

            ResultSet resultSet = psGetAllAccounts.executeQuery();

            while (resultSet.next()) {
                accounts.add(getAccountFromDB(resultSet));
            }
            return accounts;

        } catch (SQLException e) {
            logger.error("error get all accounts", e);
            return null;
        }

    }

    /**
     * This method makes changes to the database, adds a certain sum to the user's bank account
     *
     * @param history about the operation which will be added in database.
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean addFunds(AccountHistory history)
            throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {

            historyAPI.writeHistory(history, connection);

            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("ADD_FUNDS"));
            psAddFunds.setBigDecimal(1, history.getSum());
            psAddFunds.setInt(2, history.getAccount().getId());
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

    /**
     * This method makes changes to the database, deducts a certain sum from the user's bank account
     *
     * @param history about the operation which will be added in database.
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean withdrawFunds(AccountHistory history)
            throws SQLException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            historyAPI.writeHistory(history, connection);

            PreparedStatement psWithdrawFunds = connection.prepareStatement
                    (resourceBundle.getString("WITHDRAW_FUNDS"));
            psWithdrawFunds.setBigDecimal(1, history.getSum());
            psWithdrawFunds.setInt(2, history.getAccount().getId());
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

    /**
     * This method makes changes to the database, deducts a certain sum from
     * the user's bank account and adds this sum to the  bank account of his
     * partner.
     *
     * @param history about the operation which will be added in database.
     * @return true - if operation successful, false - if operation failed.
     */
    public boolean transferFunds(AccountHistory history,
                                 AccountHistory partnersHistory) throws SQLException {

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        try {
            historyAPI.writeHistory(history, connection);

            PreparedStatement psWithdrawFunds = connection.prepareStatement
                    (resourceBundle.getString("WITHDRAW_FUNDS"));
            psWithdrawFunds.setBigDecimal(1, history.getSum());
            psWithdrawFunds.setInt(2, history.getAccount().getId());
            psWithdrawFunds.executeUpdate();

            historyAPI.writeHistory(partnersHistory, connection);

            PreparedStatement psAddFunds = connection.prepareStatement
                    (resourceBundle.getString("ADD_FUNDS"));
            psAddFunds.setBigDecimal(1, partnersHistory.getSum());
            psAddFunds.setInt(2, partnersHistory.getAccount().getId());
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

    /**
     * This method checks if exist bank account  in database or not.
     *
     * @param numberOfAccount
     * @return true - if account is exist, false - if not.
     */
    public boolean isExist(String numberOfAccount) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psIsAccountExist = connection.prepareStatement
                    (resourceBundle.getString("IS_ACCOUNT_EXIST"));

            psIsAccountExist.setString(1, numberOfAccount);

            ResultSet resultSet = psIsAccountExist.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            logger.error("is account exist error ", e);
            return false;
        }


    }

    /**
     * This method seeks in database the Id of bank account
     *
     * @param numberOfAccount
     * @return id of this account or null if account doesn't exist.
     */
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

    /**
     * This method seeks in database the bank account by ID
     *
     * @param accountId
     * @return Account or null if account doesn't exist.
     */
    public Account read(int accountId) {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAccById = connection.prepareStatement
                    (resourceBundle.getString("GET_ACCOUNT_BY_ID"));

            psGetAccById.setInt(1, accountId);
            ResultSet result = psGetAccById.executeQuery();

            result.next();
            return getAccountFromDB(result);

        } catch (SQLException e) {
            logger.error("get account by id error", e);
            return null;
        }

    }

    /**
     * This method erases the account from database, by id
     *
     * @param id
     * @return true - if operation successful, false - if operation failed.
     */

    @Override
    public boolean delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psDelete = connection.prepareStatement
                    (resourceBundle.getString("DELETE_ACCOUNT"));
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("delete account error ", e);
            return false;
        }

    }

    /**
     * This method seeks in database all locked bank accounts
     *
     * @return List of all locked bunk accounts or null if account doesn't exist.
     */
    public List<Account> getAllLockedAccounts() {
        List<Account> lockedAccounts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement psGetAllLockedAccounts = connection.prepareStatement
                    (resourceBundle.getString("GET_ALL_LOCKED_ACCOUNTS"));
            ResultSet resultSet = psGetAllLockedAccounts.executeQuery();

            while (resultSet.next()) {
                lockedAccounts.add(getAccountFromDB(resultSet));
            }
            return lockedAccounts;
        } catch (SQLException e) {
            logger.error("error get all locked accounts", e);
            return null;
        }

    }

    /**
     * This method needs only for getting a bank account from database by result of ResultSet
     *
     * @param resultSet
     * @return account or null if account doesn't exist.
     */
    private Account getAccountFromDB(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("account_id");
            String number = resultSet.getString("number");
            double interest = resultSet.getDouble("interest");
            Date openDate = resultSet.getDate("open_date");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            String currency = resultSet.getString("currency");
            boolean isBlocked = resultSet.getBoolean("is_blocked");
            int usersId = resultSet.getInt("users_id");

            User user = userAPI.read(usersId);

            return new Account(id, user, number, interest,
                    openDate, balance, currency, isBlocked);

        } catch (SQLException e) {
            logger.error("error get account from DB", e);
            return null;
        }

    }


}
