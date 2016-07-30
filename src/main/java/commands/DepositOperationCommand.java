package main.java.commands;

import main.java.controllers.SessionRequestWrapper;
import main.java.dao.DAOAccount;
import main.java.dao.DAOFactory;
import main.java.entities.Account;
import main.java.entities.AccountHistory;
import main.java.entities.OperationType;
import org.apache.log4j.Logger;
import main.java.managers.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The DepositOperationCommand class realizing the logic of deposit in bank account.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class DepositOperationCommand implements Command {
    private static final Logger logger = Logger.getLogger(DepositOperationCommand.class);
    private static DepositOperationCommand instance = null;

    private DepositOperationCommand() {
    }

    public static synchronized DepositOperationCommand getInstance() {

        if (instance == null) {
            instance = new DepositOperationCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for deposit in bank account.
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

        String numberOfAccount = request.getValueByName("deposit");
        BigDecimal sum = new BigDecimal(request.getValueByName("sum"));
        int accountId = daoAccount.getId(numberOfAccount);

        Account account = daoAccount.getAccountById(accountId);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());


        int compare = sum.compareTo(new BigDecimal(0));
        if (compare < 0) {
            path = ConfigurationManager.getProperty("path.page.error");

        } else {
            AccountHistory accountHistory = new AccountHistory(account,
                    sum, account.getUser().getName() + " "
                    + account.getUser().getSurname(), timestamp,
                    OperationType.DEPOSIT);

            try {
                daoAccount.addFunds(accountHistory);
            } catch (SQLException e) {
                logger.error("Deposit operation command error", e);
                return "path.page.error";
            }
        }

        return path;
    }
}
