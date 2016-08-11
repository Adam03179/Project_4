package ua.gerasymenko.dao;

import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;

import java.sql.SQLException;
import java.util.List;

public interface AccountAPI extends GenericDAO<Account> {
    public boolean lockAccount(AccountHistory history) throws SQLException;
    public boolean unlockAccount(AccountHistory history) throws SQLException;
    public List<Account> getAllAccounts(int userId);
    public boolean addFunds(AccountHistory history) throws SQLException;
    public boolean withdrawFunds(AccountHistory history) throws SQLException;
    public boolean transferFunds(AccountHistory history,
                                 AccountHistory partnersHistory) throws SQLException;
    public boolean isExist(String numberOfAccount);
    public Integer getId(String numberOfAccount);
    public List<Account> getAllLockedAccounts();
}