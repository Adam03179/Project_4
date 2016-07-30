package main.java.commands;

import main.java.controllers.SessionRequestWrapper;
import main.java.dao.DAOAccount;
import main.java.dao.DAOFactory;
import main.java.entities.Account;
import main.java.entities.AccountHistory;
import main.java.entities.OperationType;
import org.apache.log4j.Logger;
import main.java.managers.ConfigurationManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The AccountLockCommand class realizing the logic of locking account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, witch executes all needed task.
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
     * @param  request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {
        String path = ConfigurationManager.getProperty("path.page.operationSuccess");
        request.getSession().setAttribute("path", path);


        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOAccount daoAccount = daoFactory.getDAOAccount();

        //in request we have number of account and sum, so what we need to split request
        String numberOfAccount = request.getValueByName("lock").split(" ")[0];

        int accountId = daoAccount.getId(numberOfAccount);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Account account = daoAccount.getAccountById(accountId);


        AccountHistory accountHistory = new AccountHistory(account,
                account.getBalance(), account.getUser().getName()+" "
                + account.getUser().getSurname(), timestamp,
                OperationType.LOCK);

        try {
            daoAccount.lockAccount(accountHistory);
        } catch (SQLException e) {
            logger.error("Account lock command error", e);
        }

        return path;
    }
}
