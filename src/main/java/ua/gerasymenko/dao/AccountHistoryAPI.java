package ua.gerasymenko.dao;

import ua.gerasymenko.models.AccountHistory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The AccountHistoryAPI interface extends GenericDAO interface, this interface
 * needs for separation of the layers of MVC. Class that implements
 * this interface, may work with database.
 *
 * @author Igor Gerasymenko
 */
public interface AccountHistoryAPI extends GenericDAO<AccountHistory> {
    boolean writeHistory(AccountHistory history,
                                Connection connection) throws SQLException;
    List<AccountHistory> getHistory(int accountId);
}
