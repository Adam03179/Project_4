package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.dao.DAOAccount;
import ua.gerasymenko.dao.DAOFactory;
import ua.gerasymenko.models.Account;
import ua.gerasymenko.models.AccountHistory;
import ua.gerasymenko.models.OperationType;
import org.apache.log4j.Logger;
import ua.gerasymenko.managers.ConfigurationManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The WithdrawOperationCommand class realizing the logic of withdraw funds.
 * This class is Singleton.
 * It implements interface Command, and it is part of Command and Factory patterns.
 * It includes only one method, which executes all needed task.
 * @author Igor Gerasymenko
 */
public class WithdrawOperationCommand implements Command {
    private static final Logger logger = Logger.getLogger(WithdrawOperationCommand.class);
    private static WithdrawOperationCommand instance = null;

    private WithdrawOperationCommand() {
    }

    public static synchronized WithdrawOperationCommand getInstance() {
        if (instance == null) {
            instance = new WithdrawOperationCommand();
        }
        return instance;
    }

    /**
     * This method executes all needed task for withdraw funds.
     * It uses SessionRequestWrapper to initialized all needed parameters
     * for creating Account class. If operation was success
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
        String numberOfAccount = request.getValueByName("withdraw").split(" ")[0];
        BigDecimal sum = new BigDecimal(request.getValueByName("sum"));
        int accountId = daoAccount.getId(numberOfAccount);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Account account = daoAccount.getAccountById(accountId);

        int compare = sum.compareTo(new BigDecimal(0));

        if (sum.compareTo(account.getBalance()) > 0 || compare < 0) {
            path = ConfigurationManager.getProperty("path.page.error");
        } else {
            AccountHistory accountHistory = new AccountHistory(account,
                    sum, account.getUser().getName() + " "
                    + account.getUser().getSurname(), timestamp,
                    OperationType.WITHDRAW);

            try {
                daoAccount.withdrawFunds(accountHistory);
            } catch (SQLException e) {
                logger.error("Withdraw operation command error", e);
            }
        }
        return path;
    }
}
