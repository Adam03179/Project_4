package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.AccountAPI;
import ua.gerasymenko.dao.JdbcFactory;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.OperationType;
import org.apache.log4j.Logger;
import ua.gerasymenko.managers.ConfigurationManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The AccountLockCommand class realizing the logic of locking account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, witch executes all needed task.
 *
 * @author Igor Gerasymenko
 */
public class AccountLockCommand implements Command {

    private static final Logger logger = Logger
            .getLogger(AccountLockCommand.class);

    private static AccountLockCommand instance = null;

    private AccountLockCommand() {
    }

    public static synchronized AccountLockCommand getInstance() {

        if (instance == null) {
            instance = new AccountLockCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for locking user's account.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class and AccountHistory class. If operation was success
     * it returns path to success-page to user, if not - error page.
     *
     * @param request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        JdbcFactory jdbcFactory = JdbcFactory.getInstance();
        AccountAPI account = jdbcFactory.getJdbcAccount();

        //in request we have number of account and sum, so what we need to split request
        String numberOfAccount = request.getValueByName("lock").split(" ")[0];

        int accountId = account.getId(numberOfAccount);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Account accountForHistory = account.read(accountId);

        AccountHistory accountHistory = new AccountHistory(accountForHistory,
                accountForHistory.getBalance(), accountForHistory.getUser().getName() + " "
                + accountForHistory.getUser().getSurname(), timestamp,
                OperationType.LOCK);

        String path = ConfigurationManager.getProperty("path.page.operationSuccess");

        try {
            account.lockAccount(accountHistory);
            request.getSession().setAttribute("path", path);
        } catch (SQLException e) {
            logger.error("Account lock command error", e);
            return ConfigurationManager.getProperty("path.page.error");
        }

        return path;
    }
}
