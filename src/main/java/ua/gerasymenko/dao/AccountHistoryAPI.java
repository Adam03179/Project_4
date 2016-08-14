package ua.gerasymenko.dao;

import ua.gerasymenko.models.AccountHistory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The AccountHistoryAPI extends GenericDAO interface.
 * It needs for separation of the layers of MVC.
 * Class that implements this interface, should work with database.
 *
 * @author Igor Gerasymenko
 */
public interface AccountHistoryAPI extends GenericDAO<AccountHistory> {
    boolean writeHistory(AccountHistory history,
                         Connection connection) throws SQLException;

    List<AccountHistory> getHistory(int accountId);
}
