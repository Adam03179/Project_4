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
 * The AccountUnlockCommand class realizing the logic of unlocking account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class AccountUnlockCommand implements Command {
    private static final Logger logger = Logger.getLogger(AccountUnlockCommand.class);
    private static AccountUnlockCommand instance = null;

    private AccountUnlockCommand() {
    }

    public static synchronized AccountUnlockCommand getInstance() {

        if (instance == null) {
            instance = new AccountUnlockCommand();
        }
        return instance;
    }


    /**
     * This method executes all needed task for unlocking user's account.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class and AccountHistory class. If operation was success
     * it returns path to success-page to user, if not - error page.
     * @param  request
     * @return path to next page
     */
    @Override
    public String execute(SessionRequestWrapper request) {

        String path = ConfigurationManager.getProperty("path.page.index");

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOAccount daoAccount = daoFactory.getDAOAccount();

        //in request we have number of account and sum, so what we need to split request
        String numberOfAccount = request.getValueByName("unlock").split(" ")[0];
        int accountId = daoAccount.getId(numberOfAccount);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Account account = daoAccount.getAccountById(accountId);


        AccountHistory accountHistory = new AccountHistory(account,
                account.getBalance(), account.getUser().getName() + " "
                + account.getUser().getSurname(), timestamp,
                OperationType.UNLOCK);

        try {
            daoAccount.unlockAccount(accountHistory);
        } catch (SQLException e) {
            logger.error("Account unlock command error", e);
        }

        return path;
    }
}