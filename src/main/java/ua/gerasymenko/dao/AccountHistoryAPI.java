package ua.gerasymenko.dao;

import ua.gerasymenko.models.AccountHistory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AccountHistoryAPI extends GenericDAO<AccountHistory> {
    boolean writeHistory(AccountHistory history,
                                Connection connection) throws SQLException;
    List<AccountHistory> getHistory(int accountId);
}
